$(function () {
    //自定义设置datagrid,把表格给成现实图片
    var cardview = $.extend({}, $.fn.datagrid.defaults.view, {
        renderRow: function (target, fields, frozen, rowIndex, rowData) {
            var cc = [];
            cc.push("<td colspan=" + fields.length + " style='padding:10px 5px;border:0;'>");
            if (!frozen) {
                var img = rowData.imagePath;
                cc.push('<img src="/static/spaceImgPath/' + (rowIndex + 1) + '.png" style="width:150px;float:left"/>');
                cc.push('<div style="float:left;margin-left:20px;">');
                for (var i = 0; i < fields.length; i++) {
                    if (i == 0 || i == 2) {
                        continue;
                    }
                    var copts = $(target).datagrid('getColumnOption', fields[i]);
                    cc.push('<p><span class="c-label">' + copts.title + ':</span> ' + rowData[fields[i]] + '</p>');

                }
                cc.push('</div>');
            }
            cc.push('</td>');
            return cc.join('');
        }
    });

    $('#product').datagrid({
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
                $("#cashier_item").find("tr").last("tr").find("[tag=name]").val(row.name);
                var p = $("#cashier_item").find("tr").last("tr").find("[tag=unitpPrice]").val(row.unitpPrice);
                $("#cashier_item").find("tr").last("tr").find("[tag=number]").val(1);
                $("#cashier_item").find("tr").last("tr").find("[tag=amount]").val(p.val());
                //克隆
                var clone = $("#cashier_item tr:first").clone(true);
                $(clone).find("[tag=goodsMark]").val("");
                $(clone).find("[tag=id]").val("");
                $(clone).find("[tag=name]").val("");
                $(clone).find("[tag=unitpPrice]").val("");
                $(clone).find("[tag=number]").val("");
                $(clone).find("[tag=amount]").val("");
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
                                var value = $(v).closest("tr").find("[tag=number]").val();
                                $(v).closest("tr").find("[tag=number]").val((parseInt(value) + 1));
                            }
                        });
                    }
                });
            }
        }
    );

    //删除
    $(".btn_deleteItem").click(function () {
        var currentTr = $(this).closest("tr");
        if ($("#cashier_item tr").length == 1) {
            $(clone).find("[tag=goodsMark]").val("");
            $(clone).find("[tag=id]").val("");
            $(clone).find("[tag=name]").val("");
            $(clone).find("[tag=unitpPrice]").val("");
            $(clone).find("[tag=number]").val("");
        } else {
            currentTr.remove();
        }
    });

    //计算
    $("[tag=number]").blur(function () {
        var crrent = $(this).closest("tr");
        p = crrent.find("[tag=unitpPrice]").val();
        n = crrent.find("[tag=number]").val();
        crrent.find("[tag=amount]").val((p * n));
    });

    //隐藏列
    $('#product').datagrid('hideColumn', 'goodsMark');
    $('#product').datagrid('hideColumn', 'id');

    //计算总价
    var allprice = 0;//总价格
    var allnum = 0;//总数量
    $("input").blur(function () {
        var prices = $("[tag=amount]");
        $.each(prices, function (i, v) {
            var price = $(v).val();
            console.log(price);
            var intPrice = parseInt(price);
            allprice = allprice + intPrice;
        })
        console.log(allprice);

    });


})

//计算总价
function setTotal() {
    var allprice = 0;//总价格
    var allnum = 0;//总数量
    $("#cashier_item").each(function () {

    })
    $(".allprice").html(allprice.toFixed(2));
    $(".allnum").html(allnum);
}