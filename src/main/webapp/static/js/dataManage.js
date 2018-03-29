$.ajaxSettings.traditional = true;
$(function () {
    $('#btn-clean').click(function () {
        var vs = $('input');
        var ids = [];
        $.each(vs,function (index,val) {
            if (val.checked) {
                ids[index] = val.id;
            }
        });
        if (ids.length) {
            $.messager.confirm('温馨提示','删库跑路,你做好觉悟了么?',function (ret) {
                if (ret) {
                    $.get('/removeData.do',{ids:ids},function (data) {
                        console.log(data);
                    });
                }
            });
        } else {
            $.messager.alert('温馨提示','请勾选一个数据','info');
        }

    });
});