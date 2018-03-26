<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="/static/common/common.jsp"%>
    <script type="text/javascript" src="/static/js/menu.js"></script>
    <title>员工管理</title>
</head>
<body>
<table id="menu_datagrid"></table>
<div id="menu_toolbar">
    <a class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" data-btn="addData">新增</a>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" data-btn="editData">编辑</a>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" data-btn="deleteData">删除</a>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-reload',plain:true" data-btn="reloadData">刷新</a>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-ts',plain:true" data-btn="loadChildMenu">子菜单</a>

    <a class="easyui-linkbutton" data-options="iconCls:'icon-ts',plain:true" data-btn="loadParentMenu">父菜单</a>
</div>
<div id="menu_dialog">
    <form id="menu_form" method="post">
        <table align="center">
            <tr>
                <td>
                    <input name="id" type="hidden"/>
                </td>
            </tr>
            <tr>
                <td>

                    <font size="1">菜单名称:</font>

                </td>
                <td>
                    <input name="text" class="easyui-textbox"/>
                </td>
            </tr>
            <tr>
                <td>

                    <font size="1">URL:</font>

                </td>
                <td>
                    <input name="url" class="easyui-textbox"/>
                </td>
            </tr>
            <tr>
                <td>

                    <font size="1">图像:</font>

                </td>
                <td>
                    <input name="iconCls" class="easyui-textbox"/>
                </td>
            </tr>
            <tr>
                <td>

                    <font size="1">父菜单:</font>

                </td>
                <td>
                    <input id="rootMenu" readonly name="parent.id" class="easyui-textbox"/>
                </td>
            </tr>

        </table>
    </form>
</div>
<div id="menu_button">
    <a class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true" data-btn="save">保存</a>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" data-btn="cancel">取消</a>
</div>
</body>
</html>
