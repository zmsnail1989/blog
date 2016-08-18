<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="https://git.oschina.net/cool_snail/blog.git"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!doctype html>
<html class="no-js fixed-layout">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>SNAIL的个人博客</title>
<meta name="description" content="首页">
<meta name="keywords" content="index">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="renderer" content="webkit">
<meta http-equiv="Cache-Control" content="no-siteapp" />
<link rel="icon" type="image/png" href="/assets/i/favicon.png">
<link rel="apple-touch-icon-precomposed" href="/assets/i/app-icon72x72@2x.png">
<meta name="apple-mobile-web-app-title" content="Amaze UI" />
<link rel="stylesheet" href="/assets/css/amazeui.min.css" />
<link rel="stylesheet" href="/assets/css/admin.css">
</head>
<body>
    <form:form id="form" class="am-form am-form-horizontal" method="post" commandName="child">
        <form:hidden path="id"/>
        <form:hidden path="available"/>
        <form:hidden path="parentId"/>
        <form:hidden path="parentIds"/>
		<div class="am-form-group">
			<label class="am-u-sm-3 am-form-label">父节点名称：</label>
			<div class="am-u-sm-9">
				${parent.name}
			</div>
		</div>

		<div class="am-form-group">
			<label class="am-u-sm-3 am-form-label"><form:label path="name">子节点名称：</form:label></label>
			<div class="am-u-sm-9">
				<input type="text" id="name" name="name">
			</div>
		</div>
         <div class="am-form-group">
			<div class="am-u-sm-9 am-u-sm-push-3">
				<form:button type="submit" class="am-btn am-btn-primary">新增子节点</form:button>
			</div>
		</div>
    </form:form>
</body>
</html>