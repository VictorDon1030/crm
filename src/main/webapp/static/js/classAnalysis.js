$(function () {

    var classAnalysis_datagrid = $("#classAnalysis_datagrid");
    var methodObj = {
        //查询全部
        all:function () {
            $("#show_Other").css("display","none");//show的display属性设置为none（隐藏）
            classAnalysis_datagrid.datagrid("load",{"today":null});
        },
        //按照今日查询
        today:function () {
            $("#show_Other").css("display","none");//show的display属性设置为none（隐藏）
            classAnalysis_datagrid.datagrid("load",{"today":1});
        },
        //按照昨日查询
        week:function () {
            $("#show_Other").css("display","none");//show的display属性设置为none（隐藏）
            classAnalysis_datagrid.datagrid("load",{"week":7});
        },
        //按照本月查询
        month:function () {
            $("#show_Other").css("display","none");//show的display属性设置为none（隐藏）
            classAnalysis_datagrid.datagrid("load",{"month":30});
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
            //获取2个输入框的内容，让classAnalysis_datagrid重新加载
            var startDate=$("#startDate").val();
            var endDate=$("#endDate").val();
            classAnalysis_datagrid.datagrid("load",
                {"beginDate":startDate,"endDate":endDate});
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
