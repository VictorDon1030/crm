<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>统计</title>
    <script type="text/javascript" src="/home-face/plugin/echart/echarts-all.js"></script>
    <style type="text/css">
        .live{
            position:absolute;
            right:-100px;
            top:-10px;
        }
        .bar-one{
            position:absolute;
            right:-100px;
            top:280px;
        }

    </style>
</head>
<body>
<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
<div id="main" style="height:280px;width: 280px;margin-left: 50px"></div>
<!-- ECharts单文件引入 -->
<div class="live" id="bar" style="height:280px;width: 580px;margin-right: 50px;margin-top: 20px"></div>
<!---->
<div id="pie" style="height:280px;width: 280px;margin-left: 50px"></div>
<div class="bar-one" id="bar-one" style="height:280px;width: 600px;margin-right: 20px"></div>
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
                data : ['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月']
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
                name:'销售数量',
                type:'bar',
                data:[2.0, 4.9, 7.0, 23.2, 25.6, 76.7, 135.6, 162.2, 32.6, 20.0, 6.4, 3.3]
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
            text: '店铺业绩'
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
                data : ['家具类','食品类','日常类','全部分类']
            }
        ],
        series : [
            {
                name:'营业额',
                type:'bar',
                data:[29034, 104970, 131744, 630230]
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
            text:'出入库报表',
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
                    {value:800, name:'邮件营销'}
                ]
            }
        ]
    };
    // 为echarts对象加载数据
    myChart.setOption(option);
</script>
</body>
</html>
