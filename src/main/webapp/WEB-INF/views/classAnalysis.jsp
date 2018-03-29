<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="/static/common/common.jsp"%>
    <script type="text/javascript" src="/static/plugins/echart/echarts.min.js"></script>
    <script type="text/javascript" src="/static/js/classAnalysis.js"></script>
    <title>智能分析</title>
</head>
<body>

    <div class="easyui-layout" data-options="fit:true" style="fit:true">
        <div data-options="region:'north'" style="height:60px">
            <!--选项卡：用2个a标签-->
            <div style="margin-top: 10px;margin-bottom: 30px;">
                <a href="javascript:window.location.href='/productAnalyze/view.do'"><font style="font-size: 18px;">产品分析</font></a>
                <a href="javascript:window.location.href='/classAnalysis/view.do'"><font style="font-size: 18px;">类别分析</font></a>
            </div>
        </div>

        <div data-options="region:'center',iconCls:'icon-ok'">
            <div class="easyui-layout" data-options="fit:true">

                <!--北布局:查询按钮-->
                <div data-options="region:'north',collapsible:false" style="height: 60px;">
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

                <!--中布局-->
                <div data-options="region:'center',collapsible:false" >
                    <!--圆饼插件-->
                    <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
                    <div id="pie" style="width: 250px;height:200px;margin-left: 100px;margin-top: 100px;"></div>
                    <script type="text/javascript">
                        // 基于准备好的dom，初始化echarts实例
                        var myChart = echarts.init(document.getElementById('pie'));

                        // 指定图表的配置项和数据
                        var option = {
                            title : {
                                text: '类别分析',
                                x:'center'
                            },
                            tooltip : {//工具提示
                                trigger: 'item',
                                formatter: "{a} <br/>{b} : {c} ({d}%)"
                            },
                            legend: {//比例的转向
                                orient: 'vertical',
                                left: 'left',
                                data: ${types}//饼上的数据，相当于横轴：分组类型（maxType）
                            },
                            series : [
                                {//配置鼠标放在每一块饼上显示的内容
                                    name: '支出总额',//
                                    type: 'pie',
                                    radius : '55%',
                                    center: ['50%', '60%'],
                                    //每一块饼都必须是value:销售总额，name:分组类型，将这样的2对键值对数据封装到一个map里，有多个map，就放在list中
                                    data:${totalAmounts},

                                    itemStyle: {
                                        emphasis: {
                                            shadowBlur: 10,
                                            shadowOffsetX: 0,
                                            shadowColor: 'rgba(0, 0, 0, 0.5)'
                                        }
                                    }
                                }
                            ]
                        };
                        // 使用刚指定的配置项和数据显示图表。
                        myChart.setOption(option);
                    </script>
                </div>

                <!--东布局-->
                <div data-options="region:'east',collapsible:false"  style="width: 600px;">
                    <table id="classAnalysis_datagrid" style="height: 250px;margin-top: 20px;">

                    </table>
                </div>

            </div>
        </div>
    </div>

</body>
</html>
