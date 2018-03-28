<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge"> 
<meta name="viewport" content="width=device-width, initial-scale=1"> 
<title>德客登录</title>
	<script type="text/javascript" src="/static/plugins/easyui/jquery.min.js"></script>
	<script type="text/javascript" src="/static/plugins/easyui/jquery.easyui.min.js"></script>
<link rel="stylesheet" type="text/css" href="/static/css/normalize.css" />
<link rel="stylesheet" type="text/css" href="/static/css/demo.css" />
<!--必要样式-->
<link rel="stylesheet" type="text/css" href="/static/css/component.css" />
<!--[if IE]>
<script src="/static/js-x/html5.js"></script>
<![endif]-->
	<script type="text/javascript">
        function login() {
            $.post("/login.do",$("form").serialize(),function (data) {
                if (data.success) {
                    window.location.href = "/index.do";
                } else {
                    alert(data.msg);
                }
            },'json');
        }
	</script>
</head>
<body>
		<div class="container demo-1">
			<div class="content">
				<div id="large-header" class="large-header">
					<canvas id="demo-canvas"></canvas>
					<div class="logo_box">
						<h3>德客欢迎您</h3>
						<form action="#" name="f" method="post">
							<div class="input_outer">
								<span class="u_user"></span>
								<input name="username" class="text" style="color: #FFFFFF !important" type="text" value="admin" placeholder="请输入账户">
							</div>
							<div class="input_outer">
								<span class="us_uer"></span>
								<input name="password" class="text" style="color: #FFFFFF !important; position:absolute; z-index:100;"value="1" type="password"  placeholder="请输入密码">
							</div>
							<div class="mb2"><a class="act-but submit" href="javascript:login();" style="color: #FFFFFF">登录</a></div>
						</form>
					</div>
				</div>
			</div>
		</div><!-- /container -->
		<script src="/static/js-x/TweenLite.min.js"></script>
		<script src="/static/js-x/EasePack.min.js"></script>
		<script src="/static/js-x/rAF.js"></script>
		<script src="/static/js-x/demo-1.js"></script>
		<div style="text-align:center;">
</div>
	</body>
</html>