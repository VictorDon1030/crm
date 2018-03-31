$(function () {

    var cf_datagrid = $("#cf_datagrid");
    var cf_dialog = $("#cf_dialog");
    var cf_form = $("#cf_form");


    //二级联动
    $("#stair").combobox({
        url:'/stair/list.do',
        editable:false,
        valueField:'id',
        textField:'name',
        panelHeight:'auto',
        onSelect : function(data){
           /* //console.log(data.id);
            $('#garden').val(data.id);
            //查询类型
            $('#typeId').val('');*/
            $('#gardenApprovalTypeCombox').combobox({
                url:'/stair/getStair.do?id='+data.id+'',
                editable:false,
                valueField:'id',
                textField:'name',
                panelHeight:'auto',
                /*onSelect : function(data){
                    console.log(data);
                }*/
            })/*.combobox('clear');*/
        }
    });


    cf_datagrid.datagrid({
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
            {
                field: 'imagePath', title: '商品图片', width: 100, align: 'center', nowrap: true,
                formatter: function (value, row, index) {
                    //如下的写法太复杂了,注意只有数字才这么写.
                    return '<img width="120px" height="60px" border="0" src="/static/spaceImgPath/' + (index + 1) + '.png"/>';
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

});

//二级联动 一级分类 新增
function newAdd() {
    //点击新增打开页面
    $("#cf2_dialog").dialog('open', true);
    //设置动态标题
    $("#cf2_dialog").dialog('setTitle', '新增商品');
}


function genre() {
    $("#addStyle2").removeClass("myBtn"); //删除样式 再添加样式
    $("#addStyle").addClass("myBtn"); //添加样式
}
function serve() {
    $("#addStyle").removeClass("myBtn"); //删除样式 再添加样式
    $("#addStyle2").addClass("myBtn");
}

