$(function () {
    $("#depot_datagrid").datagrid({
        url: '/depot/list.do',
        toolbar: '#depot_toolbar',
        fit: true,
        fitColumns: true,
        //striped:true,
        pagination: true,
        singleSelect: true,
        columns: [[
            {field: 'name', title: '仓库名称', width: 50},
            {field: 'sn', title: '仓库编码', width: 50},
            {
                field: 'employee', title: '联系人', width: 50, formatter: function (value, row, index) {
                return value ? value.realname : "";
            }
            },
            {
                field: 'tel', title: '联系电话', width: 50, formatter: function (value, row, index) {
                return row.employee ? row.employee.tel : "";
            }
            },
            {field: 'address', title: '仓库地址', width: 50},
            {field: 'vdate', title: '添加时间', width: 50},
            {
                field: 'state', title: '仓库状态', width: 50,
                formatter: function (value, row, index) {
                    return value ? "<font color='green'>开启</font>" : "<font color='red'>关闭</font>"
                }
            },
            {
                field: 'str1', title: '操作', width: 60,
                formatter: function (value, row, index) {
                    var str1 = '<a href="#" name="str1" onclick="deleted()" class="easyui-linkbutton button-red" ></a>';
                    var str2 = '<a href="#" name="str2" onclick="openpro_dialog('+row.id+')" class="easyui-linkbutton button-olive" ></a>';
                    if (row.state){
                        return str1 + str2;
                    }else{
                        return str1;
                    }
                }
            }

        ]],
        onLoadSuccess: function (data) {
            $("a[name='str1']").linkbutton({text: '删除', plain: true,});
            $("a[name='str2']").linkbutton({text: '查看库存', plain: true});


        },
        onClickRow: function (index, row) {
            if (row.state) {
                $("#changeState").linkbutton({
                    text: "关闭仓库"
                });
            } else {
                $("#changeState").linkbutton({
                    text: "开启仓库"
                });
            }
        }
    });
    $("#depot_dialog").dialog({
        width: 350,
        height: 350,
        buttons: '#depot_button',
        closed: true,
        onClose: function () {
            $("#depot_form").form('clear');
        }
    });
    $("#state").switchbutton({onChange:function (checked) {
        if (checked){
            $("#depot_datagrid").datagrid("load", {
                state: true,
            });
        }else{
            $("#depot_datagrid").datagrid("load", {
                state: false,
            });
        }
    }});
    $("#pro_dialog").dialog({
        width: 700,
        height: 400,
        closed: true,
        title:"查看库存",
        toolbar:'#pro_toolbar'
    })

})
//查看库存
function openpro_dialog(id) {
    $("#pro_dialog").dialog('open').dialog('center');
    $("#pro_datagrid").datagrid({
        url: '/productStock/selectProductStockByDepotId.do?depot_stockId='+id,
        fit: true,
        fitColumns: true,
        //striped:true,
        pagination: true,
        singleSelect: true,
        columns: [[
            {field: 'name', title: '仓库名称', width: 50,formatter:function (value,row,index) {
                return row.depot ?row.depot.name:'';
            }},
            {field: 'goodsMark', title: '商品编码', width: 50,formatter:function (value,row,index) {
                return row.product?row.product.goodsMark:'';
            }},
            {
                field: 'productName', title: '商品名称', width: 50, formatter: function (value, row, index) {
                return row.product ? row.product.name : "";
            }
            },
            {field: 'storeNumber', title: '数量', width: 50},
            {field: 'price', title: '单价', width: 50},
            {field: 'amount', title: '金额', width: 50}
        ]]
    })
}





//更改状态
function changeStatus() {
    var row = $("#depot_datagrid").datagrid('getSelected');
    if (!row) {
        $.messager.alert('温馨提示', '请选中一条数据');
        return;
    }
    if(row.state){
        $.messager.confirm('确认', '确定设置为停用吗?', function (r) {
            if (r) {
                $.get('/depot/changeState.do', {id: row.id}, function (data) {
                    if (data.success) {
                        $.messager.alert('温馨提示', '操作成功', 'info', function () {
                            $("#depot_datagrid").datagrid('reload');
                            $("#status").switchbutton({checked: false});
                        });
                    } else {
                        $.messager.alert('温馨提示', data.msg);
                    }
                })
            }
        });
    }else{

      $.messager.confirm('确认', '确定重新启用吗?', function (r) {
            if (r) {
                $.get('/depot/changeState.do', {id: row.id}, function (data) {
                    if (data.success) {
                        $.messager.alert('温馨提示', '操作成功', 'info', function () {
                            $("#depot_datagrid").datagrid('reload');

                        });
                    } else {
                        $.messager.alert('温馨提示', data.msg);
                    }
                })
            }
        });
    }


}

//删除仓库
function deleted() {
    var row = $('#depot_datagrid').datagrid('getSelected');
    if (row) {
        $.messager.confirm('确认', '确定删除仓库吗?', function (r) {
            if (r) {
                $.get('/depot/delete.do', {id: row.id}, function (data) {
                    if (data.success) {
                        $.messager.alert('温馨提示', '操作成功', 'info', function () {
                            $("#depot_datagrid").datagrid('reload');

                        });
                    } else {
                        $.messager.alert('温馨提示', data.msg);
                    }
                })
            }
        });
    }
}

//新增
function add() {
    $("#depot_dialog").dialog('open').dialog('center');
    $("#edit_state").switchbutton({checked:true});
    $("#depot_dialog").dialog('setTitle', '新增仓库');

}

//编辑
function edit() {
    var row = $("#depot_datagrid").datagrid('getSelected');
    if (!row) {
        $.messager.alert('温馨提示', '请选中一条数据');
        return;
    }
    if (row.employee) {
        row["employee.id"] = row.employee.id;
        row["employee.tel"] = row.employee.tel;
    }
    $("#depot_form").form('load', row);
    $("#depot_dialog").dialog('open');
    if (row.state){
        $("#edit_state").switchbutton({checked:true});
    }else{
        $("#edit_state").switchbutton({checked:false});

    }
    $("#depot_dialog").dialog('setTitle', '编辑仓库');
}
//查看库存高级查询
function searchs() {
    var keyword = $("#pro_search").textbox("getValue");
    $("#pro_datagrid").datagrid("load", {
        keyword: keyword
    });
}

//保存
function save() {
    $("#depot_form").form('submit', {
        url: '/depot/saveOrUpdate.do',
        success: function (data) {
            data = $.parseJSON(data);
            if (data.success) {
                $.messager.alert('温馨提示', '保存成功', 'info', function () {
                    cancel();
                    $("#depot_datagrid").datagrid('reload');
                })
            }
        }
    })
}

//取消
function cancel() {
    $("#depot_dialog").dialog("close");
}

//刷新
function reload() {
    $("#depot_datagrid").datagrid('load');
}
