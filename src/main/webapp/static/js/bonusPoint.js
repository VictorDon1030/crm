$(function () {
    var memberSimpleInfo = $("#memberSimpleInfo,#memberSimpleInfo_gift");
    var memberInfo = $("#memberInfo,#memberInfo_gift");
    var giftList = $("#giftList");
    var giftEdit = $("#giftEdit");
    var giftDialog = $("#gift_dialog");
    var giftEditForm = $("#gift_edit_form");
    var exchangeRecord = $("#exchange_record");
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
            $("#memberName,#memberName_gift").html(row.name);
            $("#memberNum,#memberNum_gift").html(row.memberNum);
            $("#grade,#grade_gift").html(row.grade.name);
            $("#points,#points_gift").html(row.points);
            $("#balance,#balance_gift").html(row.balance);
            $("#birthday,#birthday_gift").html(row.birthday);
            $("#hiddenMemberId,#hiddenMemberId_gift").val(row.id);
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
            $("#sumPoints,#sumPoints_gift").html(sumPoints);
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
            exchangeRecord.datagrid("load", {keyword: exchangekeyword,beginDate:beginDate,endDate:endDate});
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
            var rows = $("#giftList4choose").datagrid("getSelections");
            $.each(rows, function (index, item) {
                console.log(item);
            });

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

});