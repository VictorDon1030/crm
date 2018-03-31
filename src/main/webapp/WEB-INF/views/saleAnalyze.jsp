<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="/static/common/common.jsp"%>
    <script type="text/javascript" src="/static/plugins/echart/echarts.min.js"></script>
    <script type="text/javascript" src="/static/js/saleAnalyze.js"></script>
    <title>智能分析</title>
</head>
<body>
    <div class="easyui-layout" data-options="fit:true" style="fit:true">
        <div data-options="region:'north'" style="height:50px">
            <!--北布局-->
            <!--选项卡：用2个a标签-->
            <div style="margin-top: 10px;margin-bottom: 30px;">
                <a href="javascript:window.location.href='/pay/view.do'"><font style="font-size: 18px;">销售报表</font></a>
            </div>
        </div>
        <!--中布局-->
        <div data-options="region:'center'">

            <div data-options="region:'north'" style="height:50px">
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

            <div data-options="region:'center'">


                <table id="saleAanalyze_datagrid" >

                </table>
            </div>

        </div>


    </div>



</body>
</html>
