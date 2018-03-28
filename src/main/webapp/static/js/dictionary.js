
$(function () {
    //1.抽取常用的变量
    var dictionary_datagrid=$("#dictionary_datagrid");
    var dictionaryItem_datagrid=$("#dictionaryItem_datagrid");
    var dictionary_dialog=$("#dictionary_dialog");
    var dictionaryItem_dialog=$("#dictionaryItem_dialog");
    var dictionary_form=$("#dictionary_form");
    var dictionaryItem_form=$("#dictionaryItem_form");
    //2.定义一个变量，来存储所有function
    var methodObject={
        /*返回dialog弹框的方法*/
        cancel: function () {
            dictionary_dialog.dialog("close");
            //dictionary_form("clear");
        },
        /*返回dialog字典明细弹框的方法*/
        itemCancel: function () {
            dictionaryItem_dialog.dialog("close");
           // dictionaryItem_form ("clear");
        },
        //字典保存或者新增的操作
        saveOrUpdate: function () {
            dictionary_form.form('submit', {
                url: "/dictionary/saveOrUpdate.do",
                success: function (data) {
                    data = $.parseJSON(data);
                    if (data.success) {
                        $.messager.alert("温馨提示", "保存成功", "info", function () {
                            methodObject.cancel();
                            dictionary_datagrid.datagrid("reload");
                        });
                    } else {
                        $.messager.alert("温馨提示", data.msg, "warning");
                    }

                }

                // $("dictionary_form").form("clear");
            });
        },
        //字典明细保存或者新增的操作
        itemSaveOrUpdate: function () {
            dictionaryItem_form.form('submit', {
                url: "/dictionaryItem/saveOrUpdate.do",
                success: function (data) {
                    data = $.parseJSON(data);
                    if (data.success) {
                        $.messager.alert("温馨提示", "保存成功", "info", function () {
                            methodObject.itemCancel();
                            dictionaryItem_datagrid.datagrid("reload");
                        });
                    } else {
                        $.messager.alert("温馨提示", data.msg, "warning");
                    }

                }

                // $("dictionary_form").form("clear");
            });
        },
        dictionary_add:function () {
            dictionary_form.form("clear");
            dictionary_dialog.dialog("setTitle","新增目录");

            dictionary_dialog.dialog("open");
        },
        dictionary_edit:function () {
            //获取被选中行的信息：要做回显
            var row=dictionary_datagrid.datagrid("getSelected");
            if(!row){
                //row为Null
                $.messager.alert("温馨提示","亲，请选择要编辑的行","warning");
                return;
            }
            //打开对话框之前，先将上一次在表单填写的内容清空
            dictionary_form.form("clear");
            //设置对话框的标题
            dictionary_dialog.dialog("setTitle","字典目录编辑");
            //回显
            dictionary_form.form("load",row);
            //打开对话框
            dictionary_dialog.dialog("open");
        },
        dictionary_delete:function () {
            //1.获取被选择的行
            var row=dictionary_datagrid.datagrid("getSelected");
            if(!row){
                $.messager.alert("温馨提示","请选择要删除的一行！","warning");
                return;
            }
            //2.弹出确认框：是否确认删除
            $.messager.confirm("温馨提示","您确认删除吗？",function () {
                //发送ajax请求到后台，将要删除的id传过去
                $.post("/dictionary/delete.do",{"id":row.id},function (data) {
                    if(data.success){
                        $.messager.alert("温馨提示","操作成功","info",function () {
                            //点击确定按钮,刷新datagrid
                            dictionary_datagrid.datagrid("load");
                        });
                    }else{
                        $.messager.alert("温馨提示",data.msg,"info");
                    }
                },"json");
            });
        },
        dictionary_reload:function () {
            dictionary_datagrid.datagrid("reload");
        },
        dictionaryItem_add:function () {
            var itemRow = null;
            dictionaryItem_form.form("clear");
            dictionaryItem_dialog.dialog("setTitle","新增目录");
            var row = dictionary_datagrid.datagrid("getSelected");
            //  itemRow={"dictionary.id":row.id};
            // console.log(itemRow);
            if(!row){
                $.messager.alert("温馨提示","亲，请选择字典再添加明细","warning");
                return;
            }
            dictionaryItem_form.form("load",{"dictionary.id":row.id});
            dictionaryItem_dialog.dialog("open");
        },
        dictionaryItem_edit:function () {
            //获取被选中行的信息：要做回显
            var row=dictionaryItem_datagrid.datagrid("getSelected");
            if(!row){
                //row为Null
                $.messager.alert("温馨提示","亲，请选择要编辑的行","warning");
                return;
            }
            var dRow = dictionary_datagrid.datagrid("getSelected");
            if(!dRow){
                $.messager.alert("温馨提示","亲，请选择字典再编辑明细","warning");
                return;
            }
            //打开对话框之前，先将上一次在表单填写的内容清空
            dictionaryItem_form.form("clear");
            //设置对话框的标题
            dictionaryItem_dialog.dialog("setTitle","字典目录编辑");
            //回显
            row["dictionary.id"]=row.dictionary.id;
            dictionaryItem_form.form("load",row);
            //回显combobx:字典目录
            $("#dictionaryItem").combobox("setText",row.dictionary.name);
            //打开对话框
            dictionaryItem_dialog.dialog("open");
        },
        dictionaryItem_delete:function () {
            //1.获取被选择的行
            var row=dictionaryItem_datagrid.datagrid("getSelected");
            if(!row){
                $.messager.alert("温馨提示","请选择要删除的一行！","warning");
                return;
            }
            //2.弹出确认框：是否确认删除
            $.messager.confirm("温馨提示","您确认删除吗？",function () {
                //发送ajax请求到后台，将要删除的id传过去
                $.post("/dictionaryItem/delete.do",{"id":row.id},function (data) {
                    if(data.success){
                        $.messager.alert("温馨提示","操作成功","info",function () {
                            //点击确定按钮,刷新datagrid
                            dictionaryItem_datagrid.datagrid("load");
                        });
                    }else{
                        $.messager.alert("温馨提示",data.msg,"info");
                    }
                },"json");
            });
        },
        dictionaryItem_reload:function () {
            dictionaryItem_datagrid.datagrid("reload");
        }
    };
    //3.给所有具有data-cmd属性的a标签来统一绑定点击事件
    $("a[data-cmd]").click(function () {
        //4.获取事件源的data-cmd的属性值
        var methodName=$(this).data("cmd");
        //5.根据方法名，调用对应方法
        console.log(methodName);

        methodObject[methodName]();
    });

    //dictionary.jsp上的dictionary_datagrid
    dictionary_datagrid.datagrid({
        url:'/dictionary/list.do',
        fit:true,
        fitColumns:true,
        singleSelect:true,
        striped:true,
        pagination:true,
        rownumbers:true,
        toolbar:'#dictionary_tb',
        columns:[[
            {field:'sn',title:'字典目录编码',width:100,align:'center'},
            {field:'name',title:'字典目录名称',width:100,align:'center'},
            {field:'intro',title:'字典目录简介',width:100,align:'center'}
        ]] ,
        onClickRow:function (index, row) {
            console.log(row);
            dictionaryItem_datagrid.datagrid("load",{'dictionaryId':row.id});
        }
    });

        //dictionary.jsp上的dictionaryItem_datagrid
        dictionaryItem_datagrid.datagrid({
            url:'/dictionaryItem/selectItemByDictionaryId.do',
            fit:true,
            fitColumns:true,
            singleSelect:true,
            striped:true,
            pagination:true,
            rownumbers:true,
            toolbar:'#dictionaryItem_tb',
            columns:[[
                {field:'name',title:'字典目录名称',width:100,align:'center'},
                {field:'dictionary',title:'字典目录编码',width:100,align:'center',formatter: function(value,row,index){
                        if (value){
                            return value.name;
                        } else {
                            return value;
                        }
                    }
                },
                {field:'intro',title:'字典目录简介',width:100,align:'center'}
            ]]
        });




});

