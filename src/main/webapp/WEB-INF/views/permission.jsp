<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="/static/common/common.jsp"%>
    <script type="text/javascript" src="/static/js/permission.js"></script>
    <title>角色管理</title>
</head>
<body>
<table id="permission_datagrid"></table>
<div id="permission_toolbar">
    <a class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" data-btn="addData">加载权限</a>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-reload',plain:true" data-btn="reloadData">刷新</a>
</div>
</body>
</html>
