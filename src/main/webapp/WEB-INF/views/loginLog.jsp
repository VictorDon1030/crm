<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="/static/common/common.jsp"%>
    <script type="text/javascript" src="/static/js/loginLog.js"></script>
    <title>员工管理</title>
</head>
<body>
<table id="login_datagrid"></table>
<div id="login_toolbar">
    <a class="easyui-linkbutton" data-options="iconCls:'icon-reload',plain:true" data-btn="reloadData">刷新</a>
    <input class="easyui-textbox" id="keyword" prompt="用户名"/>
    <input id="beginDate" class="easyui-datebox" prompt="开始时间"/>
    <input id="endDate" class="easyui-datebox" prompt="结束时间"/>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" data-btn="searchs"></a>
</div>
</body>
</html>
