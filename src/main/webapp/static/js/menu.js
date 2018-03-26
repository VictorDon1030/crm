$(function () {

    var menu_datagrid = $("#menu_datagrid");
    var menu_dialog = $("#menu_dialog");

    var methodObj = {
        //新增按钮
        addData: function () {
            var obj = menu_datagrid.datagrid("getRows");
            var text = '';
            var rootMenu = '';
            for (var i in obj){
                if (obj[i].parent) {
                    rootMenu = obj[i].parent;
                    text = obj[0].parent.text;
                }
            }
            if (rootMenu) {
                rootMenu["parent.id"] = rootMenu.id;
                rootMenu["id"] = '';
                rootMenu["text"] = '';
            $("#menu_form").form("load", rootMenu);
            }
            $("#rootMenu").textbox('setText',text);

            menu_dialog.dialog("setTitle", "新增");
            menu_dialog.dialog("open");
        },

        //保存按钮
        save: function () {
            $("#menu_form").form("submit", {
                url: '/menu/saveOrUpdate.do',
                success: function (data) {
                    data = $.parseJSON(data);
                    if (data.success) {
                        $.messager.alert("温馨提示", "操作成功", "info", function () {
                            menu_datagrid.datagrid("reload");
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
            var val = menu_datagrid.datagrid("getSelected");
            if (!val) {
                $.messager.alert("温馨提示", "请选择一条数据", "info");
                return;
            }
            var text = '';
            if (val.parent) {
                val["parent.id"] = val.parent.id;
                text = val.parent.text;
            }
            $("#menu_form").form("load", val);
            $("#rootMenu").textbox('setText',text);
            menu_dialog.dialog("setTitle", "编辑");
            menu_dialog.dialog("open");
        },
        //删除按钮
        deleteData :function () {
            var val = menu_datagrid.datagrid("getSelected");
            if (!val) {
                $.messager.alert("温馨提示", "请选择一条数据", "info");
                return;
            }
            $.messager.confirm("温馨提示","确定要删除吗?",function (ret) {
                if (ret) {
                    $("#menu_form").form("submit", {
                        url: '/menu/delete.do',
                        queryParams:{parentId:val.id},
                        success: function (data) {
                            data = $.parseJSON(data);
                            if (data.success) {
                                $.messager.alert("温馨提示", "操作成功", "info", function () {
                                    menu_datagrid.datagrid("reload");
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
            menu_dialog.dialog("close");
        },
        //刷新按钮
        reloadData: function () {
            menu_datagrid.datagrid("load");
        },
        //跳转子菜单
        loadChildMenu:function () {
            var val = menu_datagrid.datagrid("getSelected");
            if (!val) {
                $.messager.alert("温馨提示", "请选择父菜单", "info");
                return;
            }
            menu_datagrid.datagrid('load',{parentId:val.id});
            if (val.parent){
                methodObj.loadParentMenu();
            }
        },
        //跳转父菜单
        loadParentMenu:function () {
            menu_datagrid.datagrid('load',{parentId:-1});
        }
    };

    $("a[data-btn]").click(function () {
        var btn = $(this).data("btn");
        methodObj[btn]();
    });


    menu_datagrid.datagrid({
        fit: true,
        fitColumns: true,
        striped: true,
        url: '/menu/list.do',
        toolbar: '#menu_toolbar',
        rownumbers: true,
        singleSelect: true,
        columns: [[
            {field: 'id', title: '编号', width: 50},
            {field: 'text', title: '菜单名称', width: 50},
            {field: 'url', title: 'URL', width: 50},
            {field: 'iconCls', title: '图像', width: 50},
            {field: 'parent.id', title: '父菜单', width: 50, formatter: function (value, row, index) {
                    return row.parent ? row.parent.text : '';
                }
            }
        ]]
    });

    menu_dialog.dialog({
        width: 320,
        height: 360,
        buttons: '#menu_button',
        closed: true,
        onClose: function () {
            $("#menu_form").form("clear");
        }
    });
});
