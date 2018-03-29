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
function dueTime(value, row, index) {
    return row.product?row.product.pastDueTime:"" ;
};

function searchs() {
    var keyword = $("#keyword").textbox("getValue");
    var depotId = $("#depot").textbox("getValue");
    var warnNum = $("#warnNum").textbox("getValue");
    $("#productStock_datagrid").datagrid("load", {
        keyword: keyword,
        depotId:depotId,
        warnNum:warnNum
    });
}
