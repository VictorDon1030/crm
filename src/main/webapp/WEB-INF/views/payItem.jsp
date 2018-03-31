<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="/static/common/common.jsp"%>
    <script type="text/javascript" src="/static/js/payItem.js"></script>
    <title>日常支出</title>
</head>
<body>
<div class="easyui-layout" data-options="fit:true" style="width:700px;height:350px;">
    <%--<div data-options="region:'north'" style="height:60px">
        <!--选项卡：用2个a标签-->
        <div style="margin-top: 10px;margin-bottom: 30px;">
            <a href="javascript:window.location.href='/pay/view.do'"><font style="font-size: 18px;">支出录入</font></a>
            <a href="javascript:window.location.href='/payItem/view.do'"><font style="font-size: 18px;">支出明细</font></a>
        </div>
    </div>--%>

    <div data-options="region:'center',iconCls:'icon-ok'"  style="padding:5px;margin-top: 0px;">
        <table style="margin-top: 0px;margin-left:20px;" >
            <!--payItem数据表格-->
            <tr>
                <table id="payItem_datagrid" style="margin-top: 10px;" ></table>
            </tr>

        </table>
    </div>
</div>

<!--payItem数据表格的toolbar-->
<div id="pay_toolbar">
    <a class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="window.open('/payItem/exportXls.do')">导出报表</a>
    <a href="#" class="easyui-linkbutton" data-options="plain:true" data-btn="today">今日</a>
    <a href="#" class="easyui-linkbutton" data-options="plain:true" data-btn="week">本周</a>
    <a href="#" class="easyui-linkbutton" data-options="plain:true" data-btn="month">本月</a>
    <a href="#" class="easyui-linkbutton" data-options="plain:true" data-btn="other">其他</a>
    <nobr id="show_Other" style="display: none;">
        <input class="easyui-datetimebox" data-options="prompt:'开始时间'" name="startDate" id="startDate">&nbsp;
        &nbsp;至&nbsp;&nbsp;
        <input class="easyui-datetimebox" data-options="prompt:'结束时间'" name="endDate" id="endDate">
        <a class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" data-btn="searchs">搜索</a>
    </nobr>

</div>

</body>
</html>
