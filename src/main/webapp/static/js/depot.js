$(function () {
    $("#depot_datagrid").datagrid({
        url:'/depot/list.do',
        toolbar:'#depot_toolbar',
        fit:true,
        fitColumns:true,
        //striped:true,
        pagination:true,
        singleSelect:true,
        resizeHandle:"both",
        columns:[[
            {field: 'name', title: '仓库名称', width: 50},
            {field: 'sn', title: '仓库编码', width: 50},
            {field: 'employee', title: '联系人', width: 50,formatter:function (value, row, index) {
                return value ? value.realname  :"";
            }},
            // {field: 'employee', title: '联系电话', width: 50,Formatter:function (value, row, index) {
            //     return value? value:"";
            // }},
            {field: 'address', title: '仓库地址', width: 50},
            {field: 'vdate', title: '添加时间', width: 50},
            {field: 'state', title: '仓库状态', width: 50,
                formatter:function(value, row, index){
                if (value){
                    var state = '<a name="sta1" class="easyui-switchbutton" onClick=changeState2false('+index+') />';
                    return state;
                }else{
                    state = '<a name="sta2" class="easyui-switchbutton"  onClick=changeState2true('+index+') />';
                    return state;
                }
            }},
            {field: 'str1', title: '操作', width: 50 ,
                formatter:function(value, row, index){
                    var str1 = '<a href="#" name="str1" onclick="deleted()" class="easyui-linkbutton button-red" ></a>';
                return str1;}
            },
            {field: 'str2', width: 50 ,
                formatter:function(value, row, index){
                    var str2 = '<a href="#" name="str2" class="easyui-linkbutton button-olive" ></a>';
                    return str2;}
            },

        ]],
        onLoadSuccess:function(data){
            $("a[name='str1']").linkbutton({text:'删除',plain:true,});
            $("a[name='str2']").linkbutton({text:'查看库存',plain:true});
            $("a[name='sta1']").switchbutton({onText:'正常',offText:'停用',width:100,checked:true});
            $("a[name='sta2']").switchbutton({onText:'正常',offText:'停用',width:100,checked:false});

        }
});
    $("#depot_dialog").dialog({
        width:350,
        height:350,
        buttons:'#depot_button',
        closed:true,
        onClose:function () {
            $("#depot_form").form('clear');
        }
    });

})
//删除仓库
function deleted(){
    var row = $('#depot_datagrid').datagrid('getSelected');
    if (row) {
        $.messager.confirm('确认', '确定删除仓库吗?', function (r) {
            if (r) {
                $.get('/depot/delete.do', {id: row.id}, function (data) {
                    if (data.success) {
                        $.messager.alert('温馨提示', '操作成功', 'info', function () {
                            $("#depot_datagrid").datagrid('reload');

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
    $("#depot_dialog").dialog('open');
    $("#depot_dialog").dialog('setTitle','新增仓库');

}
//编辑
function edit() {
    var row=$("#depot_datagrid").datagrid('getSelected');
    if (!row) {
        $.messager.alert('温馨提示','请选中一条数据');
        return;
    }
    if (row.employee) {
        row["employee.id"]=row.employee.id;
        row["employee.tel"]=row.employee.tel;
    }
    $("#depot_form").form('load',row);
    $("#depot_dialog").dialog('open');
    $("#depot_dialog").dialog('setTitle','编辑仓库');


}
//停用
function changeState2false(index) {
    $('#depot_datagrid').datagrid('selectRow',index);
    var row = $('#depot_datagrid').datagrid('getSelected');
    if (row.state){
        $.messager.confirm('确认','确定设置为停用吗?',function (r) {
            if (r){
                $.get('/depot/changeState.do',{id:row.id},function (data) {
                    if (data.success){
                        $.messager.alert('温馨提示','操作成功','info',function () {
                            $("#depot_datagrid").datagrid('reload');

                        });
                    }else {
                        $.messager.alert('温馨提示',data.msg);
                    }
                })
            }
        });
    }
}
//启用
function changeState2true(index) {
    $('#depot_datagrid').datagrid('selectRow',index);
    var row = $('#depot_datagrid').datagrid('getSelected');
    if (!row.state){
        $.messager.confirm('确认','确定重新启用吗?',function (r) {
            if (r){
                $.get('/depot/changeState.do',{id:row.id},function (data) {
                    if (data.success){
                        $.messager.alert('温馨提示','操作成功','info',function () {
                            $("#depot_datagrid").datagrid('reload');

                        });
                    }else {
                        $.messager.alert('温馨提示',data.msg);
                    }
                })
            }
        });
    }
}
//保存
function save() {
    $("#depot_form").form('submit',{
        url:'/depot/saveOrUpdate.do',
        success:function (data) {
            data = $.parseJSON(data);
            if (data.success){
                $.messager.alert('温馨提示','保存成功','info',function () {
                    cancel();
                    $("#depot_datagrid").datagrid('reload');
                })
            }
        }
    })
}
//取消
function cancel() {
    $("#depot_dialog").dialog("close");
}
//刷新
function reload() {
    $("#depot_datagrid").datagrid('load');
}
