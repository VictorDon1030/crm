$(function () {

    var chat_datagrid = $("#chat_datagrid");
    var chat_dialog = $("#chat_dialog");

    var methodObj = {
        //保存按钮
        save: function () {
            $("#chat_form").form("submit", {
                url: '/weiChat/saveOrUpdate.do',
                success: function (data) {
                    data = $.parseJSON(data);
                    if (data.success) {
                        $.messager.alert("温馨提示", "操作成功", "info", function () {
                            chat_datagrid.datagrid("reload");
                            methodObj.cancel();
                        })
                    } else {
                        $.messager.alert("温馨提示", data.msg, "info");
                        methodObj.cancel();
                    }
                }
            })
        },
        //编辑按钮
        editData: function () {
            var val = chat_datagrid.datagrid("getSelected");
            if (!val) {
                $.messager.alert("温馨提示", "请选择一条数据", "info");
                return;
            }
            if (val.applyer) {
                val["applyer.id"] = val.applyer.id
            }
            $("#chat_form").form("load", val);
            chat_dialog.dialog("setTitle", "编辑");
            chat_dialog.dialog("open");
        },
        //删除按钮
        deleteData :function () {
            var val = chat_datagrid.datagrid("getSelected");
            if (!val) {
                $.messager.alert("温馨提示", "请选择一条数据", "info");
                return;
            }
            $.messager.confirm("温馨提示","确定要删除吗?",function (ret) {
                if (ret) {
                    $("#chat_form").form("submit", {
                        url: '/weiChat/delete.do',
                        queryParams:{chatId:val.id},
                        success: function (data) {
                            data = $.parseJSON(data);
                            if (data.success) {
                                $.messager.alert("温馨提示", "操作成功", "info", function () {
                                    chat_datagrid.datagrid("reload");
                                })
                            } else {
                                $.messager.alert("温馨提示", data.msg, "info");
                            }
                        }

                    })
                }
            });
        },
        //取消按钮
        cancel: function () {
            chat_dialog.dialog("close");
        },
        //刷新按钮
        reloadData: function () {
            chat_datagrid.datagrid("load");
        }
    };

    $("a[data-btn]").click(function () {
        var btn = $(this).data("btn");
        methodObj[btn]();
    });


    chat_datagrid.datagrid({
        fit: true,
        fitColumns: true,
        striped: true,
        url: '/weiChat/selectAll.do',
        toolbar: '#chat_toolbar',
        pagination: true,
        rownumbers: true,
        singleSelect: true,
        columns: [[
            {field: 'accountNumber', title: '威信账号', width: 50},
            {field: 'secretkey', title: '应用秘钥', width: 50},
            {field: 'applyKey', title: '用户秘钥', width: 50},
            {
                field: 'applyer', title: '用户ID', width: 50, formatter: function (value, row, index) {
                    return row.applyer.id > 1 ? ""+row.applyer.accountName+""  : "已作废";
                }
            }
        ]]
    });

    chat_dialog.dialog({
        width: 320,
        height: 360,
        buttons: '#chat_button',
        closed: true,
        onClose: function () {
            $("#chat_form").form("clear");
        }
    });
});
