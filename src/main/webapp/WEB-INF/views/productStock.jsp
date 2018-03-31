<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="../../static/plugins/insdep/themes/insdep/easyui.css">
    <link rel="stylesheet" type="text/css" href="../../static/plugins/insdep/themes/insdep/easyui_animation.css">
    <link rel="stylesheet" type="text/css" href="../../static/plugins/insdep/themes/insdep/icon.css">
    <script type="text/javascript" src="../../static/plugins/insdep/jquery.min.js"></script>
    <script type="text/javascript" src="../../static/plugins/insdep/jquery.easyui-1.5.1.min.js"></script>
    <script type="text/javascript" src="/static/plugins/easyui/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="/static/js/productStock.js"></script>
</head>

<body>
<!--订单列表-->
<table id="productStock_datagrid" class="easyui-datagrid"
       data-options="url:'/productStock/list.do',
        toolbar: '#productStock_toolbar',
        fit: true,
        fitColumns: true,
        pagination: true,
        singleSelect: true
        ">
    <thead>
        <tr>
           <th data-options="field: 'depot', title: '仓库名称', width: 50,formatter:depotFormatter"></th>
            <th data-options="field: 'goodsMark', title: '商品编码', width: 50,formatter:mark"></th>
            <th data-options="field: 'name', title: '商品名称', width: 50,formatter:name"></th>
            <th data-options="field: 'storeNumber', title: '库存数量', width: 50"></th>
            <th data-options="field: 'auditTime', title: '入库时间', width: 50"></th>
            <th data-options="field: 'pastDueTime', title: '过期时间', width: 50,formatter:dueTime"></th>
        </tr>
    </thead>
</table>
<!--订单列表顶部按钮-->
<div id="productStock_toolbar">
    <input id="depot" class="easyui-combobox" name="depot.id"
           data-options="valueField:'id',textField:'name',url:'/depot/selectAll.do',prompt:'仓库查询',limitToList:true" />
    <input id="warnNum" type="text" class="easyui-numberbox"  name="warnNum" data-options="prompt:'阈值查询'" />
    <input id="keyword" class="easyui-textbox" data-options="prompt:'产品名称/编码查询'" name="keyword" style="width:300px">
    <a class="easyui-linkbutton button-line-green" data-options="iconCls:'icon-search',plain:true" onclick="searchs()"></a>
    <a class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-print'" onclick="window.open('/productStock/exportXls.do');">导出报表</a>
</div>


</body>
</html>
