<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="/static/common/common.jsp"%>
    <script type="text/javascript" src="/static/js/subBranch.js"></script>
    <title>分店</title>
</head>
<body>

<table id="sub_datagrid"></table>
<div id="sub_toolbar">
    <a class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" data-btn="addData">新增</a>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" data-btn="editData">编辑</a>
</div>
<div id="sub_dialog">
    <form id="sub_form" method="post">
        <table align="center">
            <tr>
                <td>
                    <input name="id" type="hidden"/>
                </td>
            </tr>
            <tr>
                <td>
                    <font size="1">分店名称:</font>
                </td>
                <td>
                    <input name="shopName" class="easyui-textbox"/>
                </td>
            </tr>
            <tr>
                <td>
                    <font size="1">分店简介:</font>
                </td>
                <td>
                    <input name="intro" class="easyui-textbox"/>
                </td>
            </tr>
            <tr>
                <td>
                    <font size="1">联系人:</font>
                </td>
                <td>
                    <input name="linkman" class="easyui-textbox"/>
                </td>
            </tr>
            <tr>
                <td>
                    <font size="1">电话:</font>
                </td>
                <td>
                    <input name="tel" class="easyui-textbox"/>
                </td>
            </tr>
            <tr>
                <td>
                    <font size="1">地址:</font>
                </td>
                <td>
                    <input name="addr" class="easyui-textbox"/>
                </td>
            </tr>
            <tr>
                <td>
                    <font size="1">注册时间:</font>
                </td>
                <td>
                    <input name="joinDate" class="easyui-datebox"/>
                </td>
            </tr>
        </table>
    </form>
</div>

<div id="sub_button">
    <a class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true" data-btn="save">保存</a>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" data-btn="cancel">取消</a>
</div>
</body>
</html>
