<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<head lang="en">
<meta charset="UTF-8">
<title>SNAIL的个人博客</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
<meta name="format-detection" content="telephone=no">
<meta name="renderer" content="webkit">
<meta http-equiv="Cache-Control" content="no-siteapp" />
<link rel="alternate icon" type="image/png" href="/assets/i/favicon.png">
<link rel="stylesheet" href="http://cdn.amazeui.org/amazeui/2.7.0/css/amazeui.min.css" />
<style>
.header {
	text-align: center;
}

.header h1 {
	font-size: 200%;
	color: #333;
	margin-top: 30px;
}

.header p {
	font-size: 14px;
}
</style>
</head>
<body>
	<div class="header">
		<div class="am-g">
			<h1>
				<img src="/assets/i/h-login-pic.jpg" onclick="goback()"
					style="max-width: 100px; max-height: 340px;">
			</h1>
			<p>
				What is a man's first duty? the answer is brief: To be himself<br />
			</p>
		</div>
		<hr />
	</div>
	<div class="am-g">
		<div class="am-u-lg-6 am-u-md-8 am-u-sm-centered">
			<form class="am-form" method="post" action="">
				<label for="username">用户名:</label> 
				<input type="text" name="username" value="<shiro:principal/>"><label
					for="password">密码:</label> <input type="password" name="password"> <br> <label for="rememberMe">
					<input type="checkbox" name="rememberMe" value="true">记住我
				</label> <br />
				<div class="am-cf">
					<input type="submit" value="登 录" class="am-btn am-btn-primary am-btn-sm am-fl"> 
					<input type="button" id="forget" value="忘记密码 ^_^? "
						class="am-btn am-btn-default am-btn-sm am-fr">
				</div>
			</form>
			<hr>
			<p>© 2015 AllMobilize, Inc. Licensed under MIT license.</p>
		</div>
	</div>
	<script type="text/javascript" src="/assets/js/jquery.min.js"></script>
	<script type="text/javascript" src="/assets/lib/layer/layer.js"></script>
	<script type="text/javascript">
		function goback(){
			window.location.href="/";
		};
		var msg="${error}";
		if(msg!=null&&msg!=''){
			layer.alert(msg);
		}
	</script>
</body>
</html>
