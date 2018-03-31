var stateChildern = null;
var getValue = 0;
var addValue = 0;
var totalValue = 0;
var subValue = 0;
$(function () {
    //显示左边的会员列表
    var member_load_datagrid = $("#member_load_datagrid");
    //显示会员充值的明细
    var topup_datagrid = $("#topup_datagrid");
    var member_loadData_form = $("#member_loadData_form");
    var name_textbox = $("#name_textbox");
    var grade_textbox = $("#grade_textbox");
    var birthday_textbox = $("#birthday_textbox");
    var balance_textbox = $("#balance_textbox");
    var points_textbox = $("#points_textbox");
    var id_hidden = $("#id_hidden");
    var ff = $("#ff");
    var btn = $("#btn");
    var addway = $("#addway");
    var countResult = $("#countResult");
    var totalResult = $("#totalResult");
    var stateResult = $("#stateResult");
    var addWay = $("#addWay");
    var p = $("#p");
    var discounts = $("#discounts");
    var addbalance_textbox = $("#addbalance_textbox");
    var checkinput = $("#checkinput");
    /*合计充值的id*/
    var totalMoney = $("#totalMoney");
    /*左边的表格,显示会员*/
    member_load_datagrid.datagrid({
        width: 400,
        height: 250,
        fitColumns: true,
        remoteSort: false,
        singleSelect: true,
        url: '/member/selectAll.do',
        columns: [[

            {field: 'name', title: '会员名', width: 80, align: 'center'},
            {
                field: 'grade.id', title: '会员等级', width: 80, align: 'center', formatter: function (index, row, value) {
                    return row.grade ? '<span style="color: #CC2222">' + row.grade.name + '</span>' : row.grade;
                }
            },
            {field: 'phone', title: '电话', width: 80, align: 'center'},
            {field: 'balance', title: '储值', width: 80, align: 'center'}
        ]],
        onClickRow: function (index, row) {
            // console.log(row);

            name_textbox.textbox("setValue", row.name);
            points_textbox.textbox("setValue", row.points);
            balance_textbox.textbox("setValue", row.balance);
            birthday_textbox.textbox("setValue", row.birthday);
            grade_textbox.textbox("setValue", row.grade.name);
            birthday_textbox.textbox("setValue", row.birthday);
            id_hidden.val(row.id);
            topup_datagrid.datagrid("load", {id: row.id});
        }
    });
//充值充次事件点击
    $("a[data-state]").click(function () {
        // console.log($(this).data("state"));
        if ($(this).data("state") == 0) {
//隐藏
            document.getElementById('totalResult').style.display = 'none';
//显示
            document.getElementById('countResult').style.display = '';
        } else {
            //隐藏
            document.getElementById('countResult').style.display = 'none';
//显示
            document.getElementById('totalResult').style.display = '';
        }

    })

    /*充值,扣费,退还点击事件*/
    $("input[type='radio']").click(function () {
        $("input[type='radio']").prop("checked", function (i, val) {
                if (val) {
                    if (this.value == -1) {
                        addbalance_textbox.textbox({
                            label: '退费金额:',
                            prompt: '请输入退费金额'
                        });
                        // ff.form("clear");
                        stateChildern = discounts.detach();
                        $(".easyui-textbox").textbox("setValue", " ");
                        //隐藏
                        document.getElementById('addWay').style.display = 'none';
                        totalMoney.html("¥0.00");

                    } else if (this.value == 0) {
                        addbalance_textbox.textbox({
                            label: '退还金额:',
                            prompt: '请输入退还金额'
                        });

                        stateChildern = discounts.detach();
                        $(".easyui-textbox").textbox("setValue", " ");
                        //隐藏


                        document.getElementById('addWay').style.display = 'none';
                        totalMoney.html("¥0.00");
                    } else {
//显示
                        $(stateChildern).insertAfter(p);
                        $(".easyui-textbox").textbox("setValue", " ");
                        document.getElementById('addWay').style.display = '';
                        totalMoney.html("¥0.00");

                    }
                }
            }
        );
    })

    /*提交充值表单*/
    btn.click(function () {
        //判断当选择了会员后才能进行充值操作
        if (!id_hidden.val()) {
            $.messager.alert("提示", "请选择会员进行充值", "warning");
            return;
        }
        if ($("[data-print]").prop("checked")) {
            $.messager.show({
                title: '提示',
                msg: '打印机已没纸,请添加',
                timeout: 5000,
                showType: 'show'
            });
        }
        ff.form("submit", {
            url: '/memberTopUp/saveOrUpdate.do',
            onSubmit: function (param) {
                param.state = 1;
                $("a[data-state]").click(function () {
//设置充值还是充次
                    param.state = $(this).data("state");
                })

            },
            success: function (data) {
                data = $.parseJSON(data);
                if (data.success) {
                    $.messager.alert("温馨提示", "操作成功", "info", function () {
                        totalMoney.html("¥0.00");
                        ff.form("clear");
                        member_load_datagrid.datagrid("reload");
                        var d = member_load_datagrid.datagrid("getSelected");
                        topup_datagrid.datagrid("load",{id:d.id});


                    })
                } else {
                    $.messager.alert("温馨提示", data.msg, "info");
                    ff.form("clear");
                    member_load_datagrid.datagrid("reload");
                    totalMoney.html("¥0.00");
                }
            }
        })
    });


    /*焦点事件,判断充值的input和赠送金额的input
    * */
    var base = 0;
    $("[data-cmd]").textbox({
        onChange: function (newValue, oldValue) {
            dd = $(this);
            var d = $(this).data("cmd");

            // $("input[type='radio']").prop("checked", function (i, val) {
            //     console.log(this.value);
            //     if (val) {
            //         if (this.value == 1) {
            //             /*赠送的金额*/
            //
            //
            //         }else{
            //
            //         }
            //     }
            // })
            $("#chooseway").children("input").each(function (i, v) {
                if ($(v).prop("checked")) {
                    /*等于1表示充值*/
                    if (v.value == 1) {

                        if (d == 'add') {
                            var v = newValue;
                            addValue = v;
                            if (v >= 5000) {
                                $("#msg").css("color", "red");
                                getValue = parseFloat(getValue == null ? 0 : getValue) + parseFloat(500);
                                base = parseFloat(500);
                                dd.data("cmd", "");
                            }

                        }
                        if (d == 'give') {
                            getValue = base + parseFloat(newValue);
                        }
                        if (d == '') {
                            if (newValue < 5000) {

                                if (base == 500) {
                                    getValue = getValue - 500;
                                    base = 0;
                                    $("#msg").css("color", "black");
                                }
                                dd.data("cmd", "add");
                            }
                            addValue = newValue;
                        }
                        totalValue = (parseFloat(addValue == null ? 0 : addValue) + parseFloat(getValue == null ? 0 : getValue)).toFixed(2);
                        totalMoney.html("¥" + (totalValue) + "(赠送" + getValue + "金额)");
                    }
                    else if (v.value == -1 || v.value == 0) {

                        console.log(this);
                        /*扣费或者退还的操作*/
                        var row = $(this);
                        var data = member_load_datagrid.datagrid("getSelected");
                        if (!data) {
                            $.messager.alert("提示", "请选择会员", "error")
                            return;
                        }
                        if (data.balance < newValue) {
                            $.messager.alert("提示", "超出会员的余额,请重新输入", "error", function () {
                                addbalance_textbox.textbox("setText", data.balance);
                            })
                        }
                        // console.log(row.textbox("getText"));
                        subValue = newValue;
                        if (data.balance < newValue) {
                            subValue = data.balance;
                        }
                        totalMoney.html("¥" + (parseFloat(subValue).toFixed(2)))

                    }

                }

            })

            //var totalValue = (parseFloat(addValue+totalValue)+parseFloat(getValue);


        }
    })

    topup_datagrid.datagrid({
        width: 400,
        height: 150,
        fit: true,
        fitColumns: true,
        remoteSort: false,
        singleSelect: true,
        url: '/memberTopUp/selecToptItemByMemberId.do',
        columns: [[

            {
                field: 'state', title: '操作类型', width: 80, align: 'center',
                formatter: function (value, row, index) {
                    if (value == 1) {
                        return '充值';
                    } else {
                        return '充次';
                    }
                }
            },
            {
                field: 'grade.id', title: '支付类型', width: 80, align: 'center', formatter: function (index, row, value) {
                    return row.grade ? '<span style="color: #CC2222">' + row.grade.name + '</span>' : row.grade;
                }
            },
            {field: 'addbalance', title: '充值金额', width: 80, align: 'center'},
            {field: 'give', title: '赠送金额', width: 80, align: 'center'},
            {
                field: 'member.balance', title: '储值', width: 80, align: 'center',
                formatter: function (value, row, index) {
                    if (row.member.balance) {
                        return row.member.balance;
                    } else {
                        return row.member
                    }
                    console.log(value, row, index);

                }
            },
            {field: 'toptime', title: '充值时间', width: 80, align: 'center'},
            {field: 'intro', title: '备注', width: 80, align: 'center'}
        ]]
    })
})