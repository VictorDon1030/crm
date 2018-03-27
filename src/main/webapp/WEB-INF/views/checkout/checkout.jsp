<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="/js/easyuiplus/reset.min.css" rel="stylesheet" type="text/css">
    <link href="/js/easyuiplus/easyui_full.css" rel="stylesheet" type="text/css">

    <script type="text/javascript" src="/static/plugins/easyuiplus/jquery-1.11.3.min.js"></script>
    <script type="text/javascript" src="/static/plugins/easyuiplus/jquery.easyui-1.5.2.min.js"></script>
    <script type="text/javascript" src="/static/plugins/easyuiplus/insdep-extend.min.js"></script>
    <script type="text/javascript" src="/static/js/checkout.js"></script>
</head>
<body>
<div id="cc" class="easyui-layout" style="width:400px;height:300px;" fit="true">
    <div data-options="region:'west',title:'收银订单明细',split:true">
        <form method="post" id="checkoutItem">
            <div style="margin-left: 0px;margin-top: 10px">
                销售时间:<input type="text" class="easyui-textbox" name="checkoutTime">
            </div>
            <div style="margin-left: 0px;margin-top: 10px">
                销售单号:<input type="text" class="easyui-textbox" name="checkoutOdd">
            </div>
            <div >
                <><div style="margin-left: 50px;margin-top: 10px">
                商品名称:<input type="text" class="easyui-textbox" name="#">
            </div>
                <div style="margin-left: 50px;margin-top: 10px">
                    商品编号:<input type="text" class="easyui-textbox" name="#">
                </div>
                <div style="margin-left: 50px;margin-top: 10px">
                    单价:<input type="text" class="easyui-textbox" style="width: 50px" name="#">
                    商品单价:<input type="text" class="easyui-textbox" style="width: 50px" name="#">
                </div>
                <div style="margin-left: 50px;margin-top: 5px">
                    数量:<input type="text" class="easyui-textbox" style="width: 50px" name="#">
                    商品小计:<input type="text" class="easyui-textbox" style="width: 50px" name="#">
                </div>
            </div>
        </form>
    </div>
    <div data-options="region:'center',title:'字典详细信息',split:true" style="width:600px;">
        <table id="dictionaryItem" class="easyui-datagrid" data-options="fit:true,singleSelect:true,fitColumns:true,
            toolbar:'#d1',url:'/product/list.do',pagination:true,remoteSort:false,sortOrder:'desc'">
            <thead>
            <tr>
                <th data-options="field:'name',width:'80',sortable:true">商品名称</th>
                <th data-options="field:'sn',width:'80',sortable:true">商品编号</th>
                <th data-options="field:'salePrice',width:'80',sortable:true">商品售价</th>
                <th data-options="field:'brandName',width:'80',sortable:true">商品品牌</th>
                <th data-options="field:'imagePath',width:'80',sortable:true">商品品牌</th>
            </tr>
            </thead>
        </table>
    </div>
</div>
</body>
</html>

