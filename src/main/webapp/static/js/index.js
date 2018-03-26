$(function () {
    $("#menu").tree({
        url:'/menu/getRootMenu.do'
    });
    $("#menu").tree({
        onClick:function (node) {
            var ret = $("#tabs").tabs('exists',node.text);
            if (!ret) {
                $("#tabs").tabs('add',{
                    title:node.text,
                    closable:true,
                    content:"<iframe src='"+node.url+"' width='100%' height='99%' frameborder='0'></iframe>"
                });
            }
            $("#tabs").tabs('select',node.text);
        }
    });
});