<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="/static/common/common.jsp"%>
    <script type="text/javascript" src="/static/js/manager.js"></script>
    <title>管理人员</title>
</head>
<body>
<table id="manager_datagrid"></table>
<div id="manager_toolbar">
    <a class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" data-btn="addData">新增</a>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" data-btn="editData">编辑</a>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" data-btn="deleteData">删除</a>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-reload',plain:true" data-btn="reloadData">刷新</a>
</div>
<div id="manager_dialog">
    <form id="manager_form" method="post">
        <table align="center" style="margin-top: 20px">
            <tr>
                <td><input name="id" type="hidden"/></td>
            </tr>
            <tr>
                <td><font size="1">员工姓名:</font>
                    <input name="name" class="easyui-combobox"
                           data-options="valueField:'id',textField:'username',url:'/employee/selectAll.do'"
                    />
                </td>
            </tr>
            <tr>
                <td><font size="1">登录密码:</font>
                    <input name="password" class="easyui-textbox" prompt="请输入当前登录密码"/>
                </td>
            </tr>
        </table>
    </form>
</div>
<div id="manager_button">
    <a class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true" data-btn="save">保存</a>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" data-btn="cancel">取消</a>
</div>
</body>
</html>
