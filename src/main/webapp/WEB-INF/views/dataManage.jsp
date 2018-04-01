<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="/static/common/common.jsp"%>
    <script type="text/javascript" src="/static/js/dataManage.js"></script>
    <title>数据管理</title>
    <style type="text/css">
        .bg{
            font-weight: bolder;
            font-family: "STSong";
        }
    </style>
    <script type="text/javascript">
        $(function () {
            $("#btn_dbBackup").click(function () {
                $.get('/DbOperate/dbBackup.do',function (data) {
                    if (data.success){
                        $.messager.alert('温馨提示','备份成功');
                    }else{
                        $.messager.alert('温馨提示',data.msg);
                    }
                })
            })
            $("#btn_dbRecover").click(function () {
                $.get('/DbOperate/dbRecover.do',function (data) {
                    if (data.success){
                        $.messager.alert('温馨提示','还原成功');
                    }else{
                        $.messager.alert('温馨提示',data.msg);
                    }
                })
            })

        })
    </script>
</head>
<body>
    <div class="easyui-layout" data-options="fit:true">
        <div data-options="region:'north',width:'auto',height:160,border:false">
            <p style="margin-top: 10px;margin-left: 20%"><span><font size="4">数据初始化</font></span></p>
            <div style="background-color: lightpink;width: 60%;margin-left: 25%">
                <p style="margin-top: 10px">
                    <span class="bg"><font size="4">
                        *注意！数据是您最宝贵的财富。您不要随便初始化数据。初始化的店铺数据将永远无法恢复。</br>
                        误删,错删,请联系110或120</br>
                        坦白从宽,牢底坐穿
                        </font>
                    </span></p>
            </div>
        </div>
        <div data-options="region:'center',border:false">
            <p style="margin-top: 10px;margin-left: 20%"><span><font size="4">选择初始化内容</font></span></p>
            <div style="background-color: whitesmoke;width: 60%;margin-left: 25%">
                <p style="margin-top: 10px">
                <input id="1" type="checkbox" data-id="1"/>
                    清除员工信息 | 删除全部员工信息、员工储值、积分、优惠券等所有记录
                </p>
                <p style="margin-top: 10px">
                <input id="2" type="checkbox" data-id="2"/>
                    清除商品信息 | 删除全部商品信息（保留商品分类）、信息
                </p>
                <p style="margin-top: 10px">
                <input id="3" type="checkbox" data-id="3"/>
                    清除商品出入库信息 删除所有商品出入库信息
                </p>
            </div>

            <div style="margin-top: 40px;margin-left: 20%">
                <button id="btn-clean" style="background-color: limegreen;width: 120px;height: 50px"><font size="3">初始化数据</font></button>
                <button id="btn_dbBackup" style="background-color: limegreen;width: 120px;height: 50px" ><font size="3">备份数据</font></button>
                <button id="btn_dbRecover"  style="background-color: darkred;width: 120px;height: 50px" ><font size="3">还原数据</font></button>
            </div>
        </div>
    </div>
</body>
</html>
