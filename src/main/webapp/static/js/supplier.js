$(function () {
    $("#supplier_datagrid").datagrid({
        url:'/supplier/list.do',
        toolbar:'#supplier_toolbar',
        fit:true,
        fitColumns:true,
        pagination:true,
        singleSelect:true,
        resizeHandle:"both",
        checkbox:true,
        columns:[[
            {field: 'ck', checkbox:true},
            {field: 'name', title: '供应商', width: 50},
           // {field: 'debt', title: '应付欠款', width: 50},
            {field: 'realname', title: '联系人', width: 50},
            {field: 'tel', title: '联系电话', width: 50},
            {field: 'vdate', title: '添加时间', width: 50},
            {field: 'employee', title: '操作人员', width: 50,formatter:function (value, row, index) {
                return value ? value.realname  :"";
            }},
            {field: 'str1', title: '操作', width: 80 ,
                formatter:function(value, row, index){
                    var str1 = '<a href="#" name="str1" onclick="dept()" class="easyui-linkbutton  button-line-olive" ></a>';
                    var str2 = '<a href="#" name="str2" onclick="dept()" class="easyui-linkbutton button-line-green" ></a>';
                return str1+str2;}
            }

        ]],
        onLoadSuccess:function(data){
            $("a[name='str1']").linkbutton({text:'还款',plain:true,});
            $("a[name='str2']").linkbutton({text:'还款记录',plain:true});
        }
});
    $("#supplier_dialog").dialog({
        width:350,
        height:350,
        buttons:'#supplier_button',
        closed:true,
        onClose:function () {
            $("#supplier_form").form('clear');
        }
    });

})
//还款
function dept() {
    $.messager.alert('温馨提示','此功能为VIP使用,请购买后使用!');
}


//删除供应商
function deleted(){
    var row = $('#supplier_datagrid').datagrid('getSelected');
    if (row) {
        $.messager.confirm('确认', '确定删除该供应商吗?', function (r) {
            if (r) {
                $.get('/supplier/delete.do', {id: row.id}, function (data) {
                    if (data.success) {
                        $.messager.alert('温馨提示', '操作成功', 'info', function () {
                            $("#supplier_datagrid").datagrid('reload');

                        });
                    } else {
                        $.messager.alert('温馨提示', data.msg);
                    }
                })
            }
        });
    }
}

//新增
function add() {
    $("#supplier_dialog").dialog('open');
    $("#supplier_dialog").dialog('setTitle','新增供应商');

}
//编辑
function edit() {
    var row=$("#supplier_datagrid").datagrid('getSelected');
    if (!row) {
        $.messager.alert('温馨提示','请选中一条数据');
        return;
    }
    $("#supplier_form").form('load',row);
    $("#supplier_dialog").dialog('open');
    $("#supplier_dialog").dialog('setTitle','编辑供应商`');
}
//保存
function save() {
    $("#supplier_form").form('submit',{
        url:'/supplier/saveOrUpdate.do',
        success:function (data) {
            data = $.parseJSON(data);
            if (data.success){
                $.messager.alert('温馨提示','保存成功','info',function () {
                    cancel();
                    $("#supplier_datagrid").datagrid('reload');
                })
            }
        }
    })
}
//取消
function cancel() {
    $("#supplier_dialog").dialog("close");
}
//刷新
function reload() {
    $("#supplier_datagrid").datagrid('load');
}
