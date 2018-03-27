$(function () {

    var member_datagrid = $("#member_datagrid");
    var member_dialog = $("#member_dialog");
    var rPassword = $("#rPassword");

    var methodObj = {
        //新增按钮
        addData: function () {
            $("#password").show();
            member_dialog.dialog("setTitle", "新增");
            member_dialog.dialog("open");
        },

        //保存按钮
        save: function () {
            $("#member_form").form("submit", {
                url: '/member/saveOrUpdate.do',
                success: function (data) {
                    data = $.parseJSON(data);
                    if (data.success) {
                        $.messager.alert("温馨提示", "操作成功", "info", function () {
                            member_datagrid.datagrid("reload");
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
            var val = member_datagrid.datagrid("getSelected");
            if (!val) {
                $.messager.alert("温馨提示", "请选择一条数据", "info");
                return;
            }
            if (val.dept) {
                val["dept.id"] = val.dept.id;
            }

            $("#member_form").form("load", val);
            member_dialog.dialog("setTitle", "编辑");
            member_dialog.dialog("open");
        },

        //设置离职
        changeState: function () {
            var val = member_datagrid.datagrid("getSelected");
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
                    $.get("/member/changeState.do", {id: val.id}, function (data) {
                        if (data.success) {
                            $.messager.alert("温馨提示", "操作成功", "info");
                            member_datagrid.datagrid("reload");
                        }
                    });
                }
            })
        },

        //取消按钮
        cancel: function () {
            member_dialog.dialog("close");
            rPassword.dialog('close');
        },
        //刷新按钮
        reloadData: function () {
            member_datagrid.datagrid("load");
        },
        //高级查询
        searchs: function () {
            var keyword = $("#keyword").textbox("getValue");
            var deptId = $("#deptId").textbox("getValue");
            var beginDate = $("#beginDate").textbox("getValue");
            var endDate = $("#endDate").textbox("getValue");
            member_datagrid.datagrid("load", {
                keyword: keyword,
                deptId:deptId,
                beginDate:beginDate,
                endDate:endDate
            });
        }/*,
        changePassword:function () {
            var val = member_datagrid.datagrid("getSelected");
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


    member_datagrid.datagrid({
        fit: true,
        fitColumns: true,
        striped: true,
        url: '/member/list.do',
        toolbar: '#member_toolbar',
        pagination: true,
        rownumbers: true,
        singleSelect: true,
        columns: [[
            {field: 'memberNum', title: '会员编号', width: 50},
            {field: 'name', title: '会员名称', width: 50},
            {field: 'phone', title: '电话', width: 50},
            {field: 'gradeId', title: '等级', width: 50, formatter: function (value, row, index) {

                    return value ? "正常使用" : "<font color='red'>挂失状态</font>";
                }},
            {field: 'points', title: '积分', width: 50},
            {field: 'balance', title: '余额', width: 50},
            {field: 'shop', title: '所属店铺', width: 50},
            {field: 'come', title: '注来源', width: 50},
            {
                field: 'state', title: '状态', width: 50, formatter: function (value, row, index) {
                    return value ? "正常使用" : "<font color='red'>挂失状态</font>";
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




    member_dialog.dialog({
        width: 560,
        height: 360,
        buttons: '#member_button',
       // closed: true,
        onClose: function () {
            $("#member_form").form("clear");
        }
    });

   /* rPassword.dialog({
        title:'重置密码',
        width: 320,
        height: 300,
        buttons: '#member_button',
        closed: true
    });*/

});
