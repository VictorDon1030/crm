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
    <script type="text/javascript" src="../../static/plugins/insdep/datagrid-cellediting.js"></script>
    <script type="text/javascript" src="/static/plugins/easyui/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="/static/js/orderBill.js"></script>
</head>
<body>
<!--订单列表-->
<table id="orderBill_datagrid" class="easyui-datagrid"
       data-options="url: '/orderBill/list.do',
        toolbar: '#orderBill_toolbar',
        fit: true,
        fitColumns: true,
        pagination: true,
        singleSelect: true,
        checkbox: true">
    <thead>
    <tr>
        <th data-options="field: 'ck', checkbox: true"></th>
        <th data-options="field: 'sn', title: '单据编号', width: 50"></th>
        <th data-options="field: 'supplier', title: '供应商', width: 50,formatter:supplierFormatter"></th>
        <th data-options="field: 'depot', title: '仓库名称', width: 50,formatter:depotFormatter"></th>
        <th data-options="field: 'totalNumber', title: '数量', width: 50"></th>
        <th data-options="field: 'totalAmount', title: '合计', width: 50"></th>
        <th data-options="field: 'status', title: '入库状态', width: 50,formatter:statusFormatter"></th>
    </tr>
    </thead>
</table>

<%--选择商品弹出框--%>
<div id="product_dialog">
    <%--商品列表--%>
    <table id="product_datagrid">

    </table>
</div>

<%--添加采购订单弹出框--%>
<div id="orderBill_dialog" >
    <form id="orderBill_form" method="post">
        <table align="center">
            <tbody>
                <tr>
                    <td><a class="easyui-linkbutton button-line-green" data-cmd="productOpen">选择商品</a></td>
                   <td>
                       <input name="supplier.id" class="easyui-combobox"
                           data-options="prompt:'供应商选择',panelHeight:'auto',valueField:'id',textField:'realname',
                               url:'/supplier/selectAll.do'">
                    <input name="depot.id" class="easyui-combobox"
                           data-options="prompt:'仓库选择',panelHeight:'auto',valueField:'id',textField:'name',
                               url:'/depot/selectAll.do'">
                   业务时间: <input  id="dd" name="vdate" type= "text" class= "easyui-datebox" required ="required"></input>
                   </td>
                </tr>
            </tbody>
        </table>
        <%--明细列表--%>
        <input type="hidden" name="id">
        <table id="orderBillItem_datagrid" >
            <thead>
            <tr>
                <th width="100">商品</th>
                <th width="100">编码</th>
                <th width="100">售价</th>
                <th width="100">数量</th>
                <th width="100">小计</th>
                <th width="100">备注</th>
                <th width="50"></th>
            </tr>
            </thead>
            <tbody id="edit_tbody">
                <tr id="itemTr">
                    <input  tag="pid" type="hidden">
                    <th >
                        <span  tag="name" width="150"></span>
                    </th>
                    <th >
                        <span  tag="sn" width="150"></span>
                    </th>
                    <th ><input tag="costPrice"  type="number" style="width: 60px"></th>
                    <th><input tag="number"   type="number" style="width: 60px"></th>
                    <th ><span tag="amount"width="150"></span></th>
                    <th ><input tag="remark" style="width: 60px"></th>
                    <th>
                        <a  class="easyui-linkbutton removeItem" data-options="title:'删除',plain:true,iconCls:'icon-remove'"></a>
                    </th>
                </tr>
            </tbody>
        </table>
    </form>
</div>

<!--订单列表顶部按钮-->
<div id="orderBill_toolbar">
    <a class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'" id="add_btn" data-cmd="add">新增进货</a>
    <a class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-print'" onclick="window.open('/orderBill/exportXls.do');">导出报表</a>
    <a class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-lock'" id="changeAudit_btn" data-cmd="changeAudit">入库</a>
    <a class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-redo'" id="edit_btn" data-cmd="edit">编辑</a>
    <a class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-remove'" id="deleted_btn" data-cmd="deleted">删除</a>
    <input id="status" name="status" class="easyui-switchbutton"
           data-options="onText:'已入库',offText:'未入库',width:100,">
    进货时间段:<input id="beginDate" class="easyui-datetimebox" name="beginDate"
           data-options="showSeconds:false,prompt:'begin'"  style="width:150px">~
    <input id="endDate" class="easyui-datetimebox" name="endDate"
           data-options="showSeconds:false,prompt:'end'"  style="width:150px">
    <a class="easyui-linkbutton button-line-green" data-options="iconCls:'icon-search',plain:true" onclick="searchs()"></a>
</div>
<%--采购弹框底部按钮--%>
<div id="orderBillItem_button">
    <a class="easyui-linkbutton button-line-green" data-cmd="save">保存</a>
    <a class="easyui-linkbutton button-line-grayish" data-cmd="cancel">取消</a>
</div>


<%--选择商品弹框底部按钮--%>
<div id="product_button">
    <a class="easyui-linkbutton button-line-green" data-cmd="inputValue">确定</a>
    <a class="easyui-linkbutton button-line-grayish" data-cmd="cancelProduct">取消</a>
</div>
</body>
</html>
