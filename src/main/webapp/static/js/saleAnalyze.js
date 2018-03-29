$(function () {

    var saleAanalyze_datagrid = $("#saleAanalyze_datagrid");
    var selectCondition=null;
    var methodObj = {
        //查询全部
        all:function () {
            $("#show_Other").css("display","none");//show的display属性设置为none（隐藏）
            saleAanalyze_datagrid.datagrid("load",{"today":null});
        },
        //按照今日查询
        today:function () {
            $("#show_Other").css("display","none");//show的display属性设置为none（隐藏）
            saleAanalyze_datagrid.datagrid("load",{"today":1});
            selectCondition="today=1"
        },
        //按照昨日查询
        week:function () {
            $("#show_Other").css("display","none");//show的display属性设置为none（隐藏）
            saleAanalyze_datagrid.datagrid("load",{"week":7});
            selectCondition="week=7";
        },
        //按照本月查询
        month:function () {
            $("#show_Other").css("display","none");//show的display属性设置为none（隐藏）
            saleAanalyze_datagrid.datagrid("load",{"month":30});
            selectCondition="month=30";
        },
        //点击“其他”按钮，控制开始日期输入框、结束日期输入框、search按钮的显示与隐藏
        other:function () {
            if($("#show_Other").css("display")=='none'){//如果show是隐藏的

                $("#show_Other").css("display","block");//show的display属性设置为block（显示）

            }else{//如果show是显示的

                $("#show_Other").css("display","none");//show的display属性设置为none（隐藏）

            }
        },
        //点击搜索按钮，按照输入的日期查询
        searchs:function () {
            //获取2个输入框的内容，让saleAanalyze_datagrid重新加载
            var startDate=$("#startDate").val();
            var endDate=$("#endDate").val();
            saleAanalyze_datagrid.datagrid("load",
                {"beginDate":startDate,"endDate":endDate});
            selectCondition={"beginDate":startDate,"endDate":endDate};
        },
        //点击导出
        export:function () {
            console.log(selectCondition);
            window.open("/saleAnalyze/exportXls.do?selectCondition");
        }
    };

    //给所有具有data-btn属性的A标签统一绑定点击事件
    $("a[data-btn]").click(function () {
        var btn = $(this).data("btn");
        methodObj[btn]();
    });

    //saleAanalyze_datagrid的数据表格:产品分析
    saleAanalyze_datagrid.datagrid({
        fitColumns: true,
        pagination:true,
        striped: true,
        url: '/saleAnalyze/queryByDate.do',
        singleSelect: true,
        onLoadSuccess: function (data) {
            if (data.total == 0) {
                //添加一个新数据行，第一列的值为你需要的提示信息，然后将其他列合并到第一列来，注意colspan参数为columns配置的总列数
                $(this).datagrid('appendRow', { itemid: '<div style="text-align:center;color:red">没有相关记录！</div>' }).datagrid('mergeCells',
                    { index: 0, field: 'itemid', colspan: 10 });
                //隐藏分页导航条，这个需要熟悉datagrid的html结构，直接用jquery操作DOM对象，easyui datagrid没有提供相关方法隐藏导航条
                $(this).closest('div.datagrid-wrap').find('div.datagrid-pager').hide();
            }
            //如果通过调用reload方法重新加载数据有数据时显示出分页导航容器
            else $(this).closest('div.datagrid-wrap').find('div.datagrid-pager').show();
        },
        columns: [[
            {field: 'name', title: '商品名称', width: 100},
            {field: 'sn', title: '单号', width: 100},
           {field: 'member', title: '消费对象', width: 100,formatter: function(row){
                    if(row){
                        return value?'会员':'散客';
                    }

               }},
            {field: 'salePrice', title: '商品单价', width: 100},
            {field: 'type', title: '折扣类型', width: 100,formatter: function(row){
                    if(row){
                        return '无折扣';
                    }

                }
            },
            {field: 'number', title: '数量', width: 100},
            {field: 'saleAmount', title: '销售金额', width: 100},
            {field: 'pay', title: '支付方式', width: 100,formatter:function (row) {
                    if(row){
                        return '现金';
                    }

                }},

            {field: 'home', title: '消费店铺', width: 100,formatter:function (row) {
                    if(row){
                        return '德客便利店';
                    }
                }},
            {field: 'vdate', title: '消费时间', width: 100,formatter: formatDatebox}
        ]]

    });

});

//扩展Date的日期转换功能
Date.prototype.format = function (format) {
    var o = {
        "M+": this.getMonth() + 1, // month
        "d+": this.getDate(), // day
        "h+": this.getHours(), // hour
        "m+": this.getMinutes(), // minute
        "s+": this.getSeconds(), // second
        "q+": Math.floor((this.getMonth() + 3) / 3), // quarter
        "S": this.getMilliseconds()
        // millisecond
    }
    if (/(y+)/.test(format))
        format = format.replace(RegExp.$1, (this.getFullYear() + "")
            .substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(format))
            format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));
    return format;
}
function formatDatebox(value) {
    if (value == null || value == '') {
        return '';
    }
    var dt;
    if (value instanceof Date) {
        dt = value;
    } else {
        dt = new Date(value);
    }

    return dt.format("yyyy-MM-dd"); //扩展的Date的format方法(上述插件实现)
}

