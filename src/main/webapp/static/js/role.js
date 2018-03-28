$(function () {

    var role_datagrid = $("#role_datagrid");
    var role_dialog = $("#role_dialog");
    var allPermission = $("#allPermission");
    var selfPermission = $("#selfPermission");

    var methodObj = {
        //新增按钮
        addData: function () {
            $("#password").show();
            role_dialog.dialog("setTitle", "新增");
            role_dialog.dialog("open").dialog("center");
        },

        //保存按钮
        save: function () {
            $("#role_form").form("submit", {
                url: '/role/saveOrUpdate.do',
                onSubmit:function (param) {
                    var rows = selfPermission.datagrid('getRows');
                    var ids = $.map(rows,function (val) {
                        return val.id;
                    });
                    param.ids = ids;
                },
                success: function (data) {
                    data = $.parseJSON(data);
                    if (data.success) {
                        $.messager.alert("温馨提示", "操作成功", "info", function () {
                            role_datagrid.datagrid("reload");
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
            var val = role_datagrid.datagrid("getSelected");
            if (!val) {
                $.messager.alert("温馨提示", "请选择一条数据", "info");
                return;
            }
            $.messager.confirm("温馨提示","确定要删除吗?",function (ret) {
                if (ret) {
                    $("#role_form").form("submit", {
                        url: '/role/delete.do',
                        queryParams:{roleId:val.id},
                        success: function (data) {
                            data = $.parseJSON(data);
                            if (data.success) {
                                $.messager.alert("温馨提示", "操作成功", "info", function () {
                                    role_datagrid.datagrid("reload");
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
            var val = role_datagrid.datagrid("getSelected");
            if (!val) {
                $.messager.alert("温馨提示", "请选择一条数据", "info");
                return;
            }
            selfPermission.datagrid('load',{id:val.id});
            var rows = allPermission.datagrid('getRows');
            selfPermission.datagrid({
                url:'/role/selectPermission.do',
                onLoadSuccess:function (data) {
                    var vals = $.map(data.rows,function (val) {
                        return val.id;
                    });
                    for(var index=rows.length-1;index>=0;index--){
                        var i = $.inArray(rows[index].id, vals);
                        if ( i >= 0) {
                            allPermission.datagrid('deleteRow',i);
                        }
                    }

                }
            });

            $("#role_form").form("load", val);
            role_dialog.dialog("setTitle", "编辑");
            role_dialog.dialog("open");
        },

        //取消按钮
        cancel: function () {
            role_dialog.dialog("close");
        },
        //刷新按钮
        reloadData: function () {
            role_datagrid.datagrid("load");
        }
    };

    $("a[data-btn]").click(function () {
        var btn = $(this).data("btn");
        methodObj[btn]();
    });


    role_datagrid.datagrid({
        fit: true,
        fitColumns: true,
        striped: true,
        url: '/role/list.do',
        toolbar: '#role_toolbar',
        pagination: true,
        rownumbers: true,
        singleSelect: true,
        columns: [[
            {field: 'id', title: '角色编号', width: 50},
            {field: 'sn', title: '角色编码', width: 50},
            {field: 'name', title: '角色名称', width: 50}
        ]]
    });

    role_dialog.dialog({
        width: 500,
        height: 400,
        buttons: '#role_button',
        align : 'center',
        closed: true,
        onClose: function () {
            $("#role_form").form("clear");

            allPermission.datagrid('load');
            selfPermission.datagrid('loadData',[]);
        }
    });
    allPermission.datagrid({
        width: 210,
        height: 270,
        url:'/permission/selectAll.do',
        striped: true,
        fitColumns: true,
        columns: [[
            {field: 'id', title: '所有权限', width: 50,align:'center',formatter:function (value,row,index) {
                    return row.name;
                }}
        ]],
        onSelect:function (index,row) {
            selfPermission.datagrid('appendRow',row);
            allPermission.datagrid('deleteRow',index);
        }
    });
    selfPermission.datagrid({
        width: 210,
        height: 270,
        striped: true,
        fitColumns: true,
        columns: [[
            {field: 'id', title: '已有权限', width: 50,align:'center',formatter:function (value,row,index) {
                    return row.name;
                }}
        ]],
        onSelect:function (index,row) {
            allPermission.datagrid('appendRow',row);
            selfPermission.datagrid('deleteRow',index);
        }
    });
});
