$(function () {
    var memberSimpleInfo = $("#memberSimpleInfo");
    var memberInfo = $("#memberInfo");
    memberSimpleInfo.datagrid({
        url: '/member/selectAll.do',
        columns: [[
            {field: 'memberNum', width: 120, align: 'center', title: '会员卡号'},
            {field: 'name', width: 110, align: 'center', title: '会员姓名'},
            {
                field: 'gradeId', width: 87, align: 'center', title: '会员等级', formatter: function (gradeId) {
            }
            },
            {field: 'phone', width: 200, align: 'center', title: '电话'},
            {field: 'balance', width: 100, align: 'center', title: '余额'}
        ]],
        onClickRow: function (index, row) {
            console.log(row);
            $("#memberName").html(row.name);
            $("#memberNum").html(row.memberNum);
            $("#grade").html(row.gradeId);
            $("#points").html(row.points);
            $("#balance").html(row.balance);
        }
    });
    memberInfo.datagrid({
        url: '/member/selectAll.do',
        columns: [[
            {field: 'memberNum', width: 120, align: 'center', title: '会员卡号'},
            {field: 'name', width: 110, align: 'center', title: '会员姓名'},
            {
                field: 'gradeId', width: 87, align: 'center', title: '会员等级', formatter: function (gradeId) {
            }
            },
            {field: 'phone', width: 200, align: 'center', title: '电话'},
            {field: 'balance', width: 100, align: 'center', title: '余额'}
        ]],
        onClickRow: function (index, row) {
            console.log(row);
            $("#memberName").html(row.name);
            $("#memberNum").html(row.memberNum);
            $("#grade").html(row.gradeId);
            $("#points").html(row.points);
            $("#balance").html(row.balance);
        }
    });


});