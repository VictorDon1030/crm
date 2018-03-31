$(function () {
    $('#tt').tabs({
        onSelect:function (title,index) {
        if (index == 1){
            if (!$('#weChat').attr('src')) {
                $('#weChat').attr('src','/weiChat/view.do');
            }
        }
    }}
    );

    $('#tj').click(function () {
        $.get('/joinApply/saveOrUpdate.do',function (data) {
            if (data.success){
                alert('成功');
            } else {
                alert('失败');
            }
        });
    });
});