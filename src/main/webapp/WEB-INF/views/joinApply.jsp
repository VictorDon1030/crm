<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="/static/common/common.jsp"%>
    <script type="text/javascript" src="/static/js/joinApply.js"></script>
    <title>支付申请</title>
</head>
<body>
<table id="app_datagrid"></table>
<div id="app_toolbar">
    <a class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" data-btn="editData">编辑</a>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" data-btn="deleteData">删除</a>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-reload',plain:true" data-btn="reloadData">刷新</a>
    <input class="easyui-textbox" id="keyword" prompt="请输入用户名或电话"/>
    <input id="appType" class="easyui-combobox"
           data-options="prompt:'请选择类型',valueField:'id',textField:'name',
           data: [{
			id: '1',
			name: '个体'
		},{
			id: '0',
			name: '企业'
		}],panelHeight:'auto'"/>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" data-btn="searchs"></a>
</div>
<div id="app_dialog">
    <form id="app_form" method="post">
        <table align="center">
            <tr>
                <td>
                    <input name="id" type="hidden"/>
                </td>
            </tr>
            <tr>
                <td>
                    <font size="1">店铺名称:</font>
                </td>
                <td>
                    <input name="shopName" class="easyui-textbox"/>
                </td>
            </tr>
            <tr>
                <td>
                    <font size="1">联系电话:</font>
                </td>
                <td>
                    <input name="tel" class="easyui-textbox"/>
                </td>
            </tr>
            <tr>
                <td>

                    <font size="1">邮箱:</font>

                </td>
                <td>
                    <input name="email" class="easyui-textbox"/>
                </td>
            </tr>
            <tr>
                <td>

                    <font size="1">开户银行:</font>

                </td>
                <td>
                    <input name="bankName" class="easyui-textbox"/>
                </td>
            </tr>
            <tr>
                <td>

                    <font size="1">开户名称:</font>

                </td>
                <td>
                    <input name="accountName" class="easyui-textbox"/>
                </td>
            </tr>

        </table>
    </form>
</div>
<div id="app_button">
    <a class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true" data-btn="save">保存</a>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" data-btn="cancel">取消</a>
</div>
</body>
</html>
