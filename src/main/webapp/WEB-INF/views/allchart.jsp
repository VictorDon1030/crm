<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>统计</title>
    <script type="text/javascript" src="/home-face/plugin/echart/echarts-all.js"></script>
    <style type="text/css">
        .live{
            /*position:absolute;
            right:-100px;
            top:-10px;*/
        }
        .bar-one{
           /* position:absolute;
            right:-100px;
            top:280px;*/
        }

    </style>
</head>
<body>
<div style="width: auto;height: auto" align="center">
<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
<%--<div id="main" style="height:280px;width: 280px;margin-left: 50px"></div>--%>
    <div align="center">
<div id="main" style="height:280px;width: 280px;"></div>
<!-- ECharts单文件引入 -->
<%--<div class="live" id="bar" style="height:300px;width: 580px;margin-right: 50px;margin-top: 10px"></div>--%>
<div class="live" id="bar" style="height:300px;width: 580px;margin-top: 10px"></div>
    </div>
<!---->
<%--<div id="pie" style="height:280px;width: 280px;margin-left: 50px"></div>--%>
<div id="pie" style="height:280px;width: 280px;"></div>
<%--<div class="bar-one" id="bar-one" style="height:280px;width: 600px;margin-right: 20px"></div>--%>
<div class="bar-one" id="bar-one" style="height:280px;width: 600px;"></div>
</div>
<script type="text/javascript">
    // 基于准备好的dom，初始化echarts图表
    var myChart = echarts.init(document.getElementById('bar-one'));
    var option = {
        title : {
            text: '月份报表',
            position : 'center'

        },
        tooltip : {
            trigger: 'axis'
        },
        calculable : true,
        xAxis : [
            {
                type : 'category',
                data : ${month}
            }
        ],
        yAxis : [
            {
                type : 'value',
                splitArea : {show : true}
            }
        ],
        series : [
            {
                name:'总销售',
                type:'bar',
                data:${monthSale}
            },
            {
                name:'总利润',
                type:'bar',
                data:${profit}
            }
        ]
    };

    // 为echarts对象加载数据
    myChart.setOption(option);
</script>




<script type="text/javascript">
    // 基于准备好的dom，初始化echarts图表
    var myChart = echarts.init(document.getElementById('bar'));
    var option = {
        title : {
            text: '分类销售额'
        },
        tooltip : {
            trigger: 'axis'
        },


        calculable : true,
        xAxis : [
            {
                type : 'value',
                boundaryGap : [0, 0.01]
            }
        ],
        yAxis : [
            {
                type : 'category',
                data : ${twoName}
            }
        ],
        series : [
            {
                name:'总成本',
                type:'bar',
                data:${twoCost}
            },
            {
                name:'总销售',
                type:'bar',
                data:${twoSale}
            }
        ]
    };
    // 为echarts对象加载数据
    myChart.setOption(option);
</script>



<script type="text/javascript">
    // 基于准备好的dom，初始化echarts图表
    var myChart = echarts.init(document.getElementById('pie'));
    var option = {
        title:{
            text:'销售金额报表',
            x:'center'
        },
        tooltip : {
            position:[10,10],
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        calculable : true,
        series : [
            {
                name:'访问来源',
                type:'pie',
                radius : ['50%', '70%'],
                itemStyle : {
                    normal : {
                        label : {
                            show : false
                        },
                        labelLine : {
                            show : false
                        }
                    },
                    emphasis : {
                        label : {
                            show : true,
                            position : 'center',
                            textStyle : {
                                fontSize : '20',
                                fontWeight : 'bold'
                            }
                        }
                    }
                },
                data:[
                    {value:335, name:'直接访问'},
                    {value:310, name:'邮件营销'}
                ]
            }
        ]
    };
    // 为echarts对象加载数据
    myChart.setOption(option);
</script>

<script type="text/javascript">
    // 基于准备好的dom，初始化echarts图表
    var myChart = echarts.init(document.getElementById('main'));
    var option = {
        title:{
            text:'分类库存',
            x:'center'
        },
        tooltip : {
            position:[10,10],
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        calculable : true,
        series : [
            {
                name:'分类',
                type:'pie',
                radius : ['50%', '70%'],
                itemStyle : {
                    normal : {
                        label : {
                            show : false
                        },
                        labelLine : {
                            show : false
                        }
                    },
                    emphasis : {
                        label : {
                            show : true,
                            position : 'center',
                            textStyle : {
                                fontSize : '20',
                                fontWeight : 'bold'
                            }
                        }
                    }
                },
                data:${one}
            }
        ]
    };
    // 为echarts对象加载数据
    myChart.setOption(option);
</script>
</body>
</html>
