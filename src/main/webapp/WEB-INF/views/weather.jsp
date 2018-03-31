<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="/static/common/common.jsp"%>
    <title>天气</title>
    <script type="text/javascript">
        $(function () {
            $('#searchs').click(function () {
                var cityName = $("#city").textbox("getValue");
                $.get('/trying.do',{city:cityName},function (data) {
                    var weather = data.result.HeWeather5;
                    //基础信息
                    var base = weather[0].basic;
                    //天气预报
                    var day = weather[0].daily_forecast;
                    //生活指标数
                    var state = weather[0].suggestion;


                    //白天信息
                    var daytime = day[0].cond.txt_d;
                    //晚上天气
                    var nighttime = day[0].cond.txt_n;
                    //最高温度
                    var max = day[0].tmp.max;
                    //最低温度
                    var min = day[0].tmp.min;
                    //当前城市
                    var city = base.city;
                    //更新时间
                    var time = base.update.loc;
                    //空气质量
                    var air = weather[0].aqi.city.qlty;
                    //降水量
                    var rain = day[0].pcpn;
                    //风向
                    var dir = day[0].wind.dir;
                    //风力
                    var spd = day[0].wind.spd;
                    //舒适度指数
                    var brf = state.comf.brf;
                    //推荐事宜
                    var brf_txt = state.comf.txt;



                    //数据填充


                    //城市
                    $('#iCity').textbox('setValue',city);
                    //时间
                    $('#iTime').textbox('setValue',time);
                    //白天天气
                    $('#iDayTime').textbox('setValue',daytime);
                    //夜晚天气
                    $('#iNightTime').textbox('setValue',nighttime);
                    //最高温度
                    $('#iMax').textbox('setValue',max);
                    //最低温度
                    $('#iMin').textbox('setValue',min);
                    //风力
                    $('#iDir').textbox('setValue',dir);
                    //风向
                    $('#ISpd').textbox('setValue',spd);
                    //舒适指数
                    $('#iBrf').textbox('setValue',brf);
                    //推荐事宜
                    $('#iBrf_txt').textbox('setValue',brf_txt);
                },'json');
            });
        })
    </script>

    <style type="text/css">
        .bg{
            margin-left: 10px;
            font-size: 1.4ch;
            color: #00bbee;
            font-family: 'Microsoft YaHei';
        }
        .by{
            margin-top: 20px;
        }
    </style>
</head>
<body>
<div class="easyui-layout" data-options="fit:true">
    <div data-options="region:'north',width:160,height:45" align="center" style="background-color: cornflowerblue">
        <span style="margin-right: 100px"><font color="#ffc0cb"><i>超级简易天气预报</i></font></span>
        <input id="city" class="easyui-textbox" data-options="width:310" prompt="技术有限,请使用中文拼音查询,只能查市一级"/>
        <a class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" id="searchs"></a>
    </div>
    <div data-options="region:'center'" align="center" style="background-color: whitesmoke">
        <div style="margin-top: 80px">
            <span class="bg">当前城市 : <input id="iCity" class="easyui-textbox" iconCls="icon-city"/></span>
            <span class="bg">更新时间 : <input id="iTime" class="easyui-textbox" iconCls="icon-Itime"/></span>
        </div>
        <div class="by">
            <span class="bg">白天天气 : <input id="iDayTime" class="easyui-textbox" iconCls="icon-dayTime"/></span>
            <span class="bg">晚上天气 : <input id="iNightTime" class="easyui-textbox" iconCls="icon-nightTime"/></span>
        </div>
        <div class="by">
            <span class="bg">最高温度 : <input id="iMax" class="easyui-textbox" iconCls="icon-max"/></span>
            <span class="bg">最低温度 : <input id="iMin" class="easyui-textbox" iconCls="icon-min"/></span>
        </div>
        <div class="by">
            <span class="bg">今日风向 : <input id="iDir" class="easyui-textbox" iconCls="icon-dir"/></span>
            <span class="bg">今日风力 : <input id="ISpd" class="easyui-textbox" iconCls="icon-spd"/></span>
        </div>
        <div class="by">
            <span class="bg">舒适指数 : <input id="iBrf" class="easyui-textbox" iconCls="icon-brf"/></span>
        </div>
        <div class="by">
            <span class="bg">推荐事宜 : <input id="iBrf_txt" class="easyui-textbox" iconCls="icon-brf_txt" data-options="width:500,height:50,multiline:true"/></span>
        </div>
    </div>
</div>
</body>
</html>
