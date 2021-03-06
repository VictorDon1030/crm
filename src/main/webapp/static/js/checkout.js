$(function () {
    //自定义设置datagrid,把表格给成现实图片
    var cardview = $.extend({}, $.fn.datagrid.defaults.view, {
        renderRow: function (target, fields, frozen, rowIndex, rowData) {
            var cc = [];
            cc.push("<td colspan=" + fields.length + " style='padding:10px 5px;border:0;'>");
            if (!frozen) {
                var img = rowData.imagePath;
                cc.push('<img src="/static/spaceImgPath/' + (rowIndex + 1) + '.png" style="width:150px;float:left"/>');
                cc.push('<div style="float:left;margin-left:30px;">');
                for (var i = 0; i < fields.length; i++) {
                    if (i == 0 || i == 2) {
                        continue;
                    }
                    var copts = $(target).datagrid('getColumnOption', fields[i]);

                    if(i%2==0){
                        cc.push('<p></p>');

                    }
                    cc.push('<p>&nbsp&nbsp&nbsp<span class="c-label">' + copts.title + ':</span> ' + rowData[fields[i]] + '</p>');
                }
                cc.push('</div>');
            }
            cc.push('</td>');
            return cc.join('');
        }
    });

    $('#product').datagrid(
        {
            //引用视图
            view: cardview,
            onClickRow: function (index, row) {
                //获取jquery对象
                var arr = $($("#cashier_item").find("[tag=id]"));
                /*console.log(arr);*/
                //转化出新的数组
                var newArr = $.map(arr, function (input) {
                    return $(input).val();
                });
                //数据显示到订单明细中
                $("#cashier_item").find("tr").last("tr").find("[tag=goodsMark]").val(row.goodsMark);
                $("#cashier_item").find("tr").last("tr").find("[tag=id]").val(row.id);
                $("#cashier_item").find("tr").last("tr").find("[tag=purchasingPrice]").val(row.purchasingPrice);
                $("#cashier_item").find("tr").last("tr").find("[tag=minPrice]").val(row.minPrice);
                $("#cashier_item").find("tr").last("tr").find("[tag=initialInventory]").val(row.initialInventory);
                $("#cashier_item").find("tr").last("tr").find("[tag=integral]").val(row.integral);
                $("#cashier_item").find("tr").last("tr").find("[tag=name]").val(row.name);
                var p = $("#cashier_item").find("tr").last("tr").find("[tag=unitpPrice]").val(row.unitpPrice);
                $("#cashier_item").find("tr").last("tr").find("[tag=number]").val(1);
                $("#cashier_item").find("tr").last("tr").find("[tag=amount]").val(p.val());
                $("#cashier_item").find("tr").last("tr").find("#remove_item").html("删除");
                //克隆
                var clone = $("#cashier_item tr:first").clone(true);
                $(clone).find("[tag=goodsMark]").val("");
                $(clone).find("[tag=id]").val("");
                $(clone).find("[tag=name]").val("");
                $(clone).find("[tag=unitpPrice]").val("");
                $(clone).find("[tag=number]").val("");
                $(clone).find("[tag=amount]").val("");
                $(clone).find("[tag=purchasingPrice]").val("");
                $(clone).find("[tag=minPrice]").val("");
                $(clone).find("[tag=initialInventory]").val("");
                $(clone).find("[tag=integral]").val("");
                $(clone).find("#remove_item").html("");
                $(clone).appendTo("#cashier_item");

                //判断商品明细中是否有该商品
                $.each(newArr, function (index, val) {
                    if (row.id == val) {
                        //删除多克隆出来的行
                        $("#cashier_item").find("tr").last("tr").prev("tr").remove();
                        //获取当前明细中的jquery对象数组
                        var arrs = $("input[tag=id]");
                        //遍历数组,拿到dom对象
                        $.each(arrs, function (i, v) {
                            //判断这个对象value值是否和
                            if ($(v).val() == val) {
                                //拿到这个对象的val再+1
                                var number = $(v).closest("tr").find("[tag=number]").val();
                                var unitpPrice = $(v).closest("tr").find("[tag=unitpPrice]").val();
                                $(v).closest("tr").find("[tag=number]").val((parseInt(number) + 1));
                                number = $(v).closest("tr").find("[tag=number]").val();
                                //单个商品小计
                                var amount = $(v).closest("tr").find("[tag=amount]").val((parseInt(unitpPrice)*parseInt(number))*1);
                                //计算总价
                                var allprice = 0;
                                var prices = $("[tag=amount]");
                                for (var i = 0; i < prices.size()-1; i++) {
                                    var price = $(prices[i]).val();
                                    var intPrice = parseInt(price);
                                    allprice = allprice + intPrice;
                                }
                                $("#totalNum").html(allprice);

                            }
                        });
                    }else{
                        var allprice = 0;
                        var prices = $("[tag=amount]");
                        for (var i = 0; i < prices.size()-1; i++) {
                            var price = $(prices[i]).val();
                            var intPrice = parseInt(price);
                            allprice = allprice + intPrice;
                        }
                        $("#totalNum").html(allprice);
                    }
                });
                //获取number输入框的jquery对象
                var nums = $($("#cashier_item").find("[tag=number]"));
                //转成装number输入框的值的数组
                var num = $.map(nums, function (input) {
                    return $(input).val();
                });
               /* $.each(num,function (i,v) {

                });*/
            }
        }
    );
    //失去焦点时计算
    $("[tag=number]").blur(function () {
        var prices = $("[tag=amount]");
        var numbers = $("[tag=number]");
        for (var i = 0; i < prices.size()-1; i++) {
            if(isNaN(parseInt($(numbers[i]).val()))){
                $.messager.alert("温馨提示!", "请输入正确的数字格式!", "info", function () {
                    $(numbers).val("");
                });
                $(numbers).val("");
            }
        }
        if(parseInt($(numbers).val())!=0&&$(numbers).val()!=null){
            //计算小计
            var crrent = $(this).closest("tr");
            var p = crrent.find("[tag=unitpPrice]").val();
            var n = crrent.find("[tag=number]").val();
            crrent.find("[tag=amount]").val((p * n));
            //计算总额
            var allprice = 0;
            for (var i = 0; i < prices.size()-1; i++) {
                var price = $(prices[i]).val();
                var intPrice = parseInt(price);
                allprice = allprice + intPrice;
            }
            $("#totalNum").html(allprice);
        }else{
            $("[tag=amount]").val(0);
            $("#totalNum").html(0);
        }
    });
    //删除
    $(".btn_deleteItem").click(function () {
        var currentTr = $(this).closest("tr");
        if ($("#cashier_item tr").length == 1) {
            $(clone).find("[tag=goodsMark]").val("");
            $(clone).find("[tag=id]").val("");
            $(clone).find("[tag=name]").val("");
            $(clone).find("[tag=unitpPrice]").val("");
            $(clone).find("[tag=number]").val("");
            $(clone).find("[tag=amount]").val("");
            $(clone).find("[tag=purchasingPrice]").val("");
            $(clone).find("[tag=minPrice]").val("");
            $(clone).find("[tag=initialInventory]").val("");
            $(clone).find("[tag=integral]").val("");
            $("#totalNum").html(0);
        } else {
            currentTr.remove();
        }
    });

    //选择会员按钮
    $("#selectmember").click(function () {
        $("#dd").dialog("open");
    })
    //点击会员弹窗
    $("#member").datagrid({
        onClickRow: function (index, row) {
            row["member.id"] = row.id;
            row["member.memberNum"] = row.memberNum;
            row["member.name"] = row.name;
            row["member.balance"] = row.balance;
            row["member.points"] = row.points;
            $("#itemforms").form("load", row);
            $("#dd").dialog("close");
        }
    });
    //付款
    $("#payment").click(function () {
        if ($("#inputId").val() == "") {
            $.messager.alert("温馨提示!", "请选择一个会员!", "info", function () {
                return;
            });
            return;
        }
        if ($("[tag=id]").val() == "") {
            $.messager.alert("温馨提示!", "请选择商品!", "info", function () {
                return;
            });
            return;
        }
        //判断
        if (isNaN(parseInt($("[tag=number]").val()))) {
            $.messager.alert("温馨提示!", "请输入商品数量!", "info", function () {
                return;
            });
            return;
        }
        //判断会员的余额是否足够付款
        var allprice = 0;
        var prices = $("[tag=amount]");
        for (var i = 0; i < prices.size()-1; i++) {
            var price = $(prices[i]).val();
            var intPrice = parseInt(price);
            allprice = allprice + intPrice;
        }
        console.log(parseInt($("#memberBalance").val()));
        console.log(allprice);
        if(parseInt($("#memberBalance").val())<allprice){
            $.messager.confirm("温馨提示!", "余额以不足,请充值!", function (r) {
                if(r){
                    $.messager.confirm("温馨提示!", "是否要为您跳转到充值页面", function (r) {
                        if(r){
                            location.href="/memberTopUp/view.do";
                        }else{
                            window.location.reload();
                        }
                    })
                }
            });
            return;
        }

        $.messager.confirm("温馨提示!", "确定要付款吗?", function (r) {
            if (r) {
                $.each($("#cashier_item tr"), function (index, ele) {
                    $(ele).find("[tag=goodsMark]").prop("name", "items[" + index + "].product.goodsMark");
                    $(ele).find("[tag=id]").prop("name", "items[" + index + "].product.id");
                    $(ele).find("[tag=name]").prop("name", "items[" + index + "].product.name");
                    $(ele).find("[tag=unitpPrice]").prop("name", "items[" + index + "].unitpPrice");
                    $(ele).find("[tag=number]").prop("name", "items[" + index + "].number");
                    $(ele).find("[tag=purchasingPrice]").prop("name", "items[" + index + "].product.purchasingPrice");
                    $(ele).find("[tag=minPrice]").prop("name", "items[" + index + "].product.minPrice");
                    $(ele).find("[tag=initialInventory]").prop("name", "items[" + index + "].product.initialInventory");
                    $(ele).find("[tag=integral]").prop("name", "items[" + index + "].integral");
                });
                $("#itemforms").form("submit", {
                    url: "/checkoutComeBill/saveOrUpdate.do",
                    success: function (data) {
                        data = $.parseJSON(data);
                        if (data.success) {
                            console.log(data.success);
                            $.messager.alert("温馨提示!", "支付成功!", "info", function () {
                                window.location.reload();
                            });
                        } else {
                            $.messager.alert("温馨提示!", data.msg, "info", function () {
                                window.location.reload();
                            });
                        }
                    }
                });
            }
        });
    });

    //挂单
    $("#waitPayment").click(function () {
        if ($("#inputId").val() == "") {
            $.messager.alert("温馨提示!", "请选择一个会员!", "info", function () {
                return;
            });
            return;
        }
        $.messager.confirm("温馨提示!", "确定要挂单吗!", function (r) {
            if (r) {
                $.each($("#cashier_item tr"), function (index, ele) {
                    $(ele).find("[tag=goodsMark]").prop("name", "items[" + index + "].product.goodsMark");
                    $(ele).find("[tag=id]").prop("name", "items[" + index + "].product.id");
                    $(ele).find("[tag=name]").prop("name", "items[" + index + "].product.name");
                    $(ele).find("[tag=unitpPrice]").prop("name", "items[" + index + "].unitpPrice");
                    $(ele).find("[tag=number]").prop("name", "items[" + index + "].number");
                    $(ele).find("[tag=purchasingPrice]").prop("name", "items[" + index + "].product.purchasingPrice");
                    $(ele).find("[tag=minPrice]").prop("name", "items[" + index + "].product.minPrice");
                    $(ele).find("[tag=initialInventory]").prop("name", "items[" + index + "].product.initialInventory");
                    $(ele).find("[tag=integral]").prop("name", "items[" + index + "].integral");
                });
                $("#itemforms").form("submit", {
                    url: "/checkoutComeBill/waitPayment.do",
                    success: function (data) {
                        data = $.parseJSON(data);
                        if (data.success) {
                            console.log(data.success);
                            $.messager.alert("温馨提示!", "挂单成功!", "info", function () {
                                window.location.reload();
                            });
                        } else {
                            $.messager.alert("温馨提示!", "挂单失败!", "info",function () {
                                window.location.reload();
                            });
                        }
                    }
                });
                /*$.messager.alert("温馨提示!", "挂单成功!", "info",function () {
                    window.location.reload();
                });*/
            }
        });
    })

    //取单
    $("#selectOrders").click(function () {
        $("#dd1").dialog("open");
    })
    //点击取单
    $("#waitPaymentMember").datagrid({
        onClickRow: function (index, row) {
            console.log(row);
            //判断是否重复选择
            var val = $("#inputId").val();
            if (val == row.member.id) {
                $.messager.alert("温馨提示!", "不要重复选择!", "info", function () {
                    return;
                });
                return;
            }
            row["member.id"] = row.member.id;
            row["member.memberNum"] = row.member.memberNum;
            row["member.name"] = row.member.name;
            row["member.balance"] = row.member.balance;
            row["member.points"] = row.member.points;
            $("#itemforms").form("load", row);
            //隐藏余额列
            $('#waitPaymentMember').datagrid('hideColumn', 'memberBalance');
            $('#waitPaymentMember').datagrid('hideColumn', 'memberPoints');

            //判断表格里是否有数据,有数据清空
            if ($("[tag=goodsMark]").val() != null) {
                var clone = $("#cashier_item tr:first").clone(true);
                $("#cashier_item").html("");
                $(clone).find("[tag=goodsMark]").val("");
                $(clone).find("[tag=id]").val("");
                $(clone).find("[tag=name]").val("");
                $(clone).find("[tag=unitpPrice]").val("");
                $(clone).find("[tag=number]").val("");
                $(clone).find("[tag=amount]").val("");
                $(clone).find("[tag=purchasingPrice]").val("");
                $(clone).find("[tag=minPrice]").val("");
                $(clone).find("[tag=initialInventory]").val("");
                $(clone).find("[tag=integral]").val("");
                $(clone).appendTo("#cashier_item");
            }
            //从挂单表中取出挂单的数据展现在页面
            $.post("/checkoutComeBill/selectbyMemberId.do", {"id": row.member.id}, function (datas) {
                if (datas != null) {
                    //遍历
                    $.each(datas, function (i, data) {
                        //将数添到表中
                        $("#cashier_item").find("tr").last("tr").find("[tag=goodsMark]").val(data.product.goodsMark);
                        $("#cashier_item").find("tr").last("tr").find("[tag=id]").val(data.product.id);
                        $("#cashier_item").find("tr").last("tr").find("[tag=purchasingPrice]").val(data.product.purchasingPrice);
                        $("#cashier_item").find("tr").last("tr").find("[tag=minPrice]").val(data.product.minPrice);
                        $("#cashier_item").find("tr").last("tr").find("[tag=initialInventory]").val(data.product.initialInventory);
                        $("#cashier_item").find("tr").last("tr").find("[tag=integral]").val(data.product.integral);
                        $("#cashier_item").find("tr").last("tr").find("[tag=name]").val(data.product.name);
                        var p = $("#cashier_item").find("tr").last("tr").find("[tag=unitpPrice]").val(data.product.unitpPrice);
                        $("#cashier_item").find("tr").last("tr").find("[tag=number]").val(data.number);
                        $("#cashier_item").find("tr").last("tr").find("[tag=amount]").val(p.val());

                        //克隆行
                        var clone = $("#cashier_item tr:first").clone(true);
                        $(clone).find("[tag=goodsMark]").val("");
                        $(clone).find("[tag=id]").val("");
                        $(clone).find("[tag=name]").val("");
                        $(clone).find("[tag=unitpPrice]").val("");
                        $(clone).find("[tag=number]").val("");
                        $(clone).find("[tag=amount]").val("");
                        $(clone).find("[tag=purchasingPrice]").val("");
                        $(clone).find("[tag=minPrice]").val("");
                        $(clone).find("[tag=initialInventory]").val("");
                        $(clone).find("[tag=integral]").val("");
                        $(clone).appendTo("#cashier_item");
                    });
                }

            }, "json");
            $("#dd1").dialog("close");
        }
    });

    //设置单号
    $.post("/checkoutComeBill/addOdd.do",{"sn":"20000"},function(data){
        console.log(data);
        data = $.parseJSON(data);
        $("#salesOdd").val(data);
    },"json");


    $('#product').datagrid({
        onLoadSuccess:function (data) {
            //隐藏列
            $('#product').datagrid('hideColumn','goodsMark');
            $('#product').datagrid('hideColumn','id');
            $('#product').datagrid('hideColumn', 'purchasingPrice');
            /*$('#product').datagrid('hideColumn', 'minPrice');
            $('#product').datagrid('hideColumn', 'initialInventory');
            $('#product').datagrid('hideColumn', 'integral');*/
        }
    });
})

//显示会员等级
function state(grade) {
    if (grade) {
        return grade.name;
    }
}

//显示会员信息
function memberId(value, row, index) {
    if (row.member) {
        return row.member.id;
    }
}

function memberNumber(value, row, index) {
    if (row.member) {
        return row.member.memberNum;
    }
}

function memberName(value, row, index) {
    if (row.member) {
        return row.member.name;
    }
}

function memberBalance(value, row, index) {
    if (row.member) {
        return row.member.balance;
    }
}

function memberPoints(value, row, index) {
    if (row.member) {
        return row.member.points;
    }
}
function searchs() {
    var keyword = $("#keyword").val();
    $("#product").datagrid("load",{"keyword":keyword});
}
function fruits() {
    $("#product").datagrid("load",{"unit":1});
}
function edibleoil() {
    $("#product").datagrid("load",{"unit":5});
}
function drinks() {
    $("#product").datagrid("load",{"unit":3});
}
function food() {
    $("#product").datagrid("load",{"unit":2});
}
function vegetables() {
    $("#product").datagrid("load",{"unit":4});
}