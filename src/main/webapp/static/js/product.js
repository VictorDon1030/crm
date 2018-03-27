$(function () {

    var product_datagrid = $("#product_datagrid");
    var product_dialog = $("#product_dialog");
    var product_form = $("#product_form");

    //给商品设值 表头动态值
    $.get("/product/list.do", function (data) {

        var d = new Date;
        var a = new Date;
        var totalPrices = 0;
        var count = 0;
        for(var i = 0; i < data.rows.length; i++){
            totalPrices += data.rows[i].unitpPrice;
            a = data.rows[i].pastDueTime;
            console.log(a <= d);
         if(!(a <= d)){
             count ++;
         }

        }
        console.log(count);
        $("#piece").html(data.total); //共有多少商品
        $("#avgPrice").html(totalPrices / data.total);//设置平均价格
        $("#pastDue").html(count); //设置已经过期商品总数量

        //目的拿到时间加


    },"json");



    product_datagrid.datagrid({
        fit: true,
        fitColumns: true,
        singleSelect: true,  //只允许选择一行。
        striped: true, // 斑马线
        url: '/product/list.do',
        toolbar: '#toolbar',
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
        //新增按钮
        add: function () {
            //显示密码框
            $("#tr_password").show();
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

});










