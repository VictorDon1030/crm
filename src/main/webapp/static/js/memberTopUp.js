$(function () {
    var member_load_datagrid = $("#member_load_datagrid");
    var member_loadData_form = $("#member_loadData_form");
    var name_textbox = $("#name_textbox");
    var grade_textbox = $("#grade_textbox");
    var birthday_textbox = $("#birthday_textbox");
    var balance_textbox = $("#balance_textbox");
    var points_textbox = $("#points_textbox");
    var id_hidden = $("#id_hidden");
    var ff = $("#ff");
    var btn = $("#btn");

    member_load_datagrid.datagrid({
        width: 400,
        height: 250,
        fitColumns: true,
        remoteSort: false,
        singleSelect: true,
        url: '/member/selectAll.do',
        columns: [[

            {field: 'name', title: '会员名', width: 80, align: 'center'},
            {
                field: 'grade.id', title: '会员等级', width: 80, align: 'center', formatter: function (index, row, value) {
                    return row.grade ? '<span style="color: #CC2222">' + row.grade.name + '</span>' : row.grade;
                }
            },
            {field: 'phone', title: '电话', width: 80, align: 'center'},
            {field: 'balance', title: '储值', width: 80, align: 'center'}
        ]],
        onClickRow: function (index, row) {
            console.log(row);

            name_textbox.textbox("setValue", row.name);
            points_textbox.textbox("setValue", row.points);
            balance_textbox.textbox("setValue", row.balance);
            birthday_textbox.textbox("setValue", row.birthday);
            grade_textbox.textbox("setValue", row.grade.name);
            birthday_textbox.textbox("setValue", row.birthday);
            id_hidden.val(row.id);
        }
    });
    member_loadData_form.form()


})