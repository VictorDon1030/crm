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
    <%@ include file="/static/common/common.jsp"%>
    <script type="text/javascript" src="/home-face/plugin/echart/echarts-all.js"></script>
    <script type="text/javascript">
        $(function () {
            $('#admin').click(function () {
                alert("时间不够 ==========================");
            });
        });
        
        function go() {
            window.location.href = '/checkout/view.do';
        }
    </script>
</head>
<body>
<div class="layui-layout layui-layout-admin">
    <!--顶部-->
    <div class="layui-header">
        <div class="ht-console">
            <div class="ht-user">
                <img src="../../home-face/images/Logo_40.png" />
                <a class="ht-user-name" id="admin">超级管理员</a>
            </div>
        </div>
        <span class="sys-title">德客超市系统</span>
        <ul class="ht-nav">
            <li class="ht-nav-item">
                <a target="_blank" href="javascript:go();">前台入口</a>
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
                    <a href="javascript:;"><i class="fa fa-cloud" style="color: yellow"></i>相关事宜</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;" data-url="/weather.do" data-id="1">牛犇天气</a></dd>
                        <dd><a href="javascript:;" data-url="/dictionary/view.do" data-id="35">数据字典</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;"><i class="fa fa-user" style="color:#00f7ff"></i>会员管理</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;" data-url="/member/view.do" data-id="4">会员列表</a></dd>
                        <dd><a href="javascript:;" data-url="/memberTopUp/view.do" data-id="5">会员充值</a></dd>
                        <dd><a href="javascript:;" data-url="/bonusPoint/view.do" data-id="7">积分管理</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;"><i class="fa fa-product-hunt" style="color: #00bbee"></i>商品管理</a>
                    <dl class="layui-nav-child">
                        <dd><a data-url="/product/view.do" href="javascript:;" data-id="9">商品列表</a></dd>
                        <dd><a data-url="/linkage/view.do" href="javascript:;" data-id="10">商品分类</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;"><i class="fa fa-cubes" style="color: #00ee00"></i>库存管理</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;" data-url="/orderBill/view.do" data-id="11">进货管理</a></dd>
                        <dd><a href="javascript:;" data-url="/depot/view.do" data-id="32">仓库管理</a></dd>
                        <dd><a href="javascript:;" data-url="/supplier/view.do" data-id="31">供应商管理</a></dd>
                        <dd><a href="javascript:;" data-url="/refund/view.do" data-id="12">采购退货</a></dd>
                        <dd><a href="javascript:;" data-url="/productStock/inventory.do" data-id="13">产品盘点</a></dd>
                        <dd><a href="javascript:;" data-url="/productStock/view.do" data-id="14">即时库存</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;"><i class="fa fa-money" style="color: #f4e039"></i>日常支出</a>
                    <dl class="layui-nav-child">
                        <dd><a data-url="/pay/view.do" href="javascript:;" data-id="15">支出录入</a></dd>
                        <dd><a data-url="/payItem/view.do" href="javascript:;" data-id="16">支出明细</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;"><i class="fa fa-bar-chart-o" style="color: #0000FF"></i>智能分析</a>
                    <dl class="layui-nav-child">
                        <dd><a data-url="/saleAnalyze/view.do" href="javascript:;" data-id="17">销售分析</a></dd>
                        <dd><a data-url="/memberAnalyze/view.do" href="javascript:;" data-id="18">会员分析</a></dd>
                        <dd><a data-url="/productAnalyze/view.do" href="javascript:;" data-id="19">产品分析</a></dd>
                        <dd><a data-url="/classAnalysis/view.do" href="javascript:;" data-id="30">类别分析</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;"><i class="fa fa-handshake-o" style="color: #1acbfc"></i>营销平台</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;" data-url="/gzl.do" data-id="20">营销短信</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;"><i class="fa fa-file-text-o" style="color: #00ee00"></i>营销订单</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;" data-url="/gzl.do" data-id="25">线上订单</a></dd>
                        <dd><a href="javascript:;" data-url="/gzl.do" data-id="26">线下订单</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;"><i class="fa fa-group " style="color: #f4e039"></i>咨询客服</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;" data-url="/zzh.do" data-id="27">渣渣辉</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;"><i class="fa fa-gear" style="color: #9b2979"></i>系统管理</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;" data-url="/syshome.do" data-id="29">店铺管理</a></dd>
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
                    <iframe id="import" src="/chat/statirName.do" width="100%" height="100%" frameborder="0"></iframe>
                </div>
            </div>
        </div>
    </div>
    <!--快捷菜单-->
    <div class="short-menu" style="display:none">
        <fieldset class="layui-elem-field layui-field-title">
            <legend style="color:#fff;padding-top:10px;padding-bottom:10px;">天气查询</legend>
            <iframe src="//tianqi.2345.com/plugin/widget/index.htm?s=1&z=1&t=0&v=0&d=3&bd=0&k=&f=&ltf=009944&htf=cc0000&q=1&e=1&a=1&c=54511&w=385&h=96&align=center" height="100" width="100%" style="background-color: white"></iframe>
            <div class="layui-field-box">
                <div style="width:832px;margin:0 auto;">
                    <%--<div class="windows-tile windows-two" align="center">--%>
                    <%--<div class="windows-tile windows-two" align="center">
                        <iframe src="//tianqi.2345.com/plugin/widget/index.htm?s=1&z=1&t=0&v=0&d=3&bd=0&k=&f=&ltf=009944&htf=cc0000&q=1&e=1&a=1&c=54511&w=385&h=96&align=center"></iframe>
                    </div>--%>
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
            <span>优雅</span>
        </div>
        <div class="setting-item skin skin-deepblue" data-skin="skin-deepblue">
            <span>正太色</span>
        </div>
        <div class="setting-item skin skin-pink" data-skin="skin-pink">
            <span>萝莉色</span>
        </div>
        <%--<div class="setting-item skin skin-green" data-skin="skin-green">
            <span>一碧千里</span>
        </div>--%>
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
