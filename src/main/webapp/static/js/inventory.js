//格式化
function mark(value, row, index) {
    console.log(row.product);
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
    var str2 = '<a href="#" name="str2" onclick="dept()" class="easyui-linkbutton button-line-green" ></a>';
    var str3 = '<a href="#" name="str3" onclick="dept()" class="easyui-linkbutton button-line-green" ></a>';
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

$(function () {
    $("#productStock_datagrid").datagrid({
        onLoadSuccess:function(data){
            $("a[name='str1']").linkbutton({text:'数量确认',plain:true,});
            $("a[name='str2']").linkbutton({text:'数量调整',plain:true});
            $("a[name='str3']").linkbutton({text:'盘点记录',plain:true});
        }
    })
})