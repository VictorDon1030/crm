$(function () {
    $("#menu").tree({
        lines:true, //定义是否显示树控件上的虚线。
        url:'/linkage/getRootLinkage.do',
        onClick:function (node) { //在用户点击一个节点的时候触发。
            //表明指定的面板是否存在，'which'参数可以是选项卡面板的标题或索引。
            if($("#tabs").tabs("exists",node.text)){
                $("#tabs").tabs("select", node.text);
            }else{
                //在tabs中添加一个选项卡
                $("#tabs").tabs("add",{
                    title:node.text,
                    closable:true, //在设置为true的时候，选项卡面板将显示一个关闭按钮，在点击的时候会关闭选项卡面板。
                    selected:true,
                    //使用这样加载更全面
                    content:"<iframe src='"+node.url+"' width='100%' height='98%' frameborder='0'>"
                })
            }
            var zTree = $.fn.zTree.getZTreeObj("treeDemo");
            var nodes = zTree.getNodes();

            for(var i = 0;i<nodes.length;i++){
                var style = (style=="italic" ? "normal" : "italic");

                zTree.setting.view.fontCss["font-style"] = style;

                //调用updateNode(node)接口进行更新

                zTree.updateNode(nodes[i]);

            }
        }
    })
});





























