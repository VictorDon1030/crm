$(function () {

    var login_datagrid = $("#login_datagrid");
    var login_dialog = $("#login_dialog");

    var methodObj = {
        //刷新按钮
        reloadData: function () {
            login_datagrid.datagrid("load");
        },
        //高级查询
        searchs: function () {
            var keyword = $("#keyword").textbox("getValue");
            var beginDate = $("#beginDate").textbox("getValue");
            var endDate = $("#endDate").textbox("getValue");
            login_datagrid.datagrid("load", {
                keyword:keyword,
                beginDate:beginDate,
                endDate:endDate
            });
        }
    };

    $("a[data-btn]").click(function () {
        var btn = $(this).data("btn");
        methodObj[btn]();
    });


    login_datagrid.datagrid({
        fit: true,
        fitColumns: true,
        striped: true,
        url: '/loginLog/list.do',
        toolbar: '#login_toolbar',
        pagination: true,
        rownumbers: true,
        singleSelect: true,
        columns: [[
            {field: 'logName', title: '登录人', width: 50},
            {field: 'logIp', title: '登录IP', width: 50},
            {field: 'logTime', title: '登陆时间', width: 50}

        ]]
    });

    login_dialog.dialog({
        width: 320,
        height: 360,
        buttons: '#login_button',
        closed: true,
        onClose: function () {
            $("#login_form").form("clear");
        }
    });
});
