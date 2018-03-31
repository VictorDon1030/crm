function supplierFormatter(value, row, index) {
    return value ? value.realname : "";
};

function depotFormatter(value, row, index) {
    return value ? value.name : "";
};

function statusFormatter(value, row, index) {
    return value == 1 ? "<font color='green'>已入库</font>" : "<font color='red'>未入库</font>";
};


$(function () {
    var methodObj = {
        //新增打开窗口
        add: function () {
            $("#edit_tbody").html("");
            $("#orderBill_form").form('clear');
            $("#orderBill_dialog").dialog('open');
            $("#orderBill_dialog").dialog('setTitle', '新增订单');

        },
        //删除
        deleted: function () {
            var row = $('#orderBill_datagrid').datagrid('getSelected');
            if (row) {
                $.messager.confirm('确认', '确定删除该订单吗?', function (r) {
                    if (r) {
                        $.get('/orderBill/delete.do', {id: row.id}, function (data) {
                            if (data.success) {
                                $.messager.alert('温馨提示', '操作成功', 'info', function () {
                                    $("#orderBill_datagrid").datagrid('reload');

                                });
                            } else {
                                $.messager.alert('温馨提示', data.msg);
                            }
                        })
                    }
                });
            }
        },
        //新增商品
        productOpen: function () {
            $("#product_dialog").dialog('open');
            $("#product_dialog").dialog('setTitle', '选择商品');

        },
        //保存
        save: function () {
            $.each($("#edit_tbody tr"), function (index, item) {
                $(item).find("[tag='name']").prop("name", "items[" + index + "].product.name");
                $(item).find("[tag=pid]").prop("name", "items[" + index + "].product.id");
                $(item).find("[tag=costPrice]").prop("name", "items[" + index + "].costPrice");
                $(item).find("[tag=number]").prop("name", "items[" + index + "].number");
                $(item).find("[tag=remark]").prop("name", "items[" + index + "].remark");
            });
            $("#orderBill_form").form('submit', {
                url: '/orderBill/saveOrUpdate.do',
                success: function (data) {
                    data = $.parseJSON(data);
                    if (data.success) {
                        $.messager.alert('温馨提示', '保存成功', 'info', function () {
                            $("#orderBill_dialog").dialog("close");
                            $("#orderBill_datagrid").datagrid('reload');
                        })
                    }
                }
            })
        },
        //入库
        changeAudit: function () {
            //判断是否有选中数据
            var row = $('#orderBill_datagrid').datagrid('getSelected');
            if (!row) {
                $.messager.alert('温馨提示', '请选中一条数据!');
                return;
            }
            //弹出确认框
            $.messager.confirm('温馨提示', '您想要执行该操作吗?', function (y) {
                if (y) {
                    $.get("/orderBill/audit.do", {id: row.id}, function (data) {
                        if (data.success) {
                            $.messager.alert('温馨提示', '操作成功', 'info', function () {
                                $("#orderBill_datagrid").datagrid("reload");
                            });
                        } else {
                            $.messager.alert('温馨提示', data.msg, 'error');
                        }
                    }, "json")
                }
            });
        },
        //取消
        cancel: function () {
            $("#orderBill_dialog").dialog("close");
        },

        //取消选择商品
        cancelProduct: function () {
            $("#product_dialog").dialog("close");
        },


        //编辑
        edit: function () {
            var row = $('#orderBill_datagrid').datagrid('getSelected');
            if (!row) {
                $.messager.alert('温馨提示', '请选中一条数据!');
                return;
            }
            //清空表单数据
            $("#orderBill_form").form("clear");
            $("#edit_tbody").html("");

            if (row.supplier) {
                row["supplier.id"] = row.supplier.id;
            }
            if (row.depot) {
                row["depot.id"] = row.depot.id;
            }
            //回显数据
            $("#orderBill_form").form("load", row);
            $.each(row.items, function (index, item) {
                var copy = $("#itemTr tr:first").clone(true);
                copy.appendTo("#edit_tbody");
                copy.find("[tag=sn]").html(item.product.goodsMark);
                copy.find("[tag=name]").html(item.product.name);
                copy.find("[tag=pid]").val(item.product.id);
                copy.find("[tag=costPrice]").val(item.product.purchasingPrice);
                copy.find("[tag=amount]").html(item.amount);
                copy.find("[tag=remark]").val(item.remark);
                copy.find("[tag=number]").val(item.number);
                if (row.status) {
                    $(":input").prop('readonly',true);
                    $(".ser").hide();
                    $("#orderBill_dialog").dialog("setTitle", "查看明细");
                } else {
                    $(":input").prop("readonly", false);
                    $(".ser").show();
                    $("#orderBill_dialog").dialog("setTitle", "编辑订单");
                }
                $("#product_dialog").dialog("close");
            })
            //打开弹窗
            $("#orderBill_dialog").dialog("open");
        }


    };
    $("#orderBill_datagrid").datagrid({
        onClickRow: function (index, row) {
            if (!row.status) {

                $("#edit_btn").linkbutton("enable");
                $("#changeAudit_btn").linkbutton({
                    text: '审核订单'
                });
                $("#changeAudit_btn").linkbutton("enable");
            } else {
                $("#edit_btn").linkbutton("disable");
                $("#changeAudit_btn").linkbutton("disable");
            }
        },
        onDblClickRow: function (index, row) {
            methodObj['edit']();
        }
    });

    //商品列表
    $("#product_datagrid").datagrid({
        url: '/product/list.do',
        fit: true,
        singleSelect: true,
        fitColumns: true,
        pagination: true,
        columns: [[
            {field: 'ck', checkbox: true},
            {field: 'goodsMark', title: '商品编码', width: 50},
            {field: 'name', title: '商品名称', width: 50},
            {field: 'purchasingPrice', title: '参考进价', width: 50},
            {field: 'unit', title: '单位', width: 50}
        ]],
        onClickRow: function (index, row) {
            var copy = $("#itemTr tr:first").clone(true);
            copy.appendTo("#edit_tbody");
            copy.find("[tag=sn]").html(row.goodsMark);
            copy.find("[tag=pid]").val(row.id);
            copy.find("[tag=name]").html(row.name);
            copy.find("[tag=number]").val(1);
            copy.find("[tag=costPrice]").val(row.purchasingPrice);
            copy.find("[tag=amount]").html(row.purchasingPrice);
            $("#product_dialog").dialog("close");
        }
    });
    //采购新增弹框
    $("#orderBill_dialog").dialog({
        width: 750,
        height: 400,
        buttons: '#orderBillItem_button',
        toolbar: '#orderBillItem_toolbar',
        closed: true,
        onClose: function () {
            $("#orderBill_form").form('clear');
        }
    });
    //商品弹框
    $("#product_dialog").dialog({
        closed: true,
        width: 600,
        height: 400
    });
    $("#edit_tbody").on("change", "[tag=costPrice],[tag=number]", function () {
        var currentTr = $(this).closest('tr');
        var price = currentTr.find("[tag=costPrice]").val() || 0;
        var number = currentTr.find("[tag=number]").val() || 0;
        currentTr.find("[tag=amount]").html((price * number).toFixed(2));
    }).on("click", ".removeItem", function () {
        var currentTr = $(this).closest("tr");
        currentTr.remove();
    });
    //调用所有的方法
    $("a[data-cmd]").click(function () {
        var methodName = $(this).data('cmd');
        methodObj[methodName]();
    });
    $("#status").switchbutton({onChange:function (checked) {
        if (checked){
            $("#orderBill_datagrid").datagrid("load", {
                status: 1,
            });
        }else{
            $("#orderBill_datagrid").datagrid("load", {
                status: 0,
            });
        }
    }});

})
function searchs() {
    var beginDate = $("#beginDate").textbox("getValue");
    var endDate = $("#endDate").textbox("getValue");
    $("#orderBill_datagrid").datagrid("load", {
        beginDate:beginDate,
        endDate:endDate
    });
}

