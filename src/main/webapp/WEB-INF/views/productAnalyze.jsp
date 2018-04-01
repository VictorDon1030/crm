<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="/static/common/common.jsp"%>
    <script type="text/javascript" src="/static/plugins/echart/echarts.min.js"></script>
    <script type="text/javascript" src="/static/js/productAnalyze.js"></script>
    <title>智能分析</title>
</head>
<body>

    <div class="easyui-layout" data-options="fit:true" style="fit:true">

        <div data-options="region:'center',iconCls:'icon-ok'">
            <div class="easyui-layout" data-options="fit:true">

                <!--北布局:查询按钮-->
                <div data-options="region:'north',collapsible:false" style="height: 70px;">
                    <div  style="margin-top: 10px;margin-left: 10px;">
                        <a class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="window.open('/productAnalyze/exportXls.do')" >导出报表</a>
                        <a id="all" href="#" class="easyui-linkbutton" data-options="plain:true" data-btn="all">全部</a>
                        <a id="today" href="#" class="easyui-linkbutton" data-options="plain:true" data-btn="today">今日</a>
                        <a id="week" href="#" class="easyui-linkbutton" data-options="plain:true" data-btn="week">昨日</a>
                        <a id="month" href="#" class="easyui-linkbutton" data-options="plain:true" data-btn="month">本月</a>
                        <a id="other" href="#" class="easyui-linkbutton" data-options="plain:true" data-btn="other">其他</a>
                        <nobr id="show_Other" style="display: none;">
                            <input class="easyui-datetimebox" data-options="prompt:'开始时间'" name="startDate" id="startDate">&nbsp;
                            &nbsp;至&nbsp;&nbsp;
                            <input class="easyui-datetimebox" data-options="prompt:'结束时间'" name="endDate" id="endDate">
                            <a class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" data-btn="searchs">搜索</a>
                        </nobr>

                    </div>
                </div>

                <!--中布局-->
                <div data-options="region:'center',collapsible:false" >
                    <!--柱状表的选择按钮-->
                    <div>
                        <a id="orderByNumber" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-numberOrder',plain:true" data-btn="orderByNumber">产品销量排行</a>
                        <a id="orderByProfit" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-productOrder',plain:true" data-btn="orderByProfit">产品销售毛利排行</a>
                    </div>

                    <!-- 为ECharts准备一个具备大小（宽高）的Dom ：按照销量排序-->
                    <div id="main" style="width: 900px;height:250px;display: none;"></div>

                    <!-- 为ECharts准备一个具备大小（宽高）的Dom :按照毛利排序-->
                    <div id="profit" style="width: 900px;height:250px;display: none;"></div>

                </div>

                <!--南布局-->
                <div data-options="region:'south',collapsible:false"  style="height: 180px;">
                    <table id="productAanalyze_datagrid" style="height: 250px;margin-top: 20px;">

                    </table>
                </div>

            </div>
        </div>
    </div>

</body>
</html>
