$(function () {
    var memberSimpleInfo = $("#memberSimpleInfo,#memberSimpleInfo_gift");
    var memberInfo = $("#memberInfo");
    var giftList = $("#giftList");
    var giftEdit = $("#giftEdit");
    var giftDialog = $("#gift_dialog");
    var giftEditForm = $("#gift_edit_form");
    var exchangeRecord = $("#exchange_record,#memberInfo_gift");
    var giftList4choose = $("#giftList4choose");
    giftEdit.dialog({
        width: 330,
        height: 400,
        closed: true,
        modal: true,
        buttons: '#gift_edit_buttons',
        onClose: function () {

            giftEditForm.form("clear");//清空表单数据
        }

    });
    giftDialog.dialog({
        width: 600,
        height: 400,
        closed: true,
        modal: true,
        buttons: '#choose_buttons',
        onClose: function () {

            $("#giftList4choose").datagrid("clearChecked");//清空原来的选择
        }

    });
    memberSimpleInfo.datagrid({
        url: '/member/listByKeyword.do',
        columns: [[
            {field: 'memberNum', width: 120, align: 'center', title: '会员卡号'},
            {field: 'name', width: 110, align: 'center', title: '会员姓名'},
            {
                field: 'grade', width: 87, align: 'center', title: '会员等级', formatter: function (grade) {
                return grade ? grade.name : grade;
            }
            },
            {field: 'phone', width: 200, align: 'center', title: '电话'},
            {field: 'balance', width: 100, align: 'center', title: '余额'}
        ]],
        onLoadSuccess: function () {
            memberSimpleInfo.datagrid("selectRow", 0)
        },
        onSelect: function (index, row) {
            $("#memberName,#memberName_gift").val(row.name);
            $("#memberNum,#memberNum_gift").val(row.memberNum);
            $("#grade,#grade_gift").val(row.grade.name);
            $("#points,#points_gift").val(row.points);
            $("#balance,#balance_gift").val(row.balance);
            $("#birthday,#birthday_gift").html(row.birthday);
            $("#hiddenMemberId,#hiddenMemberId_gift").val(row.id);
            $("#comsumPoints,#comsumPoints_gift").val(row.consumePoints);
            var sumPoints = 0;
            // var comsumPoints = 0;
            $.each(row.bonusPointRecord, function (index, item) {
                if (item.amount >= 0) {
                    sumPoints = sumPoints + item.amount;
                }
                /*else {
                                   comsumPoints = comsumPoints + (item.amount - item.amount * 2)
                               }*/

            });
            $("#sumPoints,#sumPoints_gift").val(sumPoints);
            // $("#comsumPoints").html(comsumPoints);


            memberInfo.datagrid({
                url: '/bonusPointRecord/selectByMemberId.do?memberId=' + row.id + '',
                columns: [[
                    {
                        field: 'member', width: 120, align: 'center', title: '会员卡号', formatter: function (member) {
                        return member ? member.memberNum : member;
                    }
                    },
                    {
                        field: 'memberName',
                        width: 110,
                        align: 'center',
                        title: '会员姓名',
                        formatter: function (value, row, index) {
                            return row.member ? row.member.name : row.member;
                        }
                    },
                    {
                        field: 'grade',
                        width: 87,
                        align: 'center',
                        title: '会员等级',
                        formatter: function (value, row, index) {
                            return row.member ? row.member.grade.name : row.member;
                        }
                    },
                    {
                        field: 'optUser', width: 200, align: 'center', title: '操作人员', formatter: function (optUser) {
                        return optUser ? optUser.username : optUser;
                    }
                    },
                    {
                        field: 'type', width: 100, align: 'center', title: '操作类型', formatter: function (type) {
                        return type == 1 ? "增加积分" : "扣除积分";
                    }
                    },
                    {
                        field: 'amount', width: 100, align: 'center', title: '变动数额', formatter: function (amount) {
                        return amount > 0 ? "<span style='color: green'>" + "+" + amount + "</span>" : "<span style='color: red'>" + amount + "</span>";
                    }
                    },
                    {
                        field: 'optDate', width: 100, align: 'center', title: '操作时间', formatter: function (optDate) {
                        return optDate ? timestampToTime(optDate) : optDate;
                    }
                    },
                    {field: 'remark', width: 100, align: 'center', title: '备注'}
                ]]
            });
        }
    });

    exchangeRecord.datagrid({
        url: '/exchangeRecord/list.do',
        columns: [[
            {
                field: 'members', width: 80, align: 'center', title: '会员卡号', formatter: function (value, row) {
                return value[0] ? value[0].memberNum : value[0];
            }
            },
            {
                field: 'memberName', width: 100, align: 'center', title: '会员名称', formatter: function (value, row) {
                return row.members[0] ? row.members[0].name : row.members[0];
            }
            },
            {
                field: 'gift', width: 80, align: 'center', title: '礼品名称', formatter: function (value) {
                return value ? value.name : value;
            }
            },
            {
                field: 'number', width: 80, align: 'center', title: '兑换数量'
            },
            {field: 'costPoints', width: 250, align: 'center', title: '消费积分'},
            {field: 'consumeStore', width: 60, align: 'center', title: '消费店铺'},
            {
                field: 'optUser', width: 60, align: 'center', title: '操作人员', formatter: function (value) {
                return value ? value.username : value;
            }
            },
            {
                field: 'exchangeDate', width: 60, align: 'center', title: '兑换日期', formatter: function (value) {
                return value ? timestampToTime(value) : value;
            }
            }
        ]]

    });
    giftList4choose.datagrid({
        onDblClickRow: function () {
            chooseGift();
        }
    });

    //将时间戳转换为日期格式
    function timestampToTime(timestamp) {
        var date = new Date(timestamp);//时间戳为10位需*1000，时间戳为13位的话不需乘1000
        Y = date.getFullYear() + '-';
        M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1) + '-';
        D = date.getDate() + ' ';
        return Y + M + D;
    }

    var methodObj = {
        advancedSearch: function () {
            var keyword = $("#keyword").textbox("getValue");
            memberSimpleInfo.datagrid("load", {keyword: keyword});
        },
        advancedSearch_gift: function () {
            var keywordGift = $("#keyword_gift").textbox("getValue");
            memberSimpleInfo.datagrid("load", {keyword: keywordGift});
        },
        searchExchangeRecord: function () {
            var exchangekeyword = $("#exchange_keyword").textbox("getValue");
            var beginDate = $("#beginDate").textbox("getValue");
            var endDate = $("#endDate").textbox("getValue");
            exchangeRecord.datagrid("load", {keyword: exchangekeyword, beginDate: beginDate, endDate: endDate});
        },
        searchGifts: function () {
            var giftKeyword = $("#giftKeyword").textbox("getValue");
            giftList.datagrid("load", {keyword: giftKeyword});
        },
        changePoint: function () {
            var memberId = $("#hiddenMemberId").val();
            var type = $("[name='type']:checked").val();
            var amount = $("[name='amount']").val();
            var remark = $("#remark").val();
            if (memberId) {
                if (!amount) {
                    $.messager.alert("温馨提示", "请输入要改变的金额", "info");
                    return;
                }
                $.post("/bonusPointRecord/saveOrUpdate.do", {
                    'member.id': memberId,
                    type: type,
                    amount: amount,
                    remark: remark
                }, function (data) {
                    if (data.success) {
                        $.messager.alert("温馨提示", "操作成功", "info");
                        //刷新积分记录表
                        memberInfo.datagrid("reload");

                    } else {
                        $.messager.alert("温馨提示", data.msg, "info");
                    }
                }, "json")
            } else {
                $.messager.alert("温馨提示", "请先选择要操作的会员", "info");
            }
        },
        clearPoints: function () {
            var memberId = $("#hiddenMemberId").val();
            if (memberId) {
                $.messager.confirm("温馨提示", "请确定将当前会员的所有积分清零吗?", function (r) {
                    if (r) {
                        $.post("/member/clearPoints.do", {
                            'id': memberId
                        }, function (data) {
                            if (data.success) {
                                $.messager.alert("温馨提示", "操作成功", "info");
                            } else {
                                $.messager.alert("温馨提示", data.msg, "info");
                            }
                        }, "json");
                        //刷新积分记录表
                        memberInfo.datagrid("reload");
                    }
                });

            } else {
                $.messager.alert("温馨提示", "请先选择要操作的会员", "info");
            }
        },
        removeData: function () {
            //判断用户是否选择数据
            var row = giftList.datagrid("getSelected");
            if (row) {
                $.messager.confirm("温馨提示", "您确定将当前礼品" + row.name + "删除吗", function (r) {
                    if (r) {
                        $.get("/gift/delete.do", {id: row.id}, function (data) {
                            if (data.success) {
                                $.messager.alert("温馨提示", "操作成功", "info");
                                giftList.datagrid("reload");
                            } else {
                                $.messager.alert("温馨提示", data.msg, "info");
                            }
                        }, "json")
                    }
                })
            } else {
                $.messager.alert("温馨提示", "请先选择您要操作的数据", 'info');
            }
        },
        addData: function () {
            //设置标题
            giftEdit.dialog("setTitle", "新增礼品");
            giftEdit.dialog('open');
        },
        editData: function () {
            //判断用户是否选择数据
            var row = giftList.datagrid("getSelected");
            if (row) {
                //设置标题
                giftEdit.dialog("setTitle", "修改礼品");
                //数据回显
                giftEditForm.form("load", row);
                giftEdit.dialog('open');
            } else {
                $.messager.alert("温馨提示", "请先选择您要操作的数据", 'info');
            }

        },
        chooseGift: function () {
            //判断当前用户的积分是否为0
            var points = $("#points_gift").html();
            if (points == 0) {
                $.messager.alert("温馨提示", "当前用户积分为0", "info");
                return;
            }
            giftDialog.dialog("setTitle", "选择礼品");
            giftDialog.dialog('open');
        },
        cancelDialog: function () {

            giftEdit.dialog('close');
        },
        cancelChoose: function () {

            giftDialog.dialog('close');
        },
        submitChoose: function () {
            chooseGift();
        },
        conformExchange: function () {
            var memberId = $("#hiddenMemberId_gift").val();
            //先输入会员密码进行校验
            $.messager.prompt('提示信息', '请输入当前会员的密码：', function (password) {
                if (password) {

                    $.post('/member/checkPass.do', {password: password, id: memberId}, function (data) {
                        if (!data.success) {

                            methodObj.conformExchange();
                            $.messager.alert("温馨提示", data.msg, "info");
                            return;
                        } else {
                            //判断用户积分是否足够
                            var neededPoints = $("#neededPoints_num").html();
                            $.post('/member/checkPoints.do', {points: neededPoints, id: memberId}, function (data) {
                                if (!data.success) {
                                    $.messager.alert("温馨提示", data.msg, "info");
                                    return;
                                } else {
                                    //保存一条记录
                                    var giftId = $("#giftId").val();
                                    var costPoints = $("#neededPoints_num").html();
                                    var number = $("#ss").val();
                                    $.post('/exchangeRecord/save.do', {
                                        'gift.id': giftId,
                                        costPoints: costPoints,
                                        'members[0].id': memberId,
                                        number: number
                                    },function (data) {

                                        if (data.success){
                                            //刷新列表
                                            $("#memberInfo_gift").datagrid("reload");
                                            //让礼品的剩余数量减少
                                            $.post('/gift/updateInventory.do',{id:giftId,number:number},function (data) {
                                                if (!data.success) {
                                                    $.messager.alert("温馨提示",data.msg,"info")
                                                } else {
                                                    //刷新列表
                                                    giftList.datagrid("reload");
                                                    giftList4choose.datagrid("reload");
                                                }
                                            },'json')

                                            $.messager.alert("温馨提示","恭喜您兑换保成功","info");
                                        } else {
                                            $.messager.alert("温馨提示","对不起,操作失败,请联系管理员","info");
                                        }
                                    },'json')


                                }
                            }, 'json')
                        }

                    }, "json");
                }
            });

        },
        deleteChoose: function () {
            $("#giftName").html("");
            $("#needed_point").html("");
            $("#quantity_remain").html("");
            $("#ss").css('border', '0px solid #e5e5e5');
            $("#ss").val("");
            $("#conform_exchange").css('border', '0px solid #e5e5e5');
            $("#operate").html("");
            $("#conform_exchange").css('backgroundColor', 'none');
            $("#conform_exchange").linkbutton('disable');
            $("#neededPoints_text").html("");
            $("#neededPoints_num").html("");
        },
        submitData: function () {
            var url = '/gift/saveOrUpdate.do';
            giftEditForm.form("submit", {
                url: url,
                success: function (data) {
                    data = $.parseJSON(data);
                    if (data.success) {
                        $.messager.alert("温馨提示", "操作成功", 'info', function () {

                            //刷新datagrid列表
                            giftList.datagrid("reload");
                            //关闭当前的dialog窗口
                            methodObj.cancelDialog();
                        });
                    } else {
                        $.messager.alert("温馨提示", data.msg, 'info');
                    }
                }
            })
        }
    }
    $("a[data-cmd]").click(function () {
        var methodName = $(this).data("cmd");
        methodObj[methodName]();
    });
    //改变数量所需积分改变
    $("#ss").change(function () {
        var point = $("#needed_point").html();
        var num = $("#ss").val();
        var total = num * point;
        $("#neededPoints_num").html(total);
    });

    //抽取选择礼品操作的方法
    function chooseGift() {
        var row = $("#giftList4choose").datagrid("getSelected");
        if (row.inventory == 0) {
            $.messager.alert("温馨提示", "当前礼品剩余数量为零,请重新选择", "info");
            return;
        }

        $("#giftName").html(row.name);
        $("#needed_point").html(row.points);
        $("#quantity_remain").html(row.inventory);
        $("#ss").css('border', '1px solid #e5e5e5');
        $("#ss").val(1);
        $("#conform_exchange").css('backgroundColor', 'green');
        $("#conform_exchange").linkbutton('enable');
        $("#neededPoints_text").html("所需积分:");
        $("#neededPoints_num").html(row.points);
        $("#operate").html("删除");
        $("#giftId").val(row.id);
        giftDialog.dialog('close');
    }
});