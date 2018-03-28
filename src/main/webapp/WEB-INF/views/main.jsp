<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="utf-8" />
    <title>德客交易平台</title>
    <link rel="shortcut icon" href="/home-face/images/Logo_40.png" type="image/x-icon">
    <!-- layui.css -->
    <link href="/home-face/plugin/layui/css/layui.css" rel="stylesheet" />
    <!-- font-awesome.css -->
    <link href="/home-face/plugin/font-awesome/css/font-awesome.min.css" rel="stylesheet" />
    <!-- animate.css -->
    <link href="/home-face/css/animate.min.css" rel="stylesheet" />
    <!-- 本页样式 -->
    <link href="/home-face/css/main.css" rel="stylesheet" />
    <script type="text/javascript" src="/home-face/plugin/echart/echarts-all.js"></script>
</head>
<body>
<div class="layui-layout layui-layout-admin">
    <!--顶部-->
    <div class="layui-header">
        <div class="ht-console">
            <div class="ht-user">
                <img src="../../home-face/images/Logo_40.png" />
                <a class="ht-user-name">超级管理员</a>
            </div>
        </div>
        <span class="sys-title">德客超市系统</span>
        <ul class="ht-nav">
            <li class="ht-nav-item">
                <a target="_blank" href="javascript:;">前台入口</a>
            </li>
            <li class="ht-nav-item">
                <a href="javascript:;" id="individuation"><i class="fa fa-tasks fa-fw" style="padding-right:5px;"></i>个性化</a>
            </li>
            <li class="ht-nav-item">
                <a href="/logout.do"><i class="fa fa-power-off fa-fw"></i>注销</a>
            </li>
        </ul>
    </div>
    <!--侧边导航-->
    <div class="layui-side">
        <div class="layui-side-scroll">
            <ul class="layui-nav layui-nav-tree" lay-filter="leftnav">
                <li class="layui-nav-item layui-this">
                    <a href="javascript:;"><i class="fa fa-home"></i>首页</a>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;"><i class="fa fa-user" style="color:#00f7ff"></i>会员管理</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;" data-url="#" data-id="1">新增会员</a></dd>
                        <dd><a href="javascript:;" data-url="datalist.html" data-id="2">会员列表</a></dd>
                        <dd><a href="javascript:;" data-url="datalist.html" data-id="3">会员充值</a></dd>
                        <dd><a href="javascript:;">会员充次</a></dd>
                        <dd><a href="javascript:;" data-url="datalist.html" data-id="4">积分管理</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;"><i class="fa fa-product-hunt" style="color: #00bbee"></i>商品管理</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;">新增商品</a></dd>
                        <dd><a href="javascript:;">商品列表</a></dd>
                        <dd><a href="javascript:;">商品分类</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;"><i class="fa fa-cubes" style="color: #00ee00"></i>库存管理</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;">进货管理</a></dd>
                        <dd><a href="javascript:;">采购退货</a></dd>
                        <dd><a href="javascript:;">产品盘点</a></dd>
                        <dd><a href="javascript:;" data-url="datalist.html" data-id="5">库存调拨</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;"><i class="fa fa-money" style="color: #f4e039"></i>日常支出</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;">支出录入</a></dd>
                        <dd><a href="javascript:;">支出明细</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;"><i class="fa fa-bar-chart-o" style="color: #0000FF"></i>智能分析</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;">销售分析</a></dd>
                        <dd><a href="javascript:;">会员分析</a></dd>
                        <dd><a href="javascript:;">产品分析</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;"><i class="fa fa-handshake-o" style="color: #1acbfc"></i>营销平台</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;">营销短信</a></dd>
                        <dd><a href="javascript:;">微信会员</a></dd>
                        <dd><a href="javascript:;">微信商城</a></dd>
                        <dd><a href="javascript:;">营销活动</a></dd>
                        <dd><a href="javascript:;">优惠券</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;"><i class="fa fa-file-text-o" style="color: #00ee00"></i>营销订单</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;">线上订单</a></dd>
                        <dd><a href="javascript:;">线下订单</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;"><i class="fa fa-group " style="color: #f4e039"></i>咨询客服</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;" data-url="#">渣渣辉</a></dd>
                        <dd><a href="javascript:;">古扎乐</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;"><i class="fa fa-gear" style="color: #9b2979"></i>系统管理</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;" data-url="/syshome.do">店铺管理</a></dd>
                    </dl>
                </li>
            </ul>
        </div>
    </div>
    <!--收起导航-->
    <div class="layui-side-hide layui-bg-cyan">
        <i class="fa fa-long-arrow-left fa-fw"></i>收起导航
    </div>
    <!--主体内容-->
    <div class="layui-body">
        <div style="margin:0;position:absolute;top:4px;bottom:0px;width:100%;" class="layui-tab layui-tab-brief" lay-filter="tab" lay-allowclose="true">
            <ul class="layui-tab-title">
                <li lay-id="0" class="layui-this">首页</li>
            </ul>
            <div id="main" style="height:250px;width: 250px"></div>
            <div class="layui-tab-content">
                <div class="layui-tab-item layui-show">
                    <p style="padding: 10px 15px; margin-bottom: 20px; margin-top: 10px; border:1px solid #ddd;display:inline-block;">
                        待填充功能
                        <span style="padding-left:1em;">xxxx</span>
                        <span style="padding-left:1em;">oooo</span>
                        <span style="padding-left:1em;">yyyyyy</span>
                    </p>
                    <fieldset class="layui-elem-field layui-field-title">
                        <legend>统计信息</legend>
                        <div class="layui-field-box">
                            <div style="display: inline-block; width: 100%;">
                                <div class="ht-box layui-bg-blue">
                                    <p>???</p>
                                    <p>今日折扣</p>
                                </div>
                                <div class="ht-box layui-bg-red">
                                    <p>???</p>
                                    <p>今日特价</p>
                                </div>
                                <div class="ht-box layui-bg-green">
                                    <p>???</p>
                                    <p>今日新品</p>
                                </div>
                                <div class="ht-box layui-bg-orange">
                                    <p>???</p>
                                    <p>会员特价</p>
                                </div>
                            </div>
                        </div>
                    </fieldset>
                </div>
            </div>
        </div>
    </div>
    <!--快捷菜单-->
    <div class="short-menu" style="display:none">
        <fieldset class="layui-elem-field layui-field-title">
            <legend style="color:#fff;padding-top:10px;padding-bottom:10px;">快捷菜单</legend>
            <div class="layui-field-box">
                <div style="width:832px;margin:0 auto;">

                    <div class="windows-tile windows-two" align="center">
                        <font size="4">唧唧复唧唧</font>
                    </div>
                    <div class="windows-tile windows-two" align="center">
                        <font size="4">木兰开飞机</font>
                    </div>
                    <div style="clear:both;"></div>
                </div>
            </div>
        </fieldset>
    </div>


    <!--个性化设置-->
    <div class="individuation animated flipOutY layui-hide">
        <ul>
            <li><i class="fa fa-cog" style="padding-right:5px"></i>个性化</li>
        </ul>
        <div class="explain">
            <small>从这里进行系统设置和主题预览</small>
        </div>
        <div class="setting-title">设置</div>
        <div class="setting-item layui-form">
            <span>侧边导航</span>
            <input type="checkbox" lay-skin="switch" lay-filter="sidenav" lay-text="ON|OFF" checked>
        </div>
        <div class="setting-item layui-form">
            <span>管家提醒</span>
            <input type="checkbox" lay-skin="switch" lay-filter="steward" lay-text="ON|OFF" checked>
        </div>
        <div class="setting-title">主题</div>
        <div class="setting-item skin skin-default" data-skin="skin-default">
            <span>低调优雅</span>
        </div>
        <div class="setting-item skin skin-deepblue" data-skin="skin-deepblue">
            <span>蓝色梦幻</span>
        </div>
        <div class="setting-item skin skin-pink" data-skin="skin-pink">
            <span>姹紫嫣红</span>
        </div>
        <div class="setting-item skin skin-green" data-skin="skin-green">
            <span>一碧千里</span>
        </div>
    </div>
</div>


<!-- layui.js -->
<script src="/home-face/plugin/layui/layui.js"></script>
<!-- layui规范化用法 -->
<script type="text/javascript">
    layui.config({
        base: '../js/'
    }).use('main');
</script>
</body>
</html>
