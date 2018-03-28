$(function () {

    var sub_datagrid = $("#sub_datagrid");
    var sub_dialog = $("#sub_dialog");

    var methodObj = {
        //新增按钮
        addData: function () {
            $("#password").show();
            sub_dialog.dialog("setTitle", "新增");
            sub_dialog.dialog("open");
        },
        //编辑按钮
        editData: function () {
            $("#password").hide();
            var val = sub_datagrid.datagrid("getSelected");
            if (!val) {
                $.messager.alert("温馨提示", "请选择一条数据", "info");
                return;
            }
            $("#sub_form").form("load", val);
            sub_dialog.dialog("setTitle", "编辑");
            sub_dialog.dialog("open");
        },

        //保存按钮
        save: function () {
            $("#sub_form").form("submit", {
                url: '/subBranch/saveOrUpdate.do',
                success: function (data) {
                    data = $.parseJSON(data);
                    if (data.success) {
                        $.messager.alert("温馨提示", "操作成功", "info", function () {
                            sub_datagrid.datagrid("reload");
                            methodObj.cancel();
                        })
                    } else {
                        $.messager.alert("温馨提示", data.msg, "info");
                        methodObj.cancel();
                    }
                }
            })
        },
        //取消按钮
        cancel: function () {
            sub_dialog.dialog("close");
        }
    };

    $("a[data-btn]").click(function () {
        var btn = $(this).data("btn");
        methodObj[btn]();
    });


    sub_datagrid.datagrid({
        fit: true,
        fitColumns: true,
        striped: true,
        url: '/subBranch/list.do',
        toolbar: '#sub_toolbar',
        pagination: true,
        rownumbers: true,
        singleSelect: true,
        columns: [[
            {field: 'shopName', title: '分店名称', width: 50},
            {field: 'intro', title: '分店简介', width: 50},
            {field: 'linkman', title: '联系人', width: 50},
            {field: 'tel', title: '电话', width: 50},
            {
                field: 'state', title: '店铺状态', width: 50, formatter: function (value, row, index) {
                    return value ? "启用" : "<font color='red'>停用</font>";
                }
            },
            {field: 'addr', title: '地址', width: 50},
            {field: 'joinDate', title: '注册时间', width: 50},
            {
                field: 'mallState', title: '微商城', width: 50,align:'center', formatter: function (value, row, index) {
                    if (value) {
                        var str = '<input name="opera" class="easyui-switchbutton" data-options="onText:\'开启\'" checked/>';
                        return str;
                    } else {
                        var str = '<input name="opera" class="easyui-switchbutton" data-options="offText:\'关闭\'"/>';
                        return str;
                    }
                }
            }
        ]],
        onLoadSuccess:function(data){
            $("input[name='opera']").switchbutton({
                onChange: function(checked){
                    var val = sub_datagrid.datagrid("getSelected");
                    console.log(val);
                    if (checked) {
                        $(this).switchbutton({
                            onText:'开启'
                        });
                    } else {
                        /*$.get('/subBranch/changeMallState.do',{id:shopId},function (data) {
                            if (data) {
                                $.messager.alert('温馨提示','分店微商城关闭成功','info');
                            } else {
                                $.messager.alert('温馨提示',data.msg,'info');
                            }
                        });*/
                        $(this).switchbutton({
                            offText:'关闭'
                        });
                    }
                }});
        }
    });
    sub_dialog.dialog({
        width: 320,
        height: 300,
        buttons: '#sub_button',
        closed: true,
        onClose: function () {
            $("#sub_form").form("clear");
        }
    });

});
