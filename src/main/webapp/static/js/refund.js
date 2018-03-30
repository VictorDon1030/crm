function supplierFormatter(value, row, index) {
    return value ? value.realname : "";
};

function depotFormatter(value, row, index) {
    return value ? value.name : "";
};

function statusFormatter(value, row, index) {
    return value == 1 ? "<font color='green'>已退货</font>" : "<font color='red'>退货中</font>";
};


$(function () {
    var methodObj = {
        //新增打开窗口
        add: function () {
            $("#edit_tbody").html("");
            $("#refund_form").form('clear');
            $("#refund_dialog").dialog('open');
            $("#refund_dialog").dialog('setTitle', '新增退货');

        },
        //删除
        deleted: function () {
            var row = $('#refund_datagrid').datagrid('getSelected');
            if (row) {
                $.messager.confirm('确认', '确定删除该退货单吗?', function (r) {
                    if (r) {
                        $.get('/refund/delete.do', {id: row.id}, function (data) {
                            if (data.success) {
                                $.messager.alert('温馨提示', '操作成功', 'info', function () {
                                    $("#refund_datagrid").datagrid('reload');

                                });
                            } else {
                                $.messager.alert('温馨提示', data.msg);
                            }
                        })
                    }
                });
            }
        },
        //新增采购
        productOpen: function () {
            $("#edit_tbody").html("");
            $("#product_dialog").dialog('open');
            $("#product_dialog").dialog('setTitle', '选择采购单');

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
            $("#refund_form").form('submit', {
                url: '/refund/saveOrUpdate.do',
                success: function (data) {
                    data = $.parseJSON(data);
                    if (data.success) {
                        $.messager.alert('温馨提示', '保存成功', 'info', function () {
                            $("#refund_dialog").dialog("close");
                            $("#refund_datagrid").datagrid('reload');
                        })
                    }
                }
            })
        },
        //入库
        changeAudit: function () {
            //判断是否有选中数据
            var row = $('#refund_datagrid').datagrid('getSelected');
            if (!row) {
                $.messager.alert('温馨提示', '请选中一条数据!');
                return;
            }
            //弹出确认框
            $.messager.confirm('温馨提示', '您想要执行该操作吗?', function (y) {
                if (y) {
                    $.get("/refund/audit.do", {id: row.id}, function (data) {
                        if (data.success) {
                            $.messager.alert('温馨提示', '操作成功', 'info', function () {
                                $("#refund_datagrid").datagrid("reload");
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
            $("#refund_dialog").dialog("close");
        },

        //取消选择商品
        cancelProduct: function () {
            $("#product_dialog").dialog("close");
        },


        //编辑
        edit: function () {
            var row = $('#refund_datagrid').datagrid('getSelected');
            if (!row) {
                $.messager.alert('温馨提示', '请选中一条数据!');
                return;
            }
            //清空表单数据
            $("#refund_form").form("clear");
            $("#edit_tbody").html("");

            if (row.supplier) {
                row["supplier.id"] = row.supplier.id;
            }
            if (row.depot) {
                row["depot.id"] = row.depot.id;
            }
            //回显数据
            $("#refund_form").form("load", row);
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
                if (row.status==1) {
                    $(":input").prop('readonly',true);
                    $(".ser").hide();
                    $("#refund_dialog").dialog("setTitle", "查看明细");
                } else {
                    $(":input").prop("readonly", false);
                    $(".ser").show();
                    $("#refund_dialog").dialog("setTitle", "编辑退货订单");
                }
                $("#product_dialog").dialog("close");
            })
            //打开弹窗
            $("#refund_dialog").dialog("open");
        }


    };
    $("#refund_datagrid").datagrid({
        onClickRow: function (index, row) {
            if (!row.status) {
                $("#edit_btn").linkbutton("enable");
                $("#changeAudit_btn").linkbutton({
                    text: '审核退货'
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

    //采购订单列表
    $("#product_datagrid").datagrid({
        url: '/orderBill/selectAll.do',
        fit: true,
        singleSelect: true,
        fitColumns: true,
        pagination: true,
        columns: [[
            {field: 'ck', checkbox: true},
            {field: 'sn', title: '订单编号', width: 50},
            {field: 'supplierName', title: '订单来源', width: 50,
                formatter: function (value, row, index) {
                    return row.supplier ? row.supplier.realname : "";
            }},
            {field: 'totalAmount', title: '已付金额', width: 50},
            {field: 'status', title: '入库状态', width: 50,
                formatter: function (value, row, index) {
                    return value ? "<font color='green'>已入库</font>" : "<font color='red'>未入库</font>"
                }},
            {field: 'inputTime', title: '录入时间', width: 50}
        ]],
        onClickRow: function (index, row) {
            $.each(row.items,function (index,item) {
                var copy = $("#itemTr tr:first").clone(true);
                copy.appendTo("#edit_tbody");
                copy.find("[tag=sn]").html(item.product.goodsMark);
                copy.find("[tag=pid]").val(item.product.id);
                copy.find("[tag=name]").html(item.product.name);
                copy.find("[tag=number]").val(item.number);
                copy.find("[tag=costPrice]").val(item.product.purchasingPrice);
                copy.find("[tag=amount]").html(item.amount);
            })
                $("#product_dialog").dialog("close");
        }
    });
    //采购新增弹框
    $("#refund_dialog").dialog({
        width: 750,
        height: 400,
        buttons: '#refundItem_button',
        toolbar: '#refundItem_toolbar',
        closed: true,
        onClose: function () {
            $("#refund_form").form('clear');
        }
    });
    //商品弹框
    $("#product_dialog").dialog({
        closed: true,
        width: 800,
        height: 400,
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
            $("#refund_datagrid").datagrid("load", {
                status: 1,
            });
        }else{
            $("#refund_datagrid").datagrid("load", {
                status: 0,
            });
        }
    }});

})
function searchs() {
    var beginDate = $("#beginDate").textbox("getValue");
    var endDate = $("#endDate").textbox("getValue");
    $("#refund_datagrid").datagrid("load", {
        beginDate:beginDate,
        endDate:endDate
    });
}

