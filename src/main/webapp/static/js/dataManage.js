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
                    $.messager.confirm('温馨提示','再给你一次重新做人的机会!!',function(r){
                        if (r) {
                            $.get('/removeData.do',{ids:ids},function (data) {
                                if (data.success) {
                                    $.messager.alert('温馨提示','删库成功,准备跑路','info');
                                } else{
                                    $.messager.alert('温馨提示',data.msg,'info');
                                }
                            });
                        } else {
                            $.messager.alert('温馨提示','回头是岸','info');
                        }
                    });
                }
            });
        } else {
            $.messager.alert('温馨提示','请勾选一个数据','info');
        }

    });
});