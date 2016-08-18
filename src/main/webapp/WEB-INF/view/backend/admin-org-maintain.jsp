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
	<form:form id="form" class="am-form am-form-horizontal" method="post"
		commandName="organization">
		<form:hidden path="id" />
		<form:hidden path="available" />
		<form:hidden path="parentId" />
		<form:hidden path="parentIds" />

		<div class="am-form-group">
			<form:label for="user-name" class="am-u-sm-3 am-form-label"
				path="name">名称 / Name</form:label>
			<div class="am-u-sm-9">
				<form:input type="text" placeholder="名称 / Name" id="name"
					path="name" />
			</div>
		</div>
		<div class="am-form-group">
			<div class="am-u-sm-9 am-u-sm-push-3">
				<shiro:hasPermission name="organization:update">
					<form:button id="updateBtn" class="am-btn am-btn-primary">修改</form:button>
				</shiro:hasPermission>

				<shiro:hasPermission name="organization:delete">
					<c:if test="${not organization.rootNode}">
						<form:button id="deleteBtn" class="am-btn am-btn-primary">删除</form:button>
					</c:if>
				</shiro:hasPermission>

				<shiro:hasPermission name="organization:create">
					<form:button id="appendChildBtn" class="am-btn am-btn-primary">添加子节点</form:button>
				</shiro:hasPermission>

				<shiro:hasPermission name="organization:update">
					<c:if test="${not organization.rootNode}">
						<form:button id="moveBtn" class="am-btn am-btn-primary">移动节点</form:button>
					</c:if>
				</shiro:hasPermission>
			</div>
		</div>
	</form:form>
	<!--[if lt IE 9]>
<script src="http://libs.baidu.com/jquery/1.11.1/jquery.min.js"></script>
<script src="http://cdn.staticfile.org/modernizr/2.8.3/modernizr.js"></script>
<script src="/assets/js/amazeui.ie8polyfill.min.js"></script>
<![endif]-->

	<!--[if (gte IE 9)|!(IE)]><!-->
	<script src="/assets/js/jquery.min.js"></script>
	<!--<![endif]-->
	<script src="/assets/js/amazeui.min.js"></script>
	<script src="/assets/js/app.js"></script>
	<script>
        $(function() {
            $("#updateBtn").click(function() {
                $("#form")
                        .attr("action", "${pageContext.request.contextPath}/backend/organization/${organization.id}/update")
                        .submit();
                return false;
            });
            $("#deleteBtn").click(function() {
                if(confirm("确认删除吗？")) {
                    $("#form")
                            .attr("action", "${pageContext.request.contextPath}/backend/organization/${organization.id}/delete")
                            .submit();
                }
                return false;
            });

            $("#appendChildBtn").click(function() {
                location.href="${pageContext.request.contextPath}/backend/organization/${organization.id}/appendChild";
                return false;
            });

            $("#moveBtn").click(function() {
                location.href="${pageContext.request.contextPath}/backend/organization/${organization.id}/move";
                return false;
            });
        });
    </script>
</body>
</html>