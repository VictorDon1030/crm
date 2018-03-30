
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
    <script type="text/javascript" src="/static/js/supplier.js"></script>
</head>
<body>
    <!--列表-->
    <table id="supplier_datagrid">
    </table>
    <!--列表顶部按钮-->
    <div id="supplier_toolbar">
        <a class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'" onclick="add();">新增供应商</a>
        <a class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'" onclick="edit();">修改</a>
        <a class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-remove'" onclick="deleted();">删除</a>
        <a class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-reload'" onclick="reload();">刷新</a>


    </div>
    <div id="supplier_button">
        <a class="easyui-linkbutton button-line-green"  onclick="save()">保存</a>
        <a class="easyui-linkbutton button-line-grayish" onclick="cancel()">取消</a>
    </div>
    <div id="supplier_dialog">
        <form id="supplier_form" method="post">
            <table style="margin-top: 20px;margin-left: 60px" >
            <input type="hidden" name="id">
                <tr>
                    <td>供应商:</td>
                    <td><input name="name" class="easyui-textbox" data-options="prompt:'输入供应商名称'"></td>
                </tr>
                <tr>
                    <td>联系人:</td>
                    <td><input  name="realname" class="easyui-textbox" data-options="prompt:'输入联系人'"></td>
                </tr>
                <tr>
                    <td>联系电话:</td>
                    <td><input  name="tel" class="easyui-textbox" data-options="prompt:'联系电话'"></td>
                </tr>
                <tr>
                    <td>客户地址:</td>
                    <td><input  name="address" class="easyui-textbox" data-options="prompt:'输入客户地址'"></td>
                </tr>
                <tr>
                    <td>备注信息:</td>
                    <td><input  name="info" class="easyui-textbox" data-options="prompt:'输入备注信息'"></td>
                </tr>
            </table>
        </form>
    </div>
</body>
</html>
