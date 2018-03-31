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
    <script type="text/javascript" src="/static/js/inventory.js"></script>
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
            <th data-options="field: 'specification', title: '规格', width: 50,formatter:specification"></th>
            <th data-options="field: 'storeNumber', title: '库存数量', width: 50"></th>
            <th data-options="field: 'auditTime', title: '入库时间', width: 50"></th>
            <th data-options="field: 'inventoryTime', title: '上次盘点时间', width: 50"></th>
            <th data-options="field: 'xx', title: '操作', width: 100,formatter:handle"></th>
        </tr>
    </thead>
</table>
<!--订单列表顶部按钮-->
<div id="productStock_toolbar">
    <input id="depot" class="easyui-combobox" name="depot.id"
           data-options="valueField:'id',textField:'name',url:'/depot/selectAll.do',prompt:'仓库查询',limitToList:true" />
    <input id="warnNum" type="text" class="easyui-numberbox"  name="warnNum" data-options="prompt:'阈值查询'" />
    <input id="keyword" class="easyui-textbox" data-options="prompt:'产品名称/编码查询'" name="keyword" style="width:300px">
    盘点时间段:<input id="beginDate" class="easyui-datetimebox" name="beginDate"
                 data-options="showSeconds:false,prompt:'begin'"  style="width:150px">~
    <input id="endDate" class="easyui-datetimebox" name="endDate"
           data-options="showSeconds:false,prompt:'end'"  style="width:150px">
    <a class="easyui-linkbutton button-line-green" data-options="iconCls:'icon-search',plain:true" onclick="searchs()"></a>
    <a class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-print'" onclick="window.open('/productStock/exportXls.do');">导出报表</a>
</div>


<div id="productStock_dialog">
    <form id="productStock_form" method="post">
        <table style="margin-top: 20px;margin-left: 60px" >
            <input type="hidden" name="id">
            <tr>
                <td>商品名称:</td>
                <td><input name="name" class="easyui-textbox" data-options="prompt:'输入仓库名称'"></td>
            </tr>
            <tr>
                <td>原有库存:</td>
                <td><input  name="sn" class="easyui-textbox" data-options="prompt:'输入仓库编码'"></td>
            </tr>
            <tr>
                <td>修改数量:</td>
                <td><input  name="employee.tel" class="easyui-textbox" readonly></td>
            </tr>
            <tr>
                <td>操作人员:</td>
                <td><input name="employee.id" class="easyui-combobox"
                           data-options="prompt:'操作员',panelHeight:'auto',valueField:'id',textField:'realname',
                               url:'/depot/selectAllEmployee.do'"></td>
            </tr>
            <tr>
                <td>备注信息:</td>
                <td>
                    <%--<input class="easyui-switchbutton" checked >--%>
                    <input id="edit_state" name="state" class="easyui-switchbutton"
                           data-options="onText:'正常',offText:'停用',width:130">
                </td>
            </tr>
            <tr>
                <td>仓库地址:</td>
                <td><input  name="address" class="easyui-textbox" data-options="prompt:'输入仓库地址'"></td>
            </tr>
            <tr>
                <td>备注信息:</td>
                <td><input  name="info" class="easyui-textbox" data-options="prompt:'输入备注信息'"></td>
            </tr>
        </table>
    </form>
</div>

</body>
</html>
