
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="../../static/plugins/insdep/themes/insdep/easyui.css">
    <link rel="stylesheet" type="text/css" href="../../static/plugins/insdep/themes/insdep/easyui_animation.css">
    <link rel="stylesheet" type="text/css" href="../../static/plugins/insdep/themes/insdep/icon.css">
    <script type="text/javascript" src="../../static/plugins/insdep/jquery.min.js"></script>
    <script type="text/javascript" src="../../static/plugins/insdep/jquery.easyui-1.5.1.min.js"></script>
    <script type="text/javascript" src="/static/plugins/easyui/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="/static/js/depot.js"></script>
</head>
<body>
    <!--列表-->
    <table id="depot_datagrid">
    </table>
    <!--列表顶部按钮-->
    <div id="depot_toolbar">
        <a class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'" onclick="add();">新增仓库</a>
        <a class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'" onclick="edit();">编辑</a>
        <a id="changeState" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'" onclick="changeStatus();">关闭仓库</a>
        <input id="state" name="state" class="easyui-switchbutton"
               data-options="onText:'开启仓库',offText:'关闭仓库',width:100,">

    </div>
    <div id="depot_button">
        <a class="easyui-linkbutton button-line-green"  onclick="save()">保存</a>
        <a class="easyui-linkbutton button-line-grayish" onclick="cancel()">取消</a>
    </div>
    <div id="depot_dialog">
        <form id="depot_form" method="post">
            <table style="margin-top: 20px;margin-left: 60px" >
            <input type="hidden" name="id">
                <tr>
                    <td>仓库名称:</td>
                    <td><input name="name" class="easyui-textbox" data-options="prompt:'输入仓库名称'"></td>
                </tr>
                <tr>
                    <td>仓库编码:</td>
                    <td><input  name="sn" class="easyui-textbox" data-options="prompt:'输入仓库编码'"></td>
                </tr>
                <tr>
                    <td>联系电话:</td>
                    <td><input  name="employee.tel" class="easyui-textbox" readonly></td>
                </tr>
                <tr>
                    <td>管理人员:</td>
                    <td><input name="employee.id" class="easyui-combobox"
                               data-options="prompt:'仓库管理员',panelHeight:'auto',valueField:'id',textField:'realname',
                               url:'/depot/selectAllEmployee.do'"></td>
                </tr>
                <tr>
                    <td>仓库状态:</td>
                    <td>
                        <%--<input class="easyui-switchbutton" checked >--%>
                        <input id="edit_state" name="state" class="easyui-switchbutton"
                               data-options="onText:'正常',offText:'停用',width:130">
                    </td>
                </tr>
                <tr>
                    <td>仓库地址:</td>
                    <td><input  name="address" class="easyui-textbox" data-options="prompt:'输入仓库地址'"></td>
                </tr>
                <tr>
                    <td>备注信息:</td>
                    <td><input  name="info" class="easyui-textbox" data-options="prompt:'输入备注信息'"></td>
                </tr>
            </table>
        </form>
    </div>
    <%--查看库存--%>
    <div id="pro_dialog">
        <table id="pro_datagrid">

        </table>
    </div>
    <%--查看库存高级查询--%>
    <div id="pro_toolbar">
        <input id="pro_search" class="easyui-textbox" data-options="prompt:'按商品名称/编码查询'" \
               style="width:630px">
        <a  href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'"  onclick="searchs()"></a>
    </div>
</body>
</html>
