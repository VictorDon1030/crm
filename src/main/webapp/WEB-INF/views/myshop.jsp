
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>我的店铺</title>
    <%@ include file="/static/common/common.jsp"%>
    <style type="text/css">
        .bg{
            margin-left: 20%;
            font-size: 2.5ch;
            font-family: "STSong";
        }
        .ct{
            margin-left: 50px;
            font-size: 2.5ch;
            font-family: "STSong";
        }
        .by{
            margin-top: 1.5%;
        }
    </style>
</head>
<body>
<div class="easyui-layout" data-options="fit:true">
    <div data-options="region:'north',width:'auto',height:200,border:false" align="center" style="background-color: #9cc8f7">
        <img src="/static/img/saber.png" style="width: auto; height: 196px"/>
    </div>
    <div data-options="region:'center'" style="background: whitesmoke;margin-top: 1px">
        <p class="by"><span class="bg">店铺名称</span><span class="ct">德客-叩丁狼分店</span></p>
        <p class="by"><span class="bg">店主姓名</span><span class="ct">匿名</span></p>
        <p class="by"><span class="bg">微信账号</span><span class="ct">12580</span></p>
        <p class="by"><span class="bg">行业类型</span><span class="ct">超市</span></p>
        <p class="by"><span class="bg">店铺简介</span><span class="ct">成人专卖</span></p>
        <p class="by"><span class="bg">店铺电话</span><span class="ct">110/120</span></p>
        <p class="by"><span class="bg">店铺宗旨</span><span class="ct">没有蛀牙</span></p>
        <p class="by"><span class="bg">店铺位置</span><span class="ct">天涯海角</span></p>
    </div>
    <div data-options="region:'south',height:40"style="background: whitesmoke">
        <p align="center">
            如有雷同,绝对抄袭
        </p>
    </div>
</div>
</body>
</html>
