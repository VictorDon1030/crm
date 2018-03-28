$(function () {
    var memberSimpleInfo = $("#memberSimpleInfo");
    var memberInfo = $("#memberInfo");
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
            $("#memberName").html(row.name);
            $("#memberNum").html(row.memberNum);
            $("#grade").html(row.grade.name);
            $("#points").html(row.points);
            $("#balance").html(row.balance);
            $("#birthday").html(row.birthday);
            $("#hiddenMemberId").val(row.id);
            var sumPoints = 0;
            var comsumPoints = 0;
            $.each(row.bonusPointRecord, function (index, item) {
                if (item.amount >= 0) {
                    sumPoints = sumPoints + item.amount;
                } else {
                    comsumPoints = comsumPoints + (item.amount - item.amount * 2)
                }

            });
            $("#sumPoints").html(sumPoints);
            $("#comsumPoints").html(comsumPoints);




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
                        return optUser ? optUser.realname : optUser;
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
        }
    }
    $("a[data-cmd]").click(function () {
        var methodName = $(this).data("cmd");
        methodObj[methodName]();
    });

});