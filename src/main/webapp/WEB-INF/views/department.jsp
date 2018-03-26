<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="/static/common/common.jsp"%>
    <script type="text/javascript" src="/static/js/department.js"></script>
    <title>员工管理</title>
</head>
<body>
<table id="dept_datagrid"></table>
<div id="dept_toolbar">
    <a class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" data-btn="addData">新增</a>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" data-btn="editData">编辑</a>
    <a id="changeState" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" data-btn="changeState">设置停用</a>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-reload',plain:true" data-btn="reloadData">刷新</a>
</div>
<div id="dept_dialog">
    <form id="dept_form" method="post">
        <table align="center">
            <tr>
                <td>
                    <input name="id" type="hidden"/>
                </td>
            </tr>
            <tr>
                <td>

                    <font size="1">部门编码:</font>

                </td>
                <td>
                    <input name="sn" class="easyui-textbox"/>
                </td>
            </tr>
            <tr>
                <td>

                    <font size="1">部门名称:</font>

                </td>
                <td>
                    <input name="name" class="easyui-textbox"/>
                </td>
            </tr>

        </table>
    </form>
</div>
<div id="dept_button">
    <a class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true" data-btn="save">保存</a>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" data-btn="cancel">取消</a>
</div>
</body>
</html>
