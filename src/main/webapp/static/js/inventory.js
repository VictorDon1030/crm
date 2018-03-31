//格式化
function mark(value, row, index) {
    return row.product? row.product.goodsMark: "";
};
function depotFormatter(value, row, index) {
    return value ? value.name : "";
};
function name(value, row, index) {
    return row.product?row.product.name:"" ;
};
function specification(value, row, index) {
    return row.product?row.product.specification:"" ;
};
function handle(value, row, index) {
    var str1 = '<a href="#" name="str1" onclick="affirm('+row.id+')" class="easyui-linkbutton  button-line-olive" ></a>';
    var str2 = '<a href="#" name="str2" onclick="edit('+index+')" class="easyui-linkbutton button-line-green" ></a>';
    var str3 = '<a href="#" name="str3" onclick="listAll('+index+')" class="easyui-linkbutton button-line-green" ></a>';
    return str1+str2+str3;
};
//高级查询
function searchs() {
    var keyword = $("#keyword").textbox("getValue");
    var depotId = $("#depot").textbox("getValue");
    var warnNum = $("#warnNum").textbox("getValue");
    var beginDate = $("#beginDate").textbox("getValue");
    var endDate = $("#endDate").textbox("getValue");
    $("#productStock_datagrid").datagrid("load", {
        keyword: keyword,
        depotId:depotId,
        warnNum:warnNum,
        beginDate:beginDate,
        endDate:endDate
    });
}
//数量确认
function affirm(id) {
    $.messager.confirm('温馨提示', '您已经确定仓库数据一致了吗?', function (y) {
        if (y) {
            $.get("/productStock/inventoryAffirm.do", {id: id}, function (data) {
                if (data.success) {
                    $.messager.alert('温馨提示', '操作成功', 'info', function () {
                        $("#productStock_datagrid").datagrid("reload");
                    });
                } else {
                    $.messager.alert('温馨提示', data.msg, 'error');
                }
            }, "json")
        }
    });
}
//数量修改
function edit(index) {
    $('#productStock_datagrid').datagrid('selectRow',index);
    $("#productStock_dialog").dialog('open').dialog('center');
    var row = $('#productStock_datagrid').datagrid('getSelected');
    if (row.product) {
        row["product.name"] = row.product.name;
    }
    $("#productStock_form").form("load", row);
}
//保存
function save() {
    $("#productStock_form").form('submit', {
        url: '/productStock/inventoryNewNumber.do',
        success: function (data) {
            data = $.parseJSON(data);
            if (data.success) {
                $.messager.alert('温馨提示', '保存成功', 'info', function () {
                    $("#productStock_dialog").dialog("close");
                    $("#productStock_datagrid").datagrid('reload');
                })
            }
        }
    })
}
//取消
function cancel() {
    $("#productStock_dialog").dialog("close");
}

//查看记录
function listAll(index) {
    $('#productStock_datagrid').datagrid('selectRow',index);
    $("#inventory_dialog").dialog('open').dialog('center')
    var row = $('#productStock_datagrid').datagrid('getSelected');
    $("#inventory_datagrid").datagrid({
        url: '/productStock/listAll.do?productStockId='+row.id,
        fit: true,
        singleSelect: true,
        fitColumns: true,
        pagination: true,
        rownumbers:true,
        columns: [[
            {field: 'storeNumber', title: '原有库存', width: 50},
            {field: 'newNumber', title: '修改数量', width: 50},
            {field: 'employee', title: '操作人员', width: 50,formatter:function (value, row, index) {
                return row.employee ? row.employee.realname :'';
            }},
            {field: 'inventoryTime', title: '盘点时间', width: 50}
        ]],
    })

}










$(function () {
    $("#productStock_datagrid").datagrid({
        onLoadSuccess:function(data){
            $("a[name='str1']").linkbutton({text:'数量确认',plain:true,});
            $("a[name='str2']").linkbutton({text:'数量调整',plain:true});
            $("a[name='str3']").linkbutton({text:'盘点记录',plain:true});
        }
    });
    $("#productStock_dialog").dialog({
        closed: true,
        width: 400,
        height: 250,
        title:'数量调整',
        buttons:'#productStock_button',
        onClose: function () {
            $("#productStock_form").form('clear');
        }
    });
    $("#inventory_dialog").dialog({
        closed: true,
        width: 700,
        height: 400,
        title:'盘点记录'
    });

})