$(function () {

    var emp_datagrid = $("#emp_datagrid");
    var emp_dialog = $("#emp_dialog");
    var rPassword = $("#rPassword");

    var methodObj = {
        //新增按钮
        addData: function () {
            $("#password").show();
            emp_dialog.dialog("setTitle", "新增");
            emp_dialog.dialog("open").dialog('center');
        },

        //保存按钮
        save: function () {
            $("#emp_form").form("submit", {
                url: '/employee/saveOrUpdate.do',
                onSubmit:function (param) {
                  var roles = $("#roles_combobox").combobox('getValues');
                    param.ids = roles;
                },
                success: function (data) {
                    data = $.parseJSON(data);
                    if (data.success) {
                        $.messager.alert("温馨提示", "操作成功", "info", function () {
                            emp_datagrid.datagrid("reload");
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
            $("#password").hide();
            var val = emp_datagrid.datagrid("getSelected");
            if (!val) {
                $.messager.alert("温馨提示", "请选择一条数据", "info");
                return;
            }
            if (val.dept) {
                val["dept.id"] = val.dept.id;
            }
            $.get("/employee/selectRoleByEmpId.do",{id:val.id},function (data) {
                $("#roles_combobox").combobox('setValues',data);
            });

            $("#emp_form").form("load", val);
            emp_dialog.dialog("setTitle", "编辑");
            emp_dialog.dialog("open");
        },

        //设置离职
        changeState: function () {
            var val = emp_datagrid.datagrid("getSelected");
            var status = "";
            if (!val) {
                $.messager.alert("温馨提示", "请选择一条数据", "info");
                return;
            }
            if (val.state) {
                status = "确定设置离职吗?";
            } else {
                status = "确定设置复职吗?";
            }
            $.messager.confirm("温馨提示", status, function (ret) {
                if (ret) {
                    $.get("/employee/changeState.do", {id: val.id}, function (data) {
                        if (data.success) {
                            $.messager.alert("温馨提示", "操作成功", "info");
                            emp_datagrid.datagrid("reload");
                        }
                    });
                }
            })
        },

        //取消按钮
        cancel: function () {
            emp_dialog.dialog("close");
            rPassword.dialog('close');
        },
        //刷新按钮
        reloadData: function () {
            emp_datagrid.datagrid("load");
        },
        //高级查询
        searchs: function () {
            var keyword = $("#keyword").textbox("getValue");
            var deptId = $("#deptId").textbox("getValue");
            var beginDate = $("#beginDate").textbox("getValue");
            var endDate = $("#endDate").textbox("getValue");
            emp_datagrid.datagrid("load", {
                keyword: keyword,
                deptId:deptId,
                beginDate:beginDate,
                endDate:endDate
            });
        }/*,
        changePassword:function () {
            var val = emp_datagrid.datagrid("getSelected");
            if (!val) {
                $.messager.alert("温馨提示", "请选择一条数据", "info");
                return;
            }
            rPassword.dialog("open");
        }*/
    };

    $("a[data-btn]").click(function () {
        var btn = $(this).data("btn");
        methodObj[btn]();
    });


    emp_datagrid.datagrid({
        fit: true,
        fitColumns: true,
        striped: true,
        url: '/employee/list.do',
        toolbar: '#emp_toolbar',
        pagination: true,
        rownumbers: true,
        singleSelect: true,
        columns: [[
            {field: 'id', title: '编号', width: 50},
            {field: 'username', title: '用户名', width: 50},
            {field: 'realname', title: '真实姓名', width: 50},
            {field: 'tel', title: '电话', width: 50},
            {field: 'email', title: '邮箱', width: 50},
            {field: 'hireDate', title: '入职时间', width: 50},
            {
                field: 'state', title: '状态', width: 50, formatter: function (value, row, index) {
                    return value ? "在职" : "<font color='red'>离职</font>";
                }
            },
            {
                field: 'dept.id', title: '部门', width: 50, formatter: function (value, row, index) {
                    if (row.dept) {
                        return row ? row.dept.name : "";
                    }
                }
            },
            {
                field: 'admin', title: '管理员', width: 50, formatter: function (value, row, index) {
                    return value ? "是" : "否";
                }
            }
        ]],
        onClickRow: function (index, row) {
            if (row.state) {
                $("#changeState").linkbutton({
                    text: "设置离职"
                });
            } else {
                $("#changeState").linkbutton({
                    text: "设置复职"
                });
            }
        }
    });




    emp_dialog.dialog({
        width: 320,
        height: 360,
        buttons: '#emp_button',
        closed: true,
        onClose: function () {
            $("#emp_form").form("clear");
        }
    });

   /* rPassword.dialog({
        title:'重置密码',
        width: 320,
        height: 300,
        buttons: '#emp_button',
        closed: true
    });*/

});
