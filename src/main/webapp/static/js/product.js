$(function () {

    var product_datagrid = $("#product_datagrid");
    var product_dialog = $("#product_dialog");
    var product_form = $("#product_form");


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
                panelHeight:'auto'
            })
        }
    });

    $.get("/productStock/query.do", function (data) {
        var num2 = 0;
        for(var i = 0; i < data.length; i++){
            var num = data[i].storeNumber;
            if(10 < num){
                num2++;
            }
        }

        $("#someProduct").html(num2);


    },"json");



    //给商品设值 表头动态值
    $.get("/product/countId.do", function (data) {

        var d = new Date;
        var creat = new Date(d.getTime() +  7320 * 24 * 30 * 1000);  //往后推2个月
        //console.log(creat.getTime());

        var now = new Date();
        //console.log(now.getTime())


        //var t = 787986456465; // 当参数为数字的时候，那么这个参数就是时间戳，被视为毫秒，创建一个距离1970年1月一日指定毫秒的时间日期对象。
        //console.log(new Date(t))
        var num = 0;
        var num2 = 0;

        //console.log(data);
        var totalPrices = 0;
        for(var i = 0; i < data.length; i++){
            totalPrices += data[i].unitpPrice;
            if(data[i].pastDueTime != null && data[i].pastDueTime != ''){
                var t = data[i].pastDueTime; // 月、日、时、分、秒如果不满两位数可不带0.
                var T = new Date(t); // 将指定日期转换为标准日期格式。Fri Dec 08 2017 20:05:30 GMT+0800 (中国标准时间)
                //console.log(T.getTime())
                if(now.getTime() >= T.getTime()){
                    num++;
                    //console.log(num++);
                }
                if(creat.getTime() >=  T.getTime()){
                    num2++;
                }
            }
        }
        //console.log(num2);
        //console.log(count);
        $("#piece").html(data.length); //共有多少商品

        $("#avgPrice").html((totalPrices / data.length).toFixed(2));//设置平均价格
        $("#pastDue").html(num); //设置已经过期商品总数量
        $("#beAboutTo").html(num2-num); //设置两个月后过期商品总数量

        //目的拿到时间加
    },"json");

    //最近7天 热卖商品
    $.get("/saleasCount/selectAll.do", function (data) {

        var d = new Date;
        var create3 = new Date(d.getTime() + 24*7*60*60*1000);  //往后推7天
        //console.log(create3.getTime());

        var myDate = new Date(); //获取今天日期
        myDate.setDate(myDate.getDate() - 7);
        //console.log(myDate);


        var num = 0;
        var num2 = 0;
        //变成数组
        var a = [];
        for(var i = 0; i < data.length; i++){
            a.push(data[i].number)
        }
        //console.log(a);
        //排序
        var arr = a;
        arr.sort(function (a, b) {
            return b-a;
        });
        /*console.log(a[0]);
        console.log(a[1]);
        console.log(a[2]);*/
        $("#num1").html(a[0]);
        $("#num2").html(a[1]);
        $("#num3").html(a[2]);

        var a = [];
        for(var i = 0; i < data.length; i++){
            //if(data[i].vdate != null && data[i].vdate != ''){
            //var c = data[i].vdate;
            var t = data[i].vdate; // 月、日、时、分、秒如果不满两位数可不带0.
            var T = new Date(t); // 将指定日期转换为标准日期格式。Fri Dec 08 2017 20:05:30 GMT+0800 (中国标准时间)
            //console.log(T.getTime())
               if(create3.getTime() >= T.getTime() && myDate.getTime() >= T.getTime()){
                   a.push(data[i].number)
               }
        }
    },"json");


    //主页面
    product_datagrid.datagrid({
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
                formatter: function (value, rows, index) {
                    //var im = '<img src="/static/spaceImgPath/' + (index + 1) + '.png"/>';
                    //console.log(rows.imagePath);
                    if(rows.imagePath != null && rows.imagePath != ''){
                        return '<img width="120px" height="60px" border="0" src= '+rows.imagePath+ '>';
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
    //dialog 弹出框
    product_dialog.dialog({
        width: 800,
        height: 550,
        buttons: '#buttons',
        closed: true,
        modal: true,
        onClose: function () {
            product_form.form('clear');
        }
    });

    //所有的按钮绑定事件
    $("a[data-cmd]").click(function () {
        //根据按钮的data-cmd属性值来调用方法
        var methodName = $(this).data("cmd");
        console.log(methodName);
        methodobj[methodName]();
    });


    var methodobj = {
        //保存按钮
        save: function () {
            product_form.form("submit", { //表单中的submit方法}
                url: '/product/saveOrUpdate.do',

                success: function (data) { //data响应回来数据
                    data = $.parseJSON(data); //转成json对象
                    if (data.success) {
                        $.messager.alert("温馨提示", "保存成功", "info", function () {
                            //关闭弹出的窗口
                            product_dialog.dialog("close", true);
                            //product_dialog.dialog("close",true);
                            //刷新datagrid数据表格
                            product_datagrid.datagrid("reload");
                        });
                    } else {
                        $.messager.alert("温馨提示", data.msg);
                    }
                }
            })
        },

        //查看已下架的商品 按钮 改变路径
        examinePutaway: function () {
            //$("#todu_changeState").linkbutton('enable');
            product_datagrid.datagrid('options').url="/product/list.do";
            product_datagrid.datagrid("reload");
        },

        //查看已上架的商品 按钮
        examineSoldOut: function () {
            product_datagrid.datagrid('options').url="/productone/list.do";
            product_datagrid.datagrid("reload");
        },

        //新增按钮
        add: function () {
            //点击新增打开页面
            product_dialog.dialog('open', true);
            //设置动态标题
            product_dialog.dialog('setTitle', '新增商品')
        },

        //编辑按钮
        edit: function () {
            //获取选中的数据
            var row = product_datagrid.datagrid("getSelected");
            //判断是否选中
            if (!row) {
                $.messager.alert("温馨提示", "亲,请选择需要编辑的数据", "info");
                return;
            }

            //回显数据 选中的那一行数据
            product_form.form("load", row);

            //点击编辑打开页面
            product_dialog.dialog('open', true);
            //设置动态标题
            product_dialog.dialog('setTitle', '编辑商品')
        },
        //商品下架按钮
        soldOut: function () {
            //获取选中的数据
            var row = product_datagrid.datagrid("getSelected");
            //判断是否选中
            if (!row) {
                $.messager.alert("温馨提示", "亲,请选择需要下架的商品", "info");
                return;
            }
            $.get("/product/query.do", {id: row.id}, function (data) {
                $.messager.confirm("温馨提示", "您确定需要下架此商品吗?", function (r) {
                    //data = $.parseJSON(data); //转成json对象
                    if (r) {
                        console.log(row);
                        $.post("/productone/saveOrUpdate.do", row, function (data) {

                            if (data.success) {
                                $.messager.alert("温馨提示", "操作成功", "info", function () {
                                    // emp_datagrid.datagrid("reload");
                                    product_datagrid.datagrid("reload");
                                });
                            } else {
                                $.messager.alert("温馨提示", data.msg);
                            }
                            $.get("/product/delete.do", {id: row.id}, function (data) {

                            },"json")
                        },"json");

                    }

                })
            },"json");
        },

        //商品上架按钮
        putaway: function () {
            //设置点击事件禁止使用 点击查看已下架商品后促发可以点击
           // $("#todu_changeState").linkbutton('disable');
            //if($("#todu_changeState").linkbutton("options").disabled==false){
            //获取选中的数据
                var row = product_datagrid.datagrid("getSelected");
                //判断是否选中
                if (!row) {
                    $.messager.alert("温馨提示", "亲,请选择需要上架的商品", "info");
                    return;
                }
                $.get("/productone/list.do", {id: row.id}, function (data) {
                    $.messager.confirm("温馨提示", "您确定需要上架此商品吗?", function (r) {
                        //data = $.parseJSON(data); //转成json对象
                        if (r) {
                            console.log(row);
                            $.post("/product/save.do", row, function (data) {

                                if (data.success) {
                                    $.messager.alert("温馨提示", "操作成功", "info", function () {
                                        // emp_datagrid.datagrid("reload");
                                        product_datagrid.datagrid("reload");
                                    });
                                } else {
                                    $.messager.alert("温馨提示", data.msg);
                                }
                                $.get("/productone/delete.do", {id: row.id}, function (data) {

                                },"json")
                            },"json");

                        }

                    })
                },"json");
           // }
        },

        //删除按钮
        remove: function () {
            //获取选中的数据
            var row = product_datagrid.datagrid("getSelected");
            //判断是否选中
            if (!row) {
                $.messager.alert("温馨提示", "亲,请选择需要编辑的数据", "info");
                return;
            }
            $.messager.confirm("温馨提示", "您确定需要删除吗?", function (r) {
                if (r) {
                    $.get("/product/delete.do", {id: row.id}, function (data) {
                        if (data.success) {
                            $.messager.alert("温馨提示", "操作成功", "info", function () {
                                // emp_datagrid.datagrid("reload");
                                product_datagrid.datagrid("reload");
                            });
                        } else {
                            $.messager.alert("温馨提示", data.msg);
                        }
                    })
                }
            })

        },

        //取消按钮
        cancel: function () {
            product_dialog.dialog("close")
        },

        //刷新datagrid数据表格
        reload: function () {
            product_datagrid.datagrid("reload");
        },

        //高级查询
        searchs: function () {
            //获取关键字内容
            var keyword = $("#keyword").textbox("getValue");
            var beginDate = $("#beginDate").textbox("getValue");
            var endDate = $("#endDate").textbox("getValue");
            product_datagrid.datagrid("load", {
                keyword: keyword,
                beginDate: beginDate,
                endDate: endDate
            });
        }
    };
     //弹出子窗口
     $("#product2_dialog").dialog({
         width: 400,
         height: 300,
         buttons: '#buttons2',
         closed: true,
         modal: true,
         onClose: function () {
         product_form.form('clear');
         }
     });

});

//二级联动 一级分类 新增
function newAdd() {
    //点击新增打开页面
    $("#product2_dialog").dialog('open', true);
    //设置动态标题
    $("#product2_dialog").dialog('setTitle', '新增商品');
}


function genre() {
    $("#addStyle2").removeClass("myBtn"); //删除样式 再添加样式
    $("#addStyle").addClass("myBtn"); //添加样式
}
function serve() {
    $("#addStyle").removeClass("myBtn"); //删除样式 再添加样式
    $("#addStyle2").addClass("myBtn");
}

//子窗口取消按钮
function cancel2() {

    $("#product2_dialog").dialog("close")

}
//子窗口提交按钮 保存按钮
function save2() {
    var value = $("[name=type]:checked").val();
    var category = $("#category").val();
    //判断是否选中
    if (!value) {
        $.messager.alert("温馨提示", "亲,请选择需要的类型", "info");
        return;
    }
    $.post("/stair/saveOrUpdate.do", {"name":category, "secondary_id":value}, function (data) {
        if (data.success) {
            $.messager.alert("温馨提示", "操作成功", "info", function () {
                $("#product2_dialog").dialog("close");
                $('#stair').combobox({
                    url : "/stair/list.do",
                    queryParams: {

                    }
                });
            });
        } else {
            $.messager.alert("温馨提示", data.msg);
        }
    },"json");
}
//商品单位 TODO
function newSave2() {
    alert("亲, 努力制作中 敬请期待 谢谢!")

}
