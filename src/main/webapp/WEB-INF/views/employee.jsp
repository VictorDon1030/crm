<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="/static/common/common.jsp"%>
    <script type="text/javascript" src="/static/js/employee.js"></script>
    <title>员工管理</title>
</head>
<body>
<table id="emp_datagrid"></table>
<div id="emp_toolbar">
    <shiro:hasRole name="大队长">
    <a class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" data-btn="addData">新增</a>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" data-btn="editData">编辑</a>
    <a id="changeState" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" data-btn="changeState">设置离职</a>
    </shiro:hasRole>
    <a id="changePassword" class="easyui-linkbutton" data-options="iconCls:'icon-bqt',plain:true" data-btn="changePassword">重置密码</a>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-reload',plain:true" data-btn="reloadData">刷新</a>
    <input class="easyui-textbox" id="keyword" prompt="请输入用户名或电话"/>
    <input id="deptId" class="easyui-combobox"
           data-options="prompt:'请选择部门',valueField:'id',textField:'name',url:'/department/selectAll.do',panelHeight:'auto'"/>
    <input id="beginDate" class="easyui-datebox" prompt="开始时间"/>
    <input id="endDate" class="easyui-datebox" prompt="结束时间"/>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" data-btn="searchs"></a>
</div>

<div id="rPassword">
    <%--<table align="center">
        <tr>
            <td><font size="1">重置密码:</font>
                <input id="pwd" type="password" class="easyui-passwordbox" data-options="required:true" />
            </td>
        </tr>
        <tr>
            <td><font size="1">再输一遍:</font>
                <input id="rpwd" type="password" class="easyui-passwordbox" required="required"/>
            </td>
        </tr>
    </table>--%>
</div>

<div id="emp_dialog">
    <form id="emp_form" method="post">
        <table align="center">
            <tr>
                <td>
                    <input name="id" type="hidden"/>
                </td>
            </tr>
            <tr>
                <td>

                    <font size="1">用户名:</font>

                </td>
                <td>
                    <input name="username" class="easyui-textbox"/>
                </td>
            </tr>
            <tr>
                <td>

                    <font size="1">真实姓名:</font>

                </td>
                <td>
                    <input name="realname" class="easyui-textbox"/>
                </td>
            </tr>
            <tr id="password">
                <td>

                    <font size="1">密码:</font>

                </td>
                <td>
                    <input name="password" class="easyui-passwordbox"/>
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

                    <font size="1">邮箱:</font>

                </td>
                <td>
                    <input name="email" class="easyui-textbox"/>
                </td>
            </tr>
            <tr>
                <td>

                    <font size="1">入职时间:</font>

                </td>
                <td>
                    <input name="hireDate" class="easyui-datebox"/>
                </td>
            </tr>
            <tr>
                <td>

                    <font size="1">部门:</font>

                </td>
                <td>
                    <input name="dept.id" class="easyui-combobox"
                           data-options="valueField:'id',textField:'name',url:'/department/selectAll.do',panelHeight:'auto'"/>
                </td>
            </tr>
            <tr>
                <td>

                    <font size="1">管理员:</font>

                </td>
                <td>
                    <input name="admin" class="easyui-combobox"
                           data-options="valueField:'id',panelHeight:'auto',textField:'name',data:[
                           {id:'1',name:'是'},{id:'0',name:'否'}
                           ]"/>
                </td>
            </tr>
            <tr>
                <td>

                    <font size="1">角色:</font>

                </td>
                <td>
                    <input id="roles_combobox" class="easyui-combobox"
                           data-options="multiple:true,valueField:'id',textField:'name',url:'/role/selectAll.do',panelHeight:'auto'"/>
                </td>
            </tr>
        </table>
    </form>
</div>
<div id="emp_button">
    <a class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true" data-btn="save">保存</a>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" data-btn="cancel">取消</a>
</div>
</body>
</html>
