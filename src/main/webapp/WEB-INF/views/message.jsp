<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="/static/common/common.jsp"%>
    <script type="text/javascript" src="/static/js/prefixfree.min.js"></script>
    <script type="text/javascript" src="/static/js/stopExecutionOnTimeout.js"></script>
    <title>短信</title>
    <script type="text/javascript">

        $(function () {
            $.extend($.fn.validatebox.defaults.rules, {
                phoneNum: { //验证手机号
                    validator: function(value, param){
                        return /^1[3-8]+\d{9}$/.test(value);
                    },
                    message: '请输入正确的手机号码。'
                }});

            $('#searchs').click(function () {
                var tel = $("#tel").textbox("getValue");
                $.get('/tryingMsg.do',{telNum:tel},function (data) {


                    $('#sp').html(data.result);
                },'json');
            });
        })
    </script>

    <style class="cp-pen-styles">body {
        background: radial-gradient(200% 100% at bottom center, #0070aa, #0b2570, #000035, #000);
        background: radial-gradient(220% 105% at top center, #000 10%, #000035 40%, #0b2570 65%, #0070aa);
        background-attachment: fixed;
        overflow: hidden;
    }

    @keyframes rotate {
        0% {
            transform: perspective(400px) rotateZ(20deg) rotateX(-40deg) rotateY(0);
        }
        100% {
            transform: perspective(400px) rotateZ(20deg) rotateX(-40deg) rotateY(-360deg);
        }
    }
    .stars {
        transform: perspective(500px);
        transform-style: preserve-3d;
        position: absolute;
        bottom: 0;
        perspective-origin: 50% 100%;
        left: 50%;
        animation: rotate 90s infinite linear;
    }

    .star {
        width: 2px;
        height: 2px;
        background: #F7F7B6;
        position: absolute;
        top: 0;
        left: 0;
        transform-origin: 0 0 -300px;
        transform: translate3d(0, 0, -300px);
        backface-visibility: hidden;
    }
    </style>

    <style type="text/css">
        .bg{
            margin-left: 10px;
            font-size: 2ch;
            color: #00bbee;
            font-family: 'Microsoft YaHei';
        }
        .by{
            margin-top: 20px;
        }
    </style>
</head>
<body>
    <button style="background-color: pink;margin-left: 200px" onclick="window.history.back()">回到商城</button>
    <span style="margin-right: 100px;margin-left: 50px"><font color="#ffc0cb"><i>会员生日短信提醒功能</i></font></span>
    <input id="tel" class="easyui-textbox" data-options="width:200,prompt:'请输入正确的手机号码',validType:'phoneNum'"/>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" id="searchs"></a>
<div class="by" align="center" style="margin-top: 50px">
    <span class="bg" id="sp"></span>
</div>
<div class="stars">
</div>

<script>
    $(document).ready(function () {
        var stars = 800;
        var $stars = $('.stars');
        var r = 800;
        for (var i = 0; i < stars; i++) {
            if (window.CP.shouldStopExecution(1)) {
                break;
            }
            var $star = $('<div/>').addClass('star');
            $stars.append($star);
        }
        window.CP.exitedLoop(1);
        $('.star').each(function () {
            var cur = $(this);
            var s = 0.2 + Math.random() * 1;
            var curR = r + Math.random() * 300;
            cur.css({
                transformOrigin: '0 0 ' + curR + 'px',
                transform: ' translate3d(0,0,-' + curR + 'px) rotateY(' + Math.random() * 360 + 'deg) rotateX(' + Math.random() * -50 + 'deg) scale(' + s + ',' + s + ')'
            });
        });
    });

</script>


</body>
</html>
