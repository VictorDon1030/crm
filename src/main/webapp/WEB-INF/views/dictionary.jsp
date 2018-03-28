<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@include file="/static/common/common.jsp"%>
    <script type="text/javascript" src="/static/js/dictionary.js"></script>
</head>
<body>
<div class="easyui-layout" data-options="fit:true">
    <!--字典的布局:字典目录-->
    <div data-options="region:'west',border:true" title="数据字典" style="width:500px;">
        <table style="width:400px;height:250px" id="dictionary_datagrid">
        </table>
    </div>
    <!--明细的中间布局：字典目录明细-->
    <div data-options="region:'center',border:false">
        <table style="width:400px;height:250px" id="dictionaryItem_datagrid">
        </table>
    </div>
</div>

<!--字典的布局:字典目录的tb-->
<div id="dictionary_tb">
    <a class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" data-cmd="dictionary_add">新增</a>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" data-cmd="dictionary_edit">编辑</a>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" data-cmd="dictionary_delete">删除</a>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-reload',plain:true" data-cmd="dictionary_reload">刷新</a>
</div>

<!--明细布局:字典目录明细的tb-->
<div id="dictionaryItem_tb">
    <a class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" data-cmd="dictionaryItem_add">新增</a>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" data-cmd="dictionaryItem_edit">编辑</a>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" data-cmd="dictionaryItem_delete">删除</a>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-reload',plain:true" data-cmd="dictionaryItem_reload">刷新</a>
</div>


<!--dictionary的编辑页面-->
<div id="dictionary_dialog" class="easyui-dialog" data-options="width:400,height:250,closed:true,buttons:'#btn'">
    <form id="dictionary_form" method="post">
        <div style="margin-left: 50px;margin-top: 20px;" >
            <input type="hidden" name="id">
        </div>
        <div style="margin-left: 50px;margin-top: 20px;" >
            <input class="easyui-textbox" data-options="label:'字典目录编码',lableWidth:80,width:300" name="sn">
        </div>
        <div style="margin-left: 50px;margin-top: 20px;" >
            <input class="easyui-textbox" data-options="label:'字典目录名称',lableWidth:80,width:300" name="name">
        </div>
        <div style="margin-left: 50px;margin-top: 20px;">
            <input class="easyui-textbox" data-options="label:'字典目录简介',lableWidth:80,width:300" name="intro">
        </div>
    </form>
</div>

<!--dictionaryItem的编辑页面-->
<div id="dictionaryItem_dialog" class="easyui-dialog" data-options="width:400,height:250,closed:true,buttons:'#itemBtn'">
    <form id="dictionaryItem_form" method="post">
        <div style="margin-left: 50px;margin-top: 20px;" >
            <input type="hidden" name="id">
        </div>
        <div style="margin-left: 50px;margin-top: 20px;" >
            <input class="easyui-textbox" data-options="label:'条码名称',lableWidth:80,width:300" name="name">
        </div>
        <div style="margin-left: 50px;margin-top: 20px;" >
            <input class="easyui-textbox" data-options="label:'条目简介',lableWidth:80,width:300" name="intro">
        </div>
        <div style="margin-left: 50px;margin-top: 20px;">
            <select id="dictionaryItem" class="easyui-combobox" name="dictionary.id" data-options="url:'/dictionary/selectAll.do',label:'字典目录',
            width:300, panelHeight:'auto',valueField:'id',textField:'name',readonly:true">
            </select>
        </div>
    </form>
</div>

<div id="btn">
    <a class="easyui-linkbutton" data-options="iconCls:'icon-ok'" data-cmd="saveOrUpdate">确认</a>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" data-cmd="cancel">取消</a>
</div>
<div id="itemBtn">
    <a class="easyui-linkbutton" data-options="iconCls:'icon-ok'" data-cmd="itemSaveOrUpdate">确认</a>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" data-cmd="itemCancel">取消</a>
</div>
</body>
</html>
