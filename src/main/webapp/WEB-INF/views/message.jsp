<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="/static/common/common.jsp"%>
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

                    console.log(data.result);

                    $('#sp').html(data.result);
                },'json');
            });
        })
    </script>

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
<div class="easyui-layout" data-options="fit:true">
    <div data-options="region:'north',width:160,height:45" align="center" style="background-color: cornflowerblue">
        <button style="background-color: pink" onclick="window.history.back()">回到商城</button>
        <span style="margin-right: 100px"><font color="#ffc0cb"><i>会员生日短信提醒功能</i></font></span>
        <input id="tel" class="easyui-textbox" data-options="width:200,prompt:'请输入正确的手机号码',validType:'phoneNum'"/>
        <a class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" id="searchs"></a>
    </div>
    <div data-options="region:'center'" align="center" style="background-color: whitesmoke">
        <div class="by">
            <span class="bg" id="sp"></span>
        </div>
    </div>
    <div data-options="region:'south',height:300" style="background-color: whitesmoke">
        <div class="by">
        </div>
    </div>
</div>


</body>
</html>
