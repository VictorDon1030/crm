$(function () {

    var manager_datagrid = $("#manager_datagrid");
    var manager_dialog = $("#manager_dialog");

    var methodObj = {
        //新增按钮
        addData: function () {
            $("#password").show();
            manager_dialog.dialog("setTitle", "新增");
            manager_dialog.dialog("open").dialog("center");
        },

        //保存按钮
        save: function () {
            $("#manager_form").form("submit", {
                url: '/managerMan/saveOrUpdate.do',
                success: function (data) {
                    data = $.parseJSON(data);
                    if (data.success) {
                        $.messager.alert("温馨提示", "操作成功", "info", function () {
                            manager_datagrid.datagrid("reload");
                            methodObj.cancel();
                        })
                    } else {
                        $.messager.alert("温馨提示", data.msg, "info");
                        methodObj.cancel();
                    }
                }
            })
        },
        //删除按钮
        deleteData :function () {
            var val = manager_datagrid.datagrid("getSelected");
            if (!val) {
                $.messager.alert("温馨提示", "请选择一条数据", "info");
                return;
            }
            $.messager.confirm("温馨提示","确定要删除吗?",function (ret) {
                if (ret) {
                    $("#manager_form").form("submit", {
                        url: '/managerMan/delete.do',
                        queryParams:{mId:val.id},
                        success: function (data) {
                            data = $.parseJSON(data);
                            if (data.success) {
                                $.messager.alert("温馨提示", "操作成功", "info", function () {
                                    manager_datagrid.datagrid("reload");
                                })
                            } else {
                                $.messager.alert("温馨提示", data.msg, "info");
                            }
                        }

                    })
                }
            });
        },
        //编辑按钮
        editData: function () {
            $("#password").hide();
            var val = manager_datagrid.datagrid("getSelected");
            if (!val) {
                $.messager.alert("温馨提示", "请选择一条数据", "info");
                return;
            }
            $("#manager_form").form("load", val);
            manager_dialog.dialog("setTitle", "编辑");
            manager_dialog.dialog("open");
        },

        //取消按钮
        cancel: function () {
            manager_dialog.dialog("close");
        },
        //刷新按钮
        reloadData: function () {
            manager_datagrid.datagrid("load");
        },
        //高级查询
        searchs: function () {
            var roleId = $("#roleId").textbox("getValue");
            var beginDate = $("#beginDate").textbox("getValue");
            var endDate = $("#endDate").textbox("getValue");
            manager_datagrid.datagrid("load", {
                roleId:roleId,
                beginDate:beginDate,
                endDate:endDate
            });
        }
    };

    $("a[data-btn]").click(function () {
        var btn = $(this).data("btn");
        methodObj[btn]();
    });


    manager_datagrid.datagrid({
        fit: true,
        fitColumns: true,
        striped: true,
        url: '/managerMan/list.do',
        toolbar: '#manager_toolbar',
        pagination: true,
        rownumbers: true,
        singleSelect: true,
        columns: [[
            {field: 'name', title: '姓名', width: 50},
            {field: 'role.id', title: '职位', width: 50,formatter:function (value,row,index) {
                    return row.role ? row.role.name : '';
                }},
            {field: 'hireDate', title: '入职时间', width: 50}
        ]]
    });

    manager_dialog.dialog({
        width: 300,
        height: 200,
        buttons: '#manager_button',
        align : 'center',
        closed: true,
        onClose: function () {
            $("#manager_form").form("clear");
        }
    });
});
