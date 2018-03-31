<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="/static/common/common.jsp"%>
    <script type="text/javascript" src="/static/js/systemLog.js"></script>
    <title>员工管理</title>
<style type="text/css">
    .bg{
    font-size: 1.4ch;
        color: #9eb9c2;
    font-family: 'Microsoft YaHei';
    }
</style>
</head>
<body>
<table id="log_datagrid"></table>
<div id="log_toolbar">
    <div align="center">
    <a class="easyui-linkbutton" data-options="iconCls:'icon-reload',plain:true" data-btn="reloadData">刷新</a>
    <input id="beginDate" class="easyui-datebox" prompt="开始时间"/>
    <input id="endDate" class="easyui-datebox" prompt="结束时间"/>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" data-btn="searchs"></a>
    </div>
    <div align="center" class="bg">
        <span>每1000次操作记录自动清除</span>
    </div>
</div>
</body>
</html>
