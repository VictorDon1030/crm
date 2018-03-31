<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="/static/common/common.jsp"%>
    <script type="text/javascript" src="/static/js/role.js"></script>
    <title>角色管理</title>
</head>
<body>
<table id="role_datagrid"></table>
<div id="role_toolbar">
    <a class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" data-btn="addData">新增</a>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" data-btn="editData">编辑</a>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" data-btn="deleteData">删除</a>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-reload',plain:true" data-btn="reloadData">刷新</a>
    <input class="easyui-textbox" id="keyword" prompt="角色名称或编码"/>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" data-btn="searchs"></a>
</div>
<div id="role_dialog">
    <form id="role_form" method="post">
        <table align="center">
            <tr>
                <td><input name="id" type="hidden"/></td>
            </tr>
            <tr>
                <td><font size="1">角色编码:</font>
                    <input name="sn" class="easyui-textbox"/>
                </td>
                <td><font size="1">角色名称:</font>
                    <input name="name" class="easyui-textbox"/>
                </td>
            </tr>
            <tr>
                <td><table id="allPermission" ></table></td>
                <td><table id="selfPermission" > </table></td>
            </tr>
        </table>
    </form>
</div>
<div id="role_button">
    <a class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true" data-btn="save">保存</a>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" data-btn="cancel">取消</a>
</div>
</body>
</html>
