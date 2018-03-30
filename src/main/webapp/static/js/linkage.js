$(function () {

    $("#linkage_datagrid").datagrid({
        fit: true,
        fitColumns: true,
        singleSelect: true,  //只允许选择一行。
        striped: true, // 斑马线
        url: '/product/list.do',
        toolbar: '#toolbar',
        collapsible:true, //定义是否显示可折叠按钮。
        //toolbar:"toolbar_form",
        pagination: true,  //显示分页工具栏。
        rownumbers: true,  //显示一个行号列。
        columns: [[
            {field: 'name', title: '商品名称', width: 100},
            /*{field:'imagePath',title:'商品图片',width:100},*/
            {field: 'name', title: '商品名称', width: 100},
            /*{field:'imagePath',title:'商品图片',width:100},*/
            {
                field: 'imagePath', title: '商品图片', width: 100, align: 'center', nowrap: true,
                formatter: function (value, rows, index) {
                    //var im = '<img src="/static/spaceImgPath/' + (index + 1) + '.png"/>';
                    //console.log(im);
                        if(rows.id == rows.imagePath){
                            return '<img width="120px" height="60px" border="0" src="/static/spaceImgPath/' + value + '.png"/>';
                        }else {
                            return '<img width="120px" height="60px" border="0" src="/static/spaceImgPath/2117.png"/>';
                        }
                }
            },
            {field: 'brand', title: '商品品牌', width: 100},
            {field: 'specification', title: '商品规格', width: 100},
            {field: 'unitpPrice', title: '销售单价', width: 100},
            {field: 'pastDueTime', title: '过期时间', width: 100},
            {field: 'goodsMark', title: '商品货号', width: 100},
            {field: 'remark', title: '备注信息', width: 100, align: 'right'}
        ]]
    });

    /*  //取消按钮
     cancel: function () {
     cf_dialog.dialog("close")


     //刷新datagrid数据表格
     reload: function () {
     cf_datagrid.datagrid("reload");*/
    //所有的按钮绑定事件
    $("a[data-cmd]").click(function () {
        //根据按钮的data-cmd属性值来调用方法
        var methodName = $(this).data("cmd");
        //console.log(methodName);
        methodobj[methodName]();
    });

    var methodobj = {
        //查看分类商品 按钮
        fruits: function () {
            $("#linkage_datagrid").datagrid('options').url="/product/fruits.do";
            $("#linkage_datagrid").datagrid("reload");
            $("*").removeClass("myBtn");//删除样式 再添加样式
            $("#fruits").addClass("myBtn"); //添加样式
        },
        drinks: function () {
            $("#linkage_datagrid").datagrid('options').url = "/product/drinks.do";
            $("#linkage_datagrid").datagrid("reload");
            $("*").removeClass("myBtn");//删除样式 再添加样式
            $("#drinks").addClass("myBtn"); //添加样式
        },
        foodstuff: function () {
            $("#linkage_datagrid").datagrid('options').url="/product/foodstuff.do";
            $("#linkage_datagrid").datagrid("reload");
            $("*").removeClass("myBtn");//删除样式 再添加样式
            $("#foodstuff").addClass("myBtn"); //添加样式
        },
        vegetables: function () {
            $("#linkage_datagrid").datagrid('options').url="/product/vegetables.do";
            $("#linkage_datagrid").datagrid("reload");
            $("*").removeClass("myBtn");//删除样式 再添加样式
            $("#vegetables").addClass("myBtn"); //添加样式
        },
        grain: function () {
            $("#linkage_datagrid").datagrid('options').url="/product/grain.do";
            $("#linkage_datagrid").datagrid("reload");
            $("*").removeClass("myBtn");//删除样式 再添加样式
            $("#grain").addClass("myBtn"); //添加样式
        },
        aquatic: function () {
            $("#linkage_datagrid").datagrid('options').url="/product/aquatic.do";
            $("#linkage_datagrid").datagrid("reload");
            $("*").removeClass("myBtn");//删除样式 再添加样式
            $("#aquatic").addClass("myBtn"); //添加样式
        },
        child: function () {
            $("#linkage_datagrid").datagrid('options').url="/product/child.do";
            $("#linkage_datagrid").datagrid("reload");
            $("*").removeClass("myBtn");//删除样式 再添加样式
            $("#child").addClass("myBtn"); //添加样式
        },
    };
});

/*function genre() {
    $($("*").removeClass("myBtn");//删除样式 再添加样式
    $("#addStyle").addClass("myBtn"); //添加样式
}
function serve() {
    $("#addStyle").removeClass("myBtn"); //删除样式 再添加样式
    $("#addStyle2").addClass("myBtn");
}*/






















