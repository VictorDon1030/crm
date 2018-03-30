<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="/static/common/common.jsp"%>
    <link rel="stylesheet" type="text/css" href="/static/plugins/timeline/main.css" />
    <script type="text/javascript" src="/static/plugins/echart/echarts.min.js"></script>
    <script type="text/javascript" src="/static/plugins/timeline/jquery.timelinr-0.9.53.js"></script>
    <script type="text/javascript" src="/static/js/pay.js"></script>
    <title>日常支出</title>
    <style type="text/css">
        #timeline {width: 760px;height: 440px;overflow: hidden;margin: 40px auto;position: relative;background: url('/static/plugins/timeline/dot.gif') 110px top repeat-y;}
        #dates {width: 115px;height: 440px;overflow: hidden;float: left;}
        #dates li {list-style: none;width: 100px;height: 100px;line-height: 100px;font-size: 18px; padding-right:20px; text-align:right; background: url('/static/plugins/timeline/biggerdot.png') 108px center no-repeat;}
        #dates a {line-height: 38px;padding-bottom: 10px;}
        #issues {width: 630px;height: 440px;overflow: hidden;float: right;}
        #issues li {width: 630px;height: 440px;list-style: none;}
        #issues li h1 {color: #ffcc00;font-size: 30px; height:52px; line-height:52px; text-shadow: #000 1px 1px 2px;}
        #issues li p {font-size: 14px;margin: 10px;line-height: 26px;}
    </style>

</head>

<script type="text/javascript">
    $(function(){
        $().timelinr({
            orientation: 	'vertical',
            issuesSpeed: 	200,
            datesSpeed: 	100,
            arrowKeys: 		'true',
            startAt:		3
        });
    });
</script>

