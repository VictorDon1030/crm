$(function () {

    var productAanalyze_datagrid = $("#productAanalyze_datagrid");
    var selectCondition=null;
    var methodObj = {
        //查询全部
        all:function () {
            $("#show_Other").css("display","none");//show的display属性设置为none（隐藏）
            productAanalyze_datagrid.datagrid("load",{"today":null});
        },
        //按照今日查询
        today:function () {
            $("#show_Other").css("display","none");//show的display属性设置为none（隐藏）
            productAanalyze_datagrid.datagrid("load",{"today":1});
            selectCondition="today=1"
        },
        //按照昨日查询
        week:function () {
            $("#show_Other").css("display","none");//show的display属性设置为none（隐藏）
            productAanalyze_datagrid.datagrid("load",{"week":7});
            selectCondition="week=7";
        },
        //按照本月查询
        month:function () {
            $("#show_Other").css("display","none");//show的display属性设置为none（隐藏）
            productAanalyze_datagrid.datagrid("load",{"month":30});
            selectCondition="month=30";
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
            selectCondition={"beginDate":startDate,"endDate":endDate};
        },
        //点击导出
        export:function () {
            console.log(selectCondition);
            window.open("/productAnalyze/exportXls.do?selectCondition");
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
