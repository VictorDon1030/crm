$(function () {

    var payItem_datagrid = $("#payItem_datagrid");
    var methodObj = {
        //按照今日查询
        today:function () {
            payItem_datagrid.datagrid("load",{"today":1});
        },
        //按照本周查询
        week:function () {
            payItem_datagrid.datagrid("load",{"week":7});
        },
        //按照本月查询
        month:function () {
            payItem_datagrid.datagrid("load",{"month":30});
        },
        //点击“其他”按钮，控制开始日期输入框、结束日期输入框、search按钮的显示与隐藏
        other:function () {

        },
        //点击搜索按钮，按照输入的日期查询
        searchs:function () {

        }
    };
    //给所有具有data-btn属性的A标签统一绑定点击事件
    $("a[data-btn]").click(function () {
        var btn = $(this).data("btn");
        methodObj[btn]();
    });

    //payItem的数据表格
    payItem_datagrid.datagrid({
        fit: true,
        fitColumns: true,
        pagination:true,
        striped: true,
        url: '/payItem/list.do',
        toolbar: '#pay_toolbar',
        singleSelect: true,
        columns: [[
            {field: 'type', title: '支出分类', width: 100},
            {field: 'amount', title: '支出金额', width: 100},
            {field: 'date', title: '操作时间', width: 100},
            {field: 'payUser', title: '支出人员', width: 100,formatter: function(value){
                    return value?value.username:null;
                }},
            {field: 'remark', title: '备注说明', width: 100}/*,
            {field:'<a href="#" data-btn="input">编辑</a>'+'<a href="#" data-btn="input">删除</a>',
                title: '操作', width: 100}*/
        ]]
    });

});