<body>

    <div class="easyui-layout" data-options="fit:true" style="width:700px;height:350px;">
        <!--外北-->
        <div data-options="region:'north'" style="height:50px">
            <!--选项卡：用2个a标签-->
            <div style="margin-top: 10px;margin-bottom: 30px;">
                <a href="javascript:window.location.href='/pay/view.do'"><font style="font-size: 18px;">支出录入</font></a>
                <a href="javascript:window.location.href='/payItem/view.do'"><font style="font-size: 18px;">支出明细</font></a>
            </div>
        </div>
        <!--外中-->
        <div data-options="region:'center',iconCls:'icon-ok'"  style="padding:5px">
            <div class="easyui-layout" data-options="fit:true">


                <!--里东布局-->
                <div data-options="region:'east',collapsible:false" style="width:400px">
                    <!--时间轴-->
                    <div style="margin-top: 20px;margin-left: 10px;">支出记录（近期5笔）</div>
                    <div style="margin-top: 20px;margin-left: 10px;">小分类&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;大分类</div>

                    <div id="timeline">
                        <ul id="dates">
                            <!--存放消费小分类-->
                            <c:forEach items="${times}" var="map">
                                <li><a href='"#"+${map.id}'>${map.type}</a></li>
                            </c:forEach>
                        </ul>
                        <ul id="issues">
                            <!--点击每个分类，展开的文字：所属大类、消费时间、消费金额-->
                            <c:forEach items="${times}" var="map">
                                <li id='${map.id}'>
                                    <h1>${map.pType}</h1>
                                    <p>￥${map.amount}</p>
                                    <p>${map.date}</p>
                                </li>
                            </c:forEach>
                        </ul>
                    </div>
                </div>


                <!--西布局-->
                <div data-options="region:'west',collapsible:false"  style="width:380px">
                    <div style="margin-left: 15px;margin-top: 30px;">
                        <select class="easyui-combobox" name="name" id="maxType">
                        </select>

                        <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" data-btn="setType">分类设置</a>
                    </div>
                    <form action="/pay/save.do" method="post" id="pay_form" style="margin-top: 20px;">
                        <!--数据表格-->
                        <table style="margin-top: 15px;margin-left:20px;width:330px;height:150px;"id="pay_datagrid">

                        </table>
                        <div style="margin-left: 20px;margin-top: 20px;" >
                            <input class="easyui-textbox" data-options="label:'总支出',width:300,prompt:'输入金额 RMB'" name="amount">
                        </div>
                        <div style="margin-left: 20px;margin-top: 20px;" >
                            <input id="showDate" class="easyui-datetimebox" data-options="label:'日期',width:300,panelHeight:'auto',currentText:'今天'" name="date">
                        </div>
                        <div style="margin-left: 20px;margin-top: 20px;" >
                            <input class="easyui-textbox" data-options="label:'备注',width:300,prompt:'备注'" name="remark">
                        </div>
                        <div style="margin-left: 20px;margin-top: 20px;" >
                            <a href="#" class="easyui-linkbutton" style="background-color: #0000FF;" data-btn="submit">确认</a>
                        </div>

                    </form>
                </div>
                <!--中布局-->
                <div data-options="region:'center',collapsible:false"  >
                    <!--4个按钮-->
                    <div style="margin-top: 20px;margin-left:20px;">
                        <a id="today" href="#" class="easyui-linkbutton" data-options="plain:true" data-btn="today">今日</a>
                        <a href="#" class="easyui-linkbutton" data-options="plain:true" data-btn="week">本周</a>
                        <a href="#" class="easyui-linkbutton" data-options="plain:true" data-btn="month">本月</a>
                        <a href="#" class="easyui-linkbutton" data-options="plain:true" data-btn="year">今年</a>
                    </div>

                    <!--圆饼插件-->
                    <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
                    <div id="pie" style="width: 250px;height:200px;margin-left: 20px;"></div>
                    <script type="text/javascript">
                        // 基于准备好的dom，初始化echarts实例
                        var myChart = echarts.init(document.getElementById('pie'));

                        // 指定图表的配置项和数据
                        var option = {
                            title : {
                                text: '支出报表',
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

                    <!--按照支出大分类maxType分组查询的数据表格-->
                    <table id="maxType_datagrid" style="width: 350px;height: 250px;margin-top: 20px;">

                    </table>

                </div>
            </div>
        </div>
    </div>

<!--数据表格上的toolbar:用来新增对应大类型里的小类型-->
    <div id="pay_toolbar">
        <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" data-btn="input">添加支出小分类</a>
        <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" data-btn="delete_minType">删除</a>
        <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-reload',plain:true" data-btn="reload_minType">刷新</a>
    </div>

<!--新增支出小类型的对话框-->
    <div id="minType_dialog" class="easyui-dialog" data-options="width:300,height:300,closed:true,buttons:'#minType_dialog_btns'">
        <form id="minType_form" method="post">
            <div style="margin-left: 50px;margin-top: 20px;" >
                所属大分类：<span id="showType"></span>
            </div>
            <div style="margin-left: 50px;margin-top: 20px;" >
                <input class="easyui-textbox" data-options="width:200,prompt:'输入小分类'" name="name" >
            </div>
        </form>
    </div>
<!--分类设置的对话框-->
    <div id="setType_dialog" class="easyui-dialog" data-options="width:600,height:380,closed:true,buttons:'#setType_dialog_btns',title:'添加分类'">
        <form id ="setType_form"  method="post" >
                <div><center><font style="font-size: 20px;">分类设置</font></center></div>

                <input  type="hidden"  name="id" >
                <table align="center"  style="margin-top:15px;" >
                    <tr>
                        <td ><center>大分类</center></td>
                        <td ><center>小分类</center></td>
                    </tr>
                    <tr>
                        <td ><a href="#" data-btn="openAddMaxType"><img src="/static/img/add.png"></a></td>
                        <td ><a href="#" data-btn="openAddMinType"><img src="/static/img/add.png"></a></td>
                    </tr>
                    <tr>
                        <td>
                            <table id="setMaxType"></table>
                        </td>
                        <td>
                            <table id="setMinType"></table>
                        </td>
                    </tr>
                </table>
        </form>
    </div>
<!--minType_dialog的buttons-->
    <div id="minType_dialog_btns">
        <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-ok',plain:true" data-btn="minType_dialog_ok">确认</a>
        <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" data-btn="minType_dialog_cancel">取消</a>
    </div>
<!--setType_dialog的buttons-->
    <div id="setType_dialog_btns">
        <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-ok',plain:true" data-btn="setType_dialog_ok">确认</a>
        <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" data-btn="setType_dialog_cancel">取消</a>
    </div>

<!--添加大分类的对话框-->
    <div id="addMaxType_dialog" class="easyui-dialog" data-options="width:300,height:300,closed:true,buttons:'#addMaxType_dialog_btns'">
        <form id="addMaxType_form" method="post">
            <div style="margin-top: 5px;" >
                <center>添加大分类</center>
            </div>
            <div style="margin-left: 15px;margin-top: 40px;" >
                <input class="easyui-textbox" data-options="width:200,prompt:'输入大分类名称'" name="name" >
            </div>
        </form>
    </div>
<!--添加小分类的对话框-->
    <div id="addMinType_dialog" class="easyui-dialog" data-options="width:300,height:300,closed:true,buttons:'#addMinType_dialog_btns'">
        <form id="addMinType_form" method="post">
            <div style="margin-top: 5px;" >
                <center>添加小分类</center>
            </div>
            <div style="margin-left: 50px;margin-top: 20px;" >
                所属大分类：<span id="showMaxType"></span>
            </div>
            <div style="margin-left: 50px;margin-top: 20px;" >
                <input class="easyui-textbox" data-options="width:200,prompt:'输入小分类名称'" name="name" >
            </div>
        </form>
    </div>
<!--添加大分类对话框的buttons-->
    <div id="addMaxType_dialog_btns">
        <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-ok',plain:true" data-btn="addMaxType_dialog_ok">确认</a>
        <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" data-btn="addMaxType_dialog_cancel">取消</a>
    </div>
<!--添加小分类对话框的buttons-->
    <div id="addMinType_dialog_btns">
        <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-ok',plain:true" data-btn="addMinType_dialog_ok">确认</a>
        <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" data-btn="addMinType_dialog_cancel">取消</a>
    </div>
</body>
</html>
