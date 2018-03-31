<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>统计</title>
    <%@ include file="/static/common/common.jsp"%>
    <script type="text/javascript" src="/home-face/plugin/echart/echarts-all.js"></script>
    <style type="text/css">
        *{ /*注意*/
            margin: 0;
            padding:  0;
        }
    </style>
</head>
<body>
<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
<div  id="main" style="width: 50%;height:350px;float: left"></div>
<div  id="main2" style="width: 50%;height:350px;float: left"></div>
<div  id="main3" style="width: 50%;height:280px;float: left"></div>
<div  id="main4" style="width: 50%;height:280px;float: left"></div>
<script type="text/javascript">
    // 基于准备好的dom，初始化echarts实例
    var charts = [];
    myChart= echarts.init(document.getElementById('main'));

    // 指定图表的配置项和数据
    var option = {
        title:{
            text:'分类库存',
            subtext:'浪吗-超市',
            x:'center'
        },
        tooltip : {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        series : [
            {
                type: 'pie',
                radius : '55%',
                center: ['50%', '60%'],
                data:${one},
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
    charts.push(myChart);
    ///////////////////////////////////////////////////////
    myChart1 = echarts.init(document.getElementById('main2'));
    var option1 = {
        title: {
            text: '商品销售记录'
        },
        tooltip: {
            trigger: 'axis'
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        toolbox: {
            feature: {
                saveAsImage: {}
            }
        },
        xAxis: {
            type: 'category',
            boundaryGap: false,
            data: ${productName}
        },
        yAxis: {
            type: 'value'
        },
        series: [
            {
                name:'成本',
                type:'line',
                stack: '总量',
                data:${totalCost}
            },
            {
                name:'销售',
                type:'line',
                stack: '总量',
                data:${totalSale}
            },
            {
                name:'利润',
                type:'line',
                stack: '总量',
                data:${totalProfit}
            }
        ]
    };
    myChart1.setOption(option1);
    charts.push(myChart1);

    /////////////////////

    myChart2 = echarts.init(document.getElementById('main3'));
    var option2 = {
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
    myChart2.setOption(option2);
    charts.push(myChart2);

    ///////////////////////////////
    myChart3 = echarts.init(document.getElementById('main4'));
    var option3 = {
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
    myChart3.setOption(option3);
    charts.push(myChart3);

    window.onresize = function(){
        for(var i = 0; i < charts.length; i++){
            charts[i].resize();
        }
    };
</script>

</body>
</html>
