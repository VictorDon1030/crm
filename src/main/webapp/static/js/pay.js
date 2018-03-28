$(function () {

    var pay_datagrid = $("#pay_datagrid");
    var maxType = $("#maxType");
    var pay_form = $("#pay_form");
    var setType_form = $("#setType_form");
    var minType_form = $("#minType_form");
    var minType_dialog = $("#minType_dialog");
    var setType_dialog = $("#setType_dialog");
    var addMaxType_dialog = $("#addMaxType_dialog");
    var addMinType_dialog = $("#addMinType_dialog");
    var setMaxType = $("#setMaxType");
    var setMinType = $("#setMinType");
    var addMaxType_form = $("#addMaxType_form");
    var addMinType_form = $("#addMinType_form");
    var maxType_datagrid = $("#maxType_datagrid");
    //设置日期输入框的值
    //$('#showDate').datetimebox('setValue', new Date());
    //页面一加载完，就要将今日按钮选中，表单是按照今日来查
    $("#today").linkbutton("select");


    var methodObj = {
        //弹出设置分类的对话框
        setType: function () {
            //打开前，将大分类的列表的第一行选中
            setMaxType.datagrid("selectRow",0);
            //将小分类的列表显示第一行大分类对应的小分类
            var row=setMaxType.datagrid("getSelected");
            setMinType.datagrid("load",{"maxTypeId":row.id});
            setType_dialog.dialog("open");
        },

        //点击时，弹出对话框：添加当前大分类的小分类
        input: function () {
            minType_form.form("clear");
            minType_dialog.dialog("setTitle","添加小分类");
            $("#showType").html(maxType.combobox("getText"));
            minType_dialog.dialog("open");
        },
        //提交录入支出表单
        submit:function () {
            //获取表单里的数据，提交到后台，插入到支出录入、支出明细
            pay_form.form("submit",{
                url:'/pay/save.do',
                onSubmit: function(param){
                    //获取大分类的名称，小分类的名称，然后拼成type，额外提交到后台，封装到pay上
                    var minTypeName=pay_datagrid.datagrid("getSelected").name;
                    var type=maxType.combobox("getText")+"-"+minTypeName;
                    param.type=type;
                    param.maxType=maxType.combobox("getText");
                },
                //提交成功后的回调函数
                success:function (data) {
                    //一旦点击提交按钮，就关闭对话框
                    minType_dialog.dialog("close");
                    data=$.parseJSON(data);
                    if(data.success){
                        $.messager.alert("温馨提示",'保存成功','info',function () {
                            //刷新页面
                            window.location.reload();
                            //中间的表格要重新查一次今日
                            maxType_datagrid.datagrid("load",{"today":1});
                        });
                    }else {
                        $.messager.alert("温馨提示",data.msg,'warning');
                    }
                }
            });
        },
        //点击确认，提交minType_dialog对话框
        minType_dialog_ok:function () {
            //在对应大分类里，插入小分类:name,大分类的id
            minType_form.form("submit",{
                url:'/minType/insertMinType.do',
                onSubmit: function(param){
                    param["maxtype_id"]=maxType.combobox("getValue");
                },
                success:function (data) {
                    //一旦点击提交按钮，就关闭对话框
                    minType_dialog.dialog("close");
                    data=$.parseJSON(data);
                    if(data.success){
                        $.messager.alert("温馨提示",'保存成功','info',function () {
                            //更新小分类列表里的数据
                            pay_datagrid.datagrid("load",{"maxTypeId":maxType.combobox("getValue")});
                        });
                    }else {
                        $.messager.alert("温馨提示",data.msg,'warning');
                    }
                }
            });
        },
        //点击关闭对话框minType_dialog
        minType_dialog_cancel:function () {
            minType_dialog.dialog("close");
        },
        //点击确认，提交setType_dialog对话框
        setType_dialog_ok:function () {
            setType_dialog.dialog("close");
        },
        //点击关闭对话框setType_dialog
        setType_dialog_cancel:function () {
            setType_dialog.dialog("close");
        },
        //点击提交对话框addMaxType_dialog
        addMaxType_dialog_ok:function () {
            addMaxType_form.form("submit",{
                url:'/maxType/insertMaxType.do',
                success:function (data) {
                    //一旦点击提交按钮，就关闭对话框
                    addMaxType_dialog.dialog("close");
                    data=$.parseJSON(data);
                    if(data.success){
                        $.messager.alert("温馨提示",'保存成功','info',function () {
                            //更新大分类列表里的数据
                            setMaxType.datagrid("load");
                        });
                    }else {
                        $.messager.alert("温馨提示",data.msg,'warning');
                    }
                }
            });
        },
        //点击关闭对话框addMaxType_dialog
        addMaxType_dialog_cancel:function () {
            addMaxType_dialog.dialog("close");
        },
        //点击提交对话框addMinType_dialog
        addMinType_dialog_ok:function () {
            addMinType_form.form("submit",{
                //1.将此小分类插入到数据库，需要：name,maxType_id
                url:'/minType/insertMinType.do',
                onSubmit: function(param){
                    //获取大分类的名称，小分类的名称，然后拼成type，额外提交到后台，封装到pay上
                    param["maxtype_id"]=setMaxType.datagrid("getSelected").id;
                },
                //提交成功后的回调函数
                success:function (data) {
                    //一旦点击提交按钮，就关闭对话框
                    addMinType_dialog.dialog("close");
                    data=$.parseJSON(data);
                    if(data.success){
                        $.messager.alert("温馨提示",'保存成功','info',function () {
                            //2.更新小分类列表的内容
                            setMinType.datagrid("load");
                            //3.更新pay_datagrid的内容
                            pay_datagrid.datagrid("load");
                        });
                    }else {
                        $.messager.alert("温馨提示",data.msg,'warning');
                    }
                }

            });
        },

        //点击关闭对话框addMinType_dialog
        addMinType_dialog_cancel:function () {
            addMinType_dialog.dialog("close");
        },
        //打开添加大分类的对话框
        openAddMaxType:function () {
            addMaxType_dialog.dialog("open");
        },
        //打开添加小分类的对话框
        openAddMinType:function () {
            //将form表单内容清空
            addMinType_form.form("clear");
            //要先设置大分类的名称
            $("#showMaxType").html(setMaxType.datagrid("getSelected").name);
            addMinType_dialog.dialog("open");
        },
        //按照今日查询
        today:function () {
            maxType_datagrid.datagrid("load",{"today":1});
            //也要让饼状图的值发生变化
            $.get("/pay/view.do",{"today":1});
        },
        //按照本周查询
        week:function () {
            maxType_datagrid.datagrid("load",{"week":7});
            $.get("/pay/view.do",{"week":7});

        },
        //按照本月查询
        month:function () {
            maxType_datagrid.datagrid("load",{"month":30});
            $.get("/pay/view.do",{"month":30});
        },
        //按照今年查询
        year:function () {
            maxType_datagrid.datagrid("load",{"year":365});
            $.get("/pay/view.do",{"year":365});
        }
    };

    //给所有具有data-btn属性的A标签统一绑定点击事件
    $("a[data-btn]").click(function () {
        var btn = $(this).data("btn");
        methodObj[btn]();
    });

    //pay_datagrid上的数据表格
    pay_datagrid.datagrid({
        fit: true,
        fitColumns: true,
        striped: true,
        url: '/minType/selectByMaxTypeId.do',
        toolbar: '#pay_toolbar',
        singleSelect: true,
        columns: [[
            {field: 'name', title: '支出类型', width: 50}
        ]]
    });

    //支出大分类的下拉框
    maxType.combobox({
        url:'/maxType/selectAll.do',
        prompt:'支出大分类',
        width:220,
        panelHeight:'auto',
        valueField:'id',
        textField:'name',
        onSelect: function(rec){ //联动，当选中某个选项时，让pay_datagrid去查数据并渲染
            pay_datagrid.datagrid("load",{"maxTypeId":rec.id});
        },
        onLoadSuccess:function(){ //默认选中第一条数据
            var data= $(this).combobox("getData");
            if (data.length > 0) {
                maxType.combobox('select', data[0].id);
            }
        }
    });


    //大分类的列表
    setMaxType.datagrid({

        url:'/maxType/selectAll.do',
        fitColumns:true,
        singleSelect:true,
        striped:true,
        width:210,
        height:380,
        columns:[[
            {field:'ck',width:100,checkbox:true,align:'center'},
            {field:'name',width:100,align:'center'}
        ]],
        //一加载完数据，就要选中第一行，并在右边显示小分类
        onLoadSuccess:function () {
            setMaxType.datagrid("selectRow",0);
        },
        onClickRow:function (index,row) {
            //点击一行时，触发此事件，index是当前行的索引，row是点击的行
            //小分类的datagrid显示
            setMinType.datagrid("load",{"maxTypeId":row.id});
        }
    });
    //小分类的列表
    setMinType.datagrid({
        fitColumns: true,
        striped: true,
        url: '/minType/selectByMaxTypeId.do',
        singleSelect: true,
        width:210,
        height:380,
        columns: [[
            {field: 'name',width: 50,align:'center'}
        ]]
    });

    //按照支出大分类maxType分组查询的数据表格
    maxType_datagrid.datagrid({
        url:'/pay/selectByDate.do',
        fit:true,
        fitColumns:true,
        striped:true,
        columns:[[
            {field: 'maxType',width: 50,align:'center'},
            {field: 'amount',width: 50,align:'center'}
        ]]
    });
    //一加载完，就要按照今日来查，因为今日按钮也是一加载完就选中
    maxType_datagrid.datagrid("load",{"today":1});

});
