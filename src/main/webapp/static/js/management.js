$(function () {
    $('#tt').tabs({
        onSelect:function (title,index) {
        if (index == 1){
            if (!$('#empManage').attr('src')) {
                $('#empManage').attr('src','/employee/view.do');
            }
        } else if (index == 2) {
            if (!$('#roleManage').attr('src')) {
                $('#roleManage').attr('src', '/role/view.do');
            }
        }
    }}
    );
});