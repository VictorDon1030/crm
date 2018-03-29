<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
    <link href="/static/plugins/easyuiplus/reset.min.css" rel="stylesheet" type="text/css">
    <link href="/static/plugins/easyuiplus/easyui_full.css" rel="stylesheet" type="text/css">

    <script type="text/javascript" src="/static/plugins/easyuiplus/jquery-1.11.3.min.js"></script>
    <script type="text/javascript" src="/static/plugins/easyuiplus/jquery.easyui-1.5.2.min.js"></script>
    <script type="text/javascript" src="/static/plugins/easyuiplus/insdep-extend.min.js"></script>
    <script type="text/javascript" src="/static/js/checkout.js"></script>
</head>
<body>
<div id="cc" class="easyui-layout" style="width:600px;" fit="true">
    <div data-options="region:'west',title:'收银订单明细',split:true">
        <form method="post" id="checkoutItem">
            <div style="margin-left: 0px;margin-top: 10px">
                销售时间:<input type="text" class="easyui-textbox" name="checkoutTime">
            </div>
            <div style="margin-left: 0px;margin-top: 10px">
                销售单号:<input type="text" class="easyui-textbox" name="checkoutOdd">
            </div>
            <div style="height: 350px" class="easyui-layout">
            <table id="ashier_table" align="center" style="margin-top: 20px">
                <thead>
                <tr align="center">
                    <td>商品编码</td>
                    <td>商品名</td>
                    <td>价格</td>
                    <td>数量</td>
                    <td>小计</td>
                </tr>
                </thead>
                    <tbody id="cashier_item">
                 <tr align="center">
                    <td>
                        <input id="productSn" name="product.goodsMark" readonly="readonly" type="text" style="width:75px"
                               tag="goodsMark"/>
                        <input id="productId" name="product.id" type="hidden" style="width:75px" tag="id"/>
                        <%--<input id="depot" name="depot.id" type="hidden" style="width:75px"/>--%>
                    </td>
                    <td>

                        <input id="productName" name="product.name" readonly="readonly" type="text" style="width:75px"
                               tag="name">
                    </td>
                    <td>
                        <input id="salePrice" name="product.unitpPrice" readonly="readonly" type="text" style="width:75px"
                               tag="unitpPrice">
                    </td>
                    <td>
                        <input id="number" name="number" type="text" style="width:75px" tag="number">
                    </td>
                    <td>
                        <input id="amount" name="amount" type="text" readonly="readonly" style="width:75px" tag="amount"
                              class="input" oninput="count();">
                    </td>
                    <td>
                        <a id="remove_item" class="btn_deleteItem">删除</a>
                    </td>
                </tr>
                </tbody>
            </table>
            </div>
            <div class="easyui-layout" style="height: 150px;width: 600px">
            <div id="p" class="p"style="width:100px;height:150px;" align="canter"
                 data-options="region:'west'">
                <p><span class="hfont"><font size="4">应付款: ¥</font></span></p>
                <p><span id="totalNum" class="hfont"></span></p>
                <p><span class="hfont"></span></p>
            </div>
                <div id="pd" class="pd" style="width:250px;height:150px;">
                    <p>会员编号:<input id="clientNum" class="easyui-textbox" data-options="" style="width:150px"></p>
                    <p>会员姓名:<input id="clientName" class="easyui-textbox" data-options="" style="width:150px"></p>
                    <p>卡内余额:<input id="balance" class="easyui-textbox" data-options="" style="width:150px"></p>
                </div>
            </div>
            <a class="easyui-linkbutton" data-options="iconCls:'icon-guadan',width:140" data-cmd="entryOrders">挂单</a>
            <a class="easyui-linkbutton" data-options="iconCls:'icon-qudan',width:140" data-cmd="selectOrders">取单</a>
            <a class="easyui-linkbutton" data-options="iconCls:'icon-fukuan',width:140" data-cmd="payment">付款</a>
            </form>
    </div>
    <div data-options="region:'center',title:'字典详细信息',split:true" style="width:600px;">
        <table id="product" class="easyui-datagrid" data-options="fit:true,singleSelect:true,fitColumns:true,
            url:'/product/list.do',pagination:true,remoteSort:false,sortOrder:'desc'">
            <thead>
            <tr>
                <th data-options="field:'id',width:'50',sortable:true">id</th>
                <th data-options="field:'name',width:'50',sortable:true">商品名称</th>
                <th data-options="field:'goodsMark',width:'50',sortable:true">商品编号</th>
                <th data-options="field:'unitpPrice',width:'50',sortable:true">商品售价</th>
                <th data-options="field:'brand',width:'50',sortable:true">商品品牌</th>
                <th data-options="field:'minDiscount',width:'50',sortable:true" style="color: red">会员折扣</th>
            </tr>
            </thead>
        </table>
    </div>
</div>
</body>
</html>

