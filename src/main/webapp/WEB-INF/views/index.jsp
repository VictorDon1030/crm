<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>首页</title>
    <%@ include file="/static/common/common.jsp"%>
    <script type="text/javascript" src="/static/js/index.js"></script>
    <link rel="stylesheet" type="text/css" href="/static/css/public.css">
    <link rel="stylesheet" type="text/css" href="/static/css/reset.css">
    <link rel="stylesheet" type="text/css" href="/static/css/style.css">
</head>
<body>
<div class="easyui-layout" data-options="fit:true">
    <div data-options="region:'north',height:75,split:true" >
        <div class="public-header-warrp">
            <div class="public-header">
                <div class="content">
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img  src="http://www.wolfcode.cn/img/wolfcode/logo.png"/>
                    <div class="public-header-admin fr">
                        <p class="admin-name"><font  color ="green">您好！<shiro:principal property="username"/> </font> </p>
                        <div class="public-header-fun fr">
                            <a href="/logout.do" class="public-header-loginout">安全退出</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div data-options="region:'south',height:20" >
        <center><span >©版权所有</span></center>
    </div>
    <div data-options="region:'west',title:'系统菜单',width:160">
        <ul id="menu"></ul>
    </div>
    <div data-options="region:'center'">
        <div id="tabs" class="easyui-tabs" data-options="fit:true,border:false">
            <div data-options="title:'首页'">
                欢迎光临
            </div>
        </div>
    </div>
</div>
</body>
</html>
