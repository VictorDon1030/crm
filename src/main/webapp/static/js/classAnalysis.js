$(function () {

    var classAnalysis_datagrid = $("#classAnalysis_datagrid");
    //页面一加载完，就要加载饼状图的数据
    ajaxData({});
    var methodObj = {
        //查询全部
        all:function () {
            $("#show_Other").css("display","none");//show的display属性设置为none（隐藏）
            classAnalysis_datagrid.datagrid("load",{"today":null});
            ajaxData({});
        },
        //按照今日查询
        today:function () {
            $("#show_Other").css("display","none");//show的display属性设置为none（隐藏）
            classAnalysis_datagrid.datagrid("load",{"today":1});
            ajaxData({"today":1});
        },
        //按照昨日查询
        week:function () {
            $("#show_Other").css("display","none");//show的display属性设置为none（隐藏）
            classAnalysis_datagrid.datagrid("load",{"week":7});
            ajaxData({"week":7});
        },
        //按照本月查询
        month:function () {
            $("#show_Other").css("display","none");//show的display属性设置为none（隐藏）
            classAnalysis_datagrid.datagrid("load",{"month":30});
            ajaxData({"month":30});
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
            //获取2个输入框的内容，让classAnalysis_datagrid重新加载
            var startDate=$("#startDate").val();
            var endDate=$("#endDate").val();
            classAnalysis_datagrid.datagrid("load",
                {"beginDate":startDate,"endDate":endDate});
            ajaxData({"beginDate":startDate,"endDate":endDate});
        },
        //点击导出
        export:function () {
            window.open("/classAnalysis/exportXls.do");
        }
    };

    //给所有具有data-btn属性的A标签统一绑定点击事件
    $("a[data-btn]").click(function () {
        var btn = $(this).data("btn");
        methodObj[btn]();
    });

    //classAnalysis_datagrid的数据表格:产品分析
    classAnalysis_datagrid.datagrid({
        fit: true,
        fitColumns: true,
        pagination:true,
        striped: true,
        url: '/classAnalysis/queryByDate.do',
        singleSelect: true,
        columns: [[
            {field: 'name', title: '产品分类', width: 100},
            {field: 'totalNumber', title: '销售数量', width: 100,formatter:function (value) {
                    return value?value:0;
                }},
            {field: 'totalAmount', title: '销售金额', width: 100,formatter:function (value) {
                    return value?value:0;
                }},
            {field: 'accountFor', title: '所占比例', width: 100,formatter:function (value) {
                    return value?value:'0.00%';
                }}
        ]]
    });

});

function ajaxData(param) {
    $.ajax({
        type : 'post',
        async : false, //同步执行
        url :'/classAnalysis/queryForPie.do', //web.xml中注册的Servlet的url-pattern
        data :param,
        dataType : 'json', //返回数据形式为json
        success : function(result) {
            if (result) {
                //把result(即Json数据)以参数形式放入Echarts代码中
                bind(result);
            }
        }/*,
        error : function(errorMsg) {
            alert("加载数据失败");
        }*/
    }); //ajax
}


//JS成功后的代码
function bind(result){
    // 基于准备好的dom，初始化echarts图表
    var TypeSalesChart = echarts.init(document.getElementById('pie'));
    var option = {
        tooltip : {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: {
            orient : 'vertical',
            x : 'left',
            data:(function(){
                var res = [];
                var len = result.length;
                for(var i=0,size=len;i<size;i++) {
                    res.push({
                        name: result[i].typeName,
                    });
                }
                return res;
            })()

        },
        series : [
            {
                name:'销售总金额',
                type:'pie',
                radius : '55%',
                center: ['50%', '60%'],
                data:(function(){
                    var res = [];
                    var len = result.length;
                    for(var i=0,size=len;i<size;i++) {
                        res.push({
                            //通过把result进行遍历循环来获取数据并放入Echarts中
                            name: result[i].typeName,
                            value: result[i].totalAmount
                        });
                    }
                    return res;
                })()
            }
        ]
    };
    // 为echarts对象加载数据
    TypeSalesChart.setOption(option);
}
