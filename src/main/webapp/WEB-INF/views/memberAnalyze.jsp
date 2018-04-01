<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="/static/common/common.jsp"%>
    <script type="text/javascript" src="/static/plugins/echart/echarts.min.js"></script>
    <script type="text/javascript" src="/static/js/memberAnalyze.js"></script>
    <title>智能分析</title>
</head>
<body>

    <div class="easyui-layout" data-options="fit:true" style="fit:true">
        <div data-options="region:'north'" style="height:60px">
            <!--北布局:查询按钮-->
            <div  style="margin-top: 10px;margin-left: 10px;">
                <a class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" data-btn="export" >导出报表</a>
                <a href="#" class="easyui-linkbutton" data-options="plain:true" data-btn="all">全部</a>
                <a href="#" class="easyui-linkbutton" data-options="plain:true" data-btn="today">今日</a>
                <a href="#" class="easyui-linkbutton" data-options="plain:true" data-btn="week">昨日</a>
                <a href="#" class="easyui-linkbutton" data-options="plain:true" data-btn="month">本月</a>
                <a href="#" class="easyui-linkbutton" data-options="plain:true" data-btn="other">其他</a>
                <nobr id="show_Other" style="display: none;">
                    <input class="easyui-datetimebox" data-options="prompt:'开始时间'" name="startDate" id="startDate">&nbsp;
                    &nbsp;至&nbsp;&nbsp;
                    <input class="easyui-datetimebox" data-options="prompt:'结束时间'" name="endDate" id="endDate">
                    <a class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" data-btn="searchs">搜索</a>
                </nobr>

            </div>
        </div>

        <div data-options="region:'center',iconCls:'icon-ok'">
            <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
            <div id="main" style="width: 1000px;height:360px;"></div>
        </div>

        <!--南布局-->
        <div data-options="region:'south',collapsible:false"  style="height: 180px;">
            <table id="memberAanalyze_datagrid" style="height: 250px;margin-top: 20px;">

            </table>
        </div>
    </div>

</body>
</html>
