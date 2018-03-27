$(function () {
    var memberSimpleInfo = $("#memberSimpleInfo");


    memberSimpleInfo.datagrid({
        onClickRow:function (index,row) {
            console.log(row);
            $("#memberName").html(row.a);
        }
    });



});