$(function () {

    var log_datagrid = $("#log_datagrid");
    var log_dialog = $("#log_dialog");

    var methodObj = {
        //刷新按钮
        reloadData: function () {
            log_datagrid.datagrid("load");
        },
        //高级查询
        searchs: function () {
            var beginDate = $("#beginDate").textbox("getValue");
            var endDate = $("#endDate").textbox("getValue");
            log_datagrid.datagrid("load", {
                beginDate:beginDate,
                endDate:endDate
            });
        }
    };

    $("a[data-btn]").click(function () {
        var btn = $(this).data("btn");
        methodObj[btn]();
    });


    log_datagrid.datagrid({
        fit: true,
        fitColumns: true,
        striped: true,
        url: '/systemLog/list.do',
        toolbar: '#log_toolbar',
        pagination: true,
        rownumbers: true,
        singleSelect: true,
        columns: [[
            {
                field: 'opUser', title: '操作人', width: 50, formatter: function (value, row, index) {
                    return row.opUser ? ""+row.opUser.username+"" : "sss";
                }
            },
            {field: 'opTime', title: '操作时间', width: 50},
            {field: 'opIp', title: 'IP地址', width: 50},
            {field: 'function', title: '具体操作', width: 50}

        ]]
    });

    log_dialog.dialog({
        width: 320,
        height: 360,
        buttons: '#log_button',
        closed: true,
        onClose: function () {
            $("#log_form").form("clear");
        }
    });
});
