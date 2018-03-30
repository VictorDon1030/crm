$(function () {

    var memberAanalyze_datagrid = $("#memberAanalyze_datagrid");
    initBar({});
    var methodObj = {
        //查询全部
        all:function () {
            $("#show_Other").css("display","none");//show的display属性设置为none（隐藏）
            memberAanalyze_datagrid.datagrid("load",{"today":null});
            initBar({});
        },
        //按照今日查询
        today:function () {
            $("#show_Other").css("display","none");//show的display属性设置为none（隐藏）
            memberAanalyze_datagrid.datagrid("load",{"today":1});
            initBar({"today":1});
        },
        //按照昨日查询
        week:function () {
            $("#show_Other").css("display","none");//show的display属性设置为none（隐藏）
            memberAanalyze_datagrid.datagrid("load",{"week":7});
            initBar({"week":7});
        },
        //按照本月查询
        month:function () {
            $("#show_Other").css("display","none");//show的display属性设置为none（隐藏）
            memberAanalyze_datagrid.datagrid("load",{"month":30});
            initBar({"month":30});
        },
        //点击“其他”按钮，控制开始日期输入框、结束日期输入框、search按钮的显示与隐藏
        other:function () {
            if($("#show_Other").css("display")=='none'){//如果show是隐藏的

                $("#show_Other").css("display","block");//show的display属性设置为block（显示）

            }else{//如果show是显示的

                $("#show_Other").css("display","none");//show的display属性设置为none（隐藏）

            }
        },
        //点击搜索按钮，按照输入的日期查询
        searchs:function () {
            //获取2个输入框的内容，让memberAanalyze_datagrid重新加载
            var startDate=$("#startDate").val();
            var endDate=$("#endDate").val();
            memberAanalyze_datagrid.datagrid("load",
                {"beginDate":startDate,"endDate":endDate});
            initBar({"beginDate":startDate,"endDate":endDate});
        },
        //点击导出
        export:function () {
            window.open("/memberAnalyze/exportXls.do");
        }
    };

    //给所有具有data-btn属性的A标签统一绑定点击事件
    $("a[data-btn]").click(function () {
        var btn = $(this).data("btn");
        methodObj[btn]();
    });

    //memberAanalyze_datagrid的数据表格:产品分析
    memberAanalyze_datagrid.datagrid({
        fit: true,
        fitColumns: true,
        pagination:true,
        striped: true,
        url: '/memberAnalyze/queryByDate.do',
        singleSelect: true,
        columns: [[
            {field: 'memberNum', title: '会员卡号', width: 100},
            {field: 'name', title: '会员名称', width: 100},
            {field: 'totalNumber', title: '消费笔数', width: 100},
            {field: 'totalAmount', title: '消费金额', width: 100},
            {field: 'totalProfit', title: '消费店铺', width: 100,formatter: function(value,row,index){
                    if (!row.user){
                        return '德客便利店';
                    }
                }
            },
            {field: 'grossProfit', title: '总店', width: 100,formatter: function(value,row,index){
                    if (!row.user){
                        return '德客便利店';
                    }
                }}
        ]]
    });

});


function initBar(param) {
    // 基于准备好的dom，初始化echarts图表
    var myChart = echarts.init(document.getElementById('main'));
    var option = {
        tooltip : {
            show : true
        },
        legend : {
            data : [ '消费总额' ]
        },
        xAxis : [ {
            type : 'category',
        } ],
        yAxis : [ {
            type : 'value'
        } ],
        series : [ {
            name : "消费总额",
            type : "bar",
            itemStyle: {
                normal: {
                    //通过数组下标选择颜色
                    color: function(params) {
                        var colorList = [
                            '#C1232B','#B5C334','#FCCE10','#E87C25','#27727B',
                            '#FE8463','#9BCA63','#FAD860','#F3A43B','#60C0DD'
                        ];
                        return colorList[params.dataIndex]
                    },
                }
            }
        } ]
    };
    //加载数据到option
    loadData(option,param);
// 为echarts对象加载数据
    myChart.setOption(option);

}

//通过ajax加载数据
function loadData(option,param) {
    $.ajax({
        type : 'post',
        async : false, //同步执行
        url : '/memberAnalyze/queryForBar.do',
        data:param,
        dataType : 'json', //返回数据形式为json

        success : function(result) {
            if (result) {
                //初始化xAxis[0]的data
                option.xAxis[0].data = [];
                for ( var i = 0; i < result.length; i++) {
                    //循环输出X轴数据
                    option.xAxis[0].data.push(result[i].typeName);
                }
                //初始化series[0]的data
                option.series[0].data = [];
                for ( var i = 0; i < result.length; i++) {
                    ////循环输出Y轴数据
                    option.series[0].data.push(result[i].totalAmount);
                }
            }
        },
        error : function(errorMsg) {
            alert("加载数据失败");
        }
    });
} //ajax