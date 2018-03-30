$(function () {

    var app_datagrid = $("#app_datagrid");
    var app_dialog = $("#app_dialog");

    var methodObj = {
        //保存按钮
        save: function () {
            $("#app_form").form("submit", {
                url: '/joinApply/saveOrUpdate.do',
                success: function (data) {
                    data = $.parseJSON(data);
                    if (data.success) {
                        $.messager.alert("温馨提示", "操作成功", "info", function () {
                            app_datagrid.datagrid("reload");
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
            var val = app_datagrid.datagrid("getSelected");
            if (!val) {
                $.messager.alert("温馨提示", "请选择一条数据", "info");
                return;
            }
            $("#app_form").form("load", val);
            app_dialog.dialog("setTitle", "编辑");
            app_dialog.dialog("open");
        },
        //删除按钮
        deleteData :function () {
            var val = app_datagrid.datagrid("getSelected");
            if (!val) {
                $.messager.alert("温馨提示", "请选择一条数据", "info");
                return;
            }
            $.messager.confirm("温馨提示","确定要删除吗?",function (ret) {
                if (ret) {
                    $("#app_form").form("submit", {
                        url: '/joinApply/delete.do',
                        queryParams:{appId:val.id},
                        success: function (data) {
                            data = $.parseJSON(data);
                            if (data.success) {
                                $.messager.alert("温馨提示", "操作成功", "info", function () {
                                    app_datagrid.datagrid("reload");
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
            app_dialog.dialog("close");
        },
        //刷新按钮
        reloadData: function () {
            app_datagrid.datagrid("load");
        }
    };

    $("a[data-btn]").click(function () {
        var btn = $(this).data("btn");
        methodObj[btn]();
    });


    app_datagrid.datagrid({
        fit: true,
        fitColumns: true,
        striped: true,
        url: '/joinApply/list.do',
        toolbar: '#app_toolbar',
        pagination: true,
        rownumbers: true,
        singleSelect: true,
        columns: [[
            {field: 'shopName', title: '店铺名称', width: 50},
            {field: 'tel', title: '联系电话', width: 50},
            {field: 'email', title: '邮箱', width: 50},
            {
                field: 'accountType', title: '开户类型', width: 50, formatter: function (value, row, index) {
                    return value ? "个人" : "企业";
                }
            },
            {field: 'bankName', title: '银行名称', width: 50},
            {field: 'accountName', title: '开户名称', width: 50}
        ]]
    });

    app_dialog.dialog({
        width: 320,
        height: 360,
        buttons: '#app_button',
        closed: true,
        onClose: function () {
            $("#app_form").form("clear");
        }
    });
});
