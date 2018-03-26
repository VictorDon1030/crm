$(function () {

    var permission_datagrid = $("#permission_datagrid");

    var methodObj = {
        //新增按钮
        addData: function () {
           $.messager.confirm("温馨提示","确定加载权限?",function (ret) {
               if (ret) {
                   $.get("/permission/load.do",function (data) {
                       if (data.success){
                           permission_datagrid.datagrid("load");
                       } else {
                            $.messager.alert("温馨提示",data.msg,"warning");
                       }
                   });
               }
           });
        },
        //刷新按钮
        reloadData: function () {
            permission_datagrid.datagrid("load");
        }
    };

    $("a[data-btn]").click(function () {
        var btn = $(this).data("btn");
        methodObj[btn]();
    });


    permission_datagrid.datagrid({
        fit: true,
        fitColumns: true,
        striped: true,
        url: '/permission/selectAll.do',
        toolbar: '#permission_toolbar',
        pagination: true,
        rownumbers: true,
        singleSelect: true,
        columns: [[
            {field: 'id', title: '权限编号', width: 50},
            {field: 'name', title: '权限名称', width: 50},
            {field: 'resource', title: '权限表达式', width: 50}
        ]]
    });
});
