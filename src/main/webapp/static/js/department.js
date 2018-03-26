$(function () {

    var dept_datagrid = $("#dept_datagrid");
    var dept_dialog = $("#dept_dialog");

    var methodObj = {
        //新增按钮
        addData: function () {
            $("#password").show();
            dept_dialog.dialog("setTitle", "新增");
            dept_dialog.dialog("open");
        },

        //保存按钮
        save: function () {
            $("#dept_form").form("submit", {
                url: '/department/saveOrUpdate.do',
                success: function (data) {
                    data = $.parseJSON(data);
                    if (data.success) {
                        $.messager.alert("温馨提示", "操作成功", "info", function () {
                            dept_datagrid.datagrid("reload");
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
            var val = dept_datagrid.datagrid("getSelected");
            if (!val) {
                $.messager.alert("温馨提示", "请选择一条数据", "info");
                return;
            }
            if (val.dept) {
                val["dept.id"] = val.dept.id;
            }
            $("#dept_form").form("load", val);
            dept_dialog.dialog("setTitle", "编辑");
            dept_dialog.dialog("open");
        },

        //设置离职
        changeState: function () {
            var val = dept_datagrid.datagrid("getSelected");
            var status = "";
            if (!val) {
                $.messager.alert("温馨提示", "请选择一条数据", "info");
                return;
            }
            if (val.state) {
                status = "确定设置停用吗?";
            } else {
                status = "确定设置使用吗?";
            }
            $.messager.confirm("温馨提示", status, function (ret) {
                if (ret) {
                    $.get("/department/changeState.do", {id: val.id}, function (data) {
                        if (data.success) {
                            $.messager.alert("温馨提示", "操作成功", "info");
                            dept_datagrid.datagrid("reload");
                        }
                    });
                }
            })
        },

        //取消按钮
        cancel: function () {
            dept_dialog.dialog("close");
        },
        //刷新按钮
        reloadData: function () {
            dept_datagrid.datagrid("load");
        }
    };

    $("a[data-btn]").click(function () {
        var btn = $(this).data("btn");
        methodObj[btn]();
    });


    dept_datagrid.datagrid({
        fit: true,
        fitColumns: true,
        striped: true,
        url: '/department/list.do',
        toolbar: '#dept_toolbar',
        pagination: true,
        rownumbers: true,
        singleSelect: true,
        columns: [[
            {field: 'id', title: '编号', width: 50},
            {field: 'sn', title: '部门编码', width: 50},
            {field: 'name', title: '部门名称', width: 50},
            {
                field: 'state', title: '状态', width: 50, formatter: function (value, row, index) {
                    return value ? "使用" : "<font color='red'>停用</font>";
                }
            }
        ]],
        onClickRow: function (index, row) {
            if (row.state) {
                $("#changeState").linkbutton({
                    text: "设置停用"
                });
            } else {
                $("#changeState").linkbutton({
                    text: "设置使用"
                });
            }
        }
    });

    dept_dialog.dialog({
        width: 320,
        height: 360,
        buttons: '#dept_button',
        closed: true,
        onClose: function () {
            $("#dept_form").form("clear");
        }
    });
});
