<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="/static/common/common.jsp"%>
    <script type="text/javascript" src="/static/js/member.js"></script>
    <title>会员管理</title>
</head>
<body>
<table id="member_datagrid"></table>
<div id="member_toolbar">
    <shiro:hasRole name="大队长"> </shiro:hasRole>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" data-btn="addData">新增</a>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" data-btn="editData">编辑</a>
    <a id="changeState" class="easyui-linkbutton"
       data-options="iconCls:'icon-remove',plain:true" data-btn="changeState">挂失</a>

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

<div id="member_dialog">
    <form id="member_form" method="post">
        <table align="center">
            <tr>
                <td>
                    <input name="id" type="hidden"/>
                </td>
            </tr>
            <tr>
                <td>

                    <font size="3">会员卡号:</font>

                </td>
                <td>
                    <input name="memberNum" class="easyui-textbox"/>
                </td>
                <td>

                    <font size="3">会员姓名:</font>

                </td>
                <td>
                    <input name="name" class="easyui-textbox"/>
                </td>
            </tr>
            <%--<tr>--%>
                <%--<td>--%>

                    <%--<font size="1">会员姓名:</font>--%>

                <%--</td>--%>
                <%--<td>--%>
                    <%--<input name="name" class="easyui-textbox"/>--%>
                <%--</td>--%>
            <%--</tr>--%>
            <tr id="password">
                <td>

                    <font size="3">密码:</font>

                </td>
                <td>
                    <input name="password" class="easyui-passwordbox"/>
                </td>
            </tr>
            <tr>
                <td>

                    <font size="3">手机号码:</font>

                </td>
                <td>
                    <input name="phone" class="easyui-textbox"/>
                </td>
            </tr>
            <tr>
                <td>

                    <font size="3">积分:</font>

                </td>
                <td>
                    <input name="points" class="easyui-textbox"/>
                </td>
            </tr>
            <tr>
                <td>

                    <font size="3">余额:</font>

                </td>
                <td>
                    <input name="balance" class="easyui-textbox"/>
                </td>
            </tr>
            <tr>
                <td>

                    <font size="3">有效期:</font>

                </td>
                <td>
                    <input name="usefullife" class="easyui-datebox"/>
                </td>
            </tr>
            <tr>
                <td>

                    <font size="3">会员生日:</font>

                </td>
                <td>
                    <input name="birthday" class="easyui-datebox"/>
                </td>
            </tr>
            <tr>
                <td>

                    <font size="3">会员等级:</font>

                </td>
                <td>
                    <input name="grade.id" class="easyui-combobox"
                           data-options="valueField:'id',panelHeight:'auto',textField:'name',url:'/dictionaryItem/selectItemByDictionarySn.do?dictionarySn=grade'"/>
                </td>
            </tr>
            <tr>
                <td>

                    <font size="3">支付方式:</font>

                </td>
                <td>
                    <input name="payment.id"  class="easyui-combobox"
                               data-options="valueField:'id',textField:'name',url:'/dictionaryItem/selectItemByDictionarySn.do?dictionarySn=payment',panelHeight:'auto'"/>
                </td>
            </tr>
        </table>
    </form>
</div>
<div id="member_button">
    <a class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true" data-btn="save">保存</a>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" data-btn="cancel">取消</a>
</div>
</body>
</html>
