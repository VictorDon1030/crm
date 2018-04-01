$(function () {

    var productAanalyze_datagrid = $("#productAanalyze_datagrid");
    initBar({});
    var methodObj = {
        //查询全部
        all:function () {
            $("#show_Other").css("display","none");//show的display属性设置为none（隐藏）
            productAanalyze_datagrid.datagrid("load",{"today":null});
            initBar({});
        },
        //按照今日查询
        today:function () {
            $("#show_Other").css("display","none");//show的display属性设置为none（隐藏）
            productAanalyze_datagrid.datagrid("load",{"today":1});
            initBar({"today":1});
        },
        //按照昨日查询
        week:function () {
            $("#show_Other").css("display","none");//show的display属性设置为none（隐藏）
            productAanalyze_datagrid.datagrid("load",{"week":7});
            initBar({"week":7});
        },
        //按照本月查询
        month:function () {
            $("#show_Other").css("display","none");//show的display属性设置为none（隐藏）
            productAanalyze_datagrid.datagrid("load",{"month":30});
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
            //获取2个输入框的内容，让productAanalyze_datagrid重新加载
            var startDate=$("#startDate").val();
            var endDate=$("#endDate").val();
            productAanalyze_datagrid.datagrid("load",
                {"beginDate":startDate,"endDate":endDate});
            initBar({"beginDate":startDate,"endDate":endDate});
        }
    };

    //给所有具有data-btn属性的A标签统一绑定点击事件
    $("a[data-btn]").click(function () {
        var btn = $(this).data("btn");
        methodObj[btn]();
    });

    //productAanalyze_datagrid的数据表格:产品分析
    productAanalyze_datagrid.datagrid({
        fit: true,
        fitColumns: true,
        pagination:true,
        striped: true,
        url: '/productAnalyze/queryByDate.do',
        singleSelect: true,
        columns: [[
            {field: 'goodsMark', title: '商品条码', width: 100},
            {field: 'name', title: '商品名称', width: 100},
            {field: 'totalNumber', title: '销售数量', width: 100},
            {field: 'totalAmount', title: '销售金额', width: 100},
            {field: 'totalProfit', title: '销售毛利', width: 100},
            {field: 'grossProfit', title: '毛利率', width: 100}
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
            data : [ '销售总额' ]
        },
        xAxis : [ {
            type : 'category',
        } ],
        yAxis : [ {
            type : 'value'
        } ],
        series : [ {
            name : "销售总额",
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
        url : '/productAnalyze/queryForBar.do',
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

