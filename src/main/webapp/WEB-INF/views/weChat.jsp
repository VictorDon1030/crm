<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="/static/common/common.jsp"%>
    <script type="text/javascript" src="/static/js/weChat.js"></script>
    <title>支付申请</title>
</head>
<body>
<table id="chat_datagrid"></table>
<div id="chat_toolbar">
    <a class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" data-btn="editData">编辑</a>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" data-btn="deleteData">删除</a>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-reload',plain:true" data-btn="reloadData">刷新</a>
    <input class="easyui-textbox" id="keyword" data-options="width:200" prompt="请输入用户名或威信账号"/>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" data-btn="searchs"></a>
</div>
<div id="chat_dialog">
    <form id="chat_form" method="post">
        <table align="center">
            <tr>
                <td>
                    <input name="id" type="hidden"/>
                </td>
            </tr>
            <tr>
                <td>
                    <font size="1">威信账号:</font>
                </td>
                <td>
                    <input name="accountNumber" class="easyui-textbox"/>
                </td>
            </tr>
            <tr>
                <td>
                    <font size="1">应用秘钥:</font>
                </td>
                <td>
                    <input name="secretkey" class="easyui-passwordbox"/>
                </td>
            </tr>
            <tr>
                <td>

                    <font size="1">用户秘钥:</font>

                </td>
                <td>
                    <input name="applyKey" class="easyui-passwordbox"/>
                </td>
            </tr>
            <tr>
                <td>

                    <font size="1">用户名称:</font>

                </td>
                <td>
                    <input name="applyer.id" class="easyui-textbox"/>
                </td>
            </tr>
        </table>
    </form>
</div>
<div id="chat_button">
    <a class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true" data-btn="save">保存</a>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" data-btn="cancel">取消</a>
</div>
</body>
</html>
