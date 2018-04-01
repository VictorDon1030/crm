
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>我的店铺</title>
    <%@ include file="/static/common/common.jsp"%>
    <script type="text/javascript" src="/static/js/skip.js"></script>

    <style type="text/css">
        .changColor:hover{
            height: 50px;
            background-color: #9eb9c2;
        }
    </style>
</head>
<body>
<div class="easyui-layout" data-options="fit:true">
    <div data-options="region:'west',width:160" align="center" style="background-color: #2f4f8e">
        <div style="height: 50px;"></div>
        <div><font size="2" color="white">德客便利店</font></div>
        <div>
            <img src="/static/img/king.png" alt="截得一手好图"/>
        </div>
        <div style="margin-top: 5px;margin-bottom: 10px">
            <span><font size="1" color="white">店铺ID:10086</font></span>
        </div>
        <p class="changColor" style="height: 25px"><a href="javascript:myshop();" style='text-decoration:none;'><font size="3" color="white">本店信息</font></a></p>
        <p class="changColor" style="height: 25px"><a href="javascript:subbranch();" style='text-decoration:none;'><font size="3" color="white">分店管理</font></a></p>
        <p class="changColor" style="height: 25px"><a href="javascript:manage();" style='text-decoration:none;'><font size="3" color="white">员工管理</font></a></p>
        <p class="changColor" style="height: 25px"><a href="javascript:dataManage();" style='text-decoration:none;'><font size="3" color="white">数据管理</font></a></p>
        <p class="changColor" style="height: 25px"><a href="javascript:payment();" style='text-decoration:none;'><font size="3" color="white">支付设置</font></a></p>
        <p class="changColor" style="height: 25px"><a href="javascript:systemLog();" style='text-decoration:none;'><font size="3" color="white">操作日志</font></a></p>
        <p class="changColor" style="height: 25px"><a href="javascript:loginLog();" style='text-decoration:none;'><font size="3" color="white">登录日志</font></a></p>
    </div>
    <div data-options="region:'center'">
        <iframe id="import" src="/myshop.do" width="100%" height="100%" frameborder="0"></iframe>
    </div>
</div>
</body>
</html>
