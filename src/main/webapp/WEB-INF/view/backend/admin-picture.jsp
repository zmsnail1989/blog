<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="https://git.oschina.net/cool_snail/blog.git"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
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
<link rel="apple-touch-icon-precomposed"
	href="/assets/i/app-icon72x72@2x.png">
<meta name="apple-mobile-web-app-title" content="Amaze UI" />
<link rel="stylesheet" href="/assets/css/amazeui.min.css" />
<link rel="stylesheet" href="/assets/css/admin.css">
<link rel="stylesheet"
	href="/assets/lib/datatables/amazeui.datatables.css" />
<body>
	<!--[if lte IE 9]>
<p class="browsehappy">你正在使用<strong>过时</strong>的浏览器，Amaze UI 暂不支持。 请 <a href="http://browsehappy.com/" target="_blank">升级浏览器</a>
  以获得更好的体验！</p>
<![endif]-->

	<header class="am-topbar am-topbar-inverse admin-header">
		<div class="am-topbar-brand">
			<strong>Snail</strong> <small>博客</small>
		</div>

		<button
			class="am-topbar-btn am-topbar-toggle am-btn am-btn-sm am-btn-success am-show-sm-only"
			data-am-collapse="{target: '#topbar-collapse'}">
			<span class="am-sr-only">导航切换</span> <span class="am-icon-bars"></span>
		</button>

		<div class="am-collapse am-topbar-collapse" id="topbar-collapse">

			<ul
				class="am-nav am-nav-pills am-topbar-nav am-topbar-right admin-header-list">
				<li><a href="javascript:;"><span class="am-icon-envelope-o"></span>
						收件箱 <span class="am-badge am-badge-warning">5</span></a></li>
				<li class="am-dropdown" data-am-dropdown><a
					class="am-dropdown-toggle" data-am-dropdown-toggle
					href="javascript:;"> <span class="am-icon-users"></span> 管理员 <span
						class="am-icon-caret-down"></span>
				</a>
					<ul class="am-dropdown-content">
						<li><a href="#"><span class="am-icon-user"></span> 资料</a></li>
						<li><a href="#"><span class="am-icon-cog"></span> 设置</a></li>
						<li><a href="#"><span class="am-icon-power-off"></span>
								退出</a></li>
					</ul></li>
				<li class="am-hide-sm-only"><a href="javascript:;"
					id="admin-fullscreen"><span class="am-icon-arrows-alt"></span>
						<span class="admin-fullText">开启全屏</span></a></li>
			</ul>
		</div>
	</header>

	<div class="am-cf admin-main">
		<!-- sidebar start -->
		<div class="admin-sidebar am-offcanvas" id="admin-offcanvas">
			<div class="am-offcanvas-bar admin-offcanvas-bar">
				<ul class="am-list admin-sidebar-list">
				</ul>
				<div class="am-panel am-panel-default admin-sidebar-panel">
					<div class="am-panel-bd">
						<p>
							<span class="am-icon-bookmark"></span> 公告
						</p>
						<p>时光静好，与君语；细水流年，与君同。</p>
					</div>
				</div>

				<div class="am-panel am-panel-default admin-sidebar-panel">
					<div class="am-panel-bd">
						<p>
							<span class="am-icon-tag">wiki</span>
						</p>
						<p>Welcome to the Snail Blog!</p>
					</div>
				</div>
			</div>
		</div>
		<!-- sidebar end -->

		<!-- content start -->
		<div class="admin-content">
			<div class="admin-content-body">
				<div class="am-cf am-padding am-padding-bottom-0">
					<div class="am-fl am-cf">
						<strong class="am-text-primary am-text-lg">系统管理</strong> / <small>图片管理</small>
					</div>
				</div>

				<hr>
				<div class="am-u-sm-12 am-u-md-6">
					<div class="am-btn-toolbar">
						<div class="am-btn-group am-btn-group-xs">
							<button type="button" class="am-btn am-btn-default">
								<span class="am-icon-plus"></span> 新增
							</button>
							<button type="button" class="am-btn am-btn-default"
								onclick="deleteAll()">
								<span class="am-icon-trash-o"></span> 删除
							</button>
						</div>
					</div>
				</div>

				<div class="am-g">
					<div class="am-u-sm-12">
						<table width="100%"
							class="am-table am-table-striped am-table-bordered am-table-compact am-text-nowrap"
							id="filelist">
							<thead>
								<tr>
									<th><input type='checkbox' onclick="checkAll(this);"></th>
									<th>资源名称</th>
									<th>资源类型</th>
									<th>URL</th>
									<th>上传时间</th>
									<th>上传用户</th>
									<th>操作</th>
								</tr>
							</thead>
							<tfoot>
								<tr>
									<th></th>
									<th>资源名称</th>
									<th>资源类型</th>
									<th>URL</th>
									<th>上传时间</th>
									<th>上传用户</th>
									<th>操作</th>
								</tr>
							</tfoot>
							<tbody></tbody>
						</table>
					</div>

				</div>
			</div>

			<footer class="admin-content-footer">
				<hr>
				<p class="am-padding-left">© 2014 AllMobilize, Inc. Licensed
					under MIT license.</p>
			</footer>

		</div>
		<!-- content end -->
	</div>

	<a href="#"
		class="am-icon-btn am-icon-th-list am-show-sm-only admin-menu"
		data-am-offcanvas="{target: '#admin-offcanvas'}"></a>

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
	<script src="/assets/lib/datatables/amazeui.datatables.min.js"></script>
	<script src="/assets/lib/datatables/dataTables.responsive.min.js"></script>
	<script type="text/javascript" src="/assets/lib/layer/layer.js"></script>
</body>
<script type="text/javascript">
	var table;
	$(function() {
		table = $('#filelist').DataTable(
						{
							ajax : {
								url : "/backend/picmanage/getAll"
							},


							"aoColumnDefs" : [ {
								"bSortable" : false,
								"aTargets" : [ 0, 6 ]
							} ], // 去掉checkbox排序功能
							"bAutoWidth" : true,
							"ordering" : true, //排序功能
							"sScrollX" : "100%",
							"sScrollXInner" : "110%",
							"bScrollCollapse" : true,
							"columns" : [
									{
										data : "id",
										render : function(data, type, full) {
											return "<input type='checkbox' name='checkList' value='"+data+"'>";
										}
									},
									{
										"data" : "name"
									},
									{
										"data" : "suffix"
									},
									{
										data : "url",
										render : function(data, type, full) {
											var html = "<a target=\"_blank\" href=\""+data+"\">"
													+ data + "</>";
											return html;
										}
									},
									{
										"data" : "update"
									},
									{
										"data" : "up_user"
									},
									{
										data : "id",
										render : function(data, type, full) {
											var html = "<button class=\"am-btn am-btn-danger am-btn-xs\" onclick=\"removeit("
													+ data
													+ ")\"><i class=\"am-icon-trash-o\" ></i> 删除</button>";
											return html;
										}
									} ]
						});
		$(".am-icon-plus").parent().click(function() {
			window.location.href = "/backend/picmanage/upload";
		});
	});

	function removeit(id) {
		layer.confirm('确定删除这条记录吗?', {
			btn : [ '确定', '取消' ]
		//按钮
		}, function() {
			$.ajax({
				url : "/backend/picmanage/delete",
				data : {
					"ids" : id
				},
				dataType : "json",
				success : function(data) {
					if (data === "ok") {
						table.ajax.reload();
						layer.msg('删除成功！', {
							icon : 1
						});
					}
				}
			});
		}, function() {
		});
	}
	function deleteAll() {
		var ids = "";
		$("input[name='checkList']").each(function(i) {
			var objs = $(this);
			if (objs.is(':checked')) {
				ids += objs.val() + ",";
			}
		});
		if (ids == "") {
			layer.msg('请选择一条记录！', {
				icon : 2
			});
			return;
		}
		layer.confirm('确定全部删除已选记录吗?', {
			btn : [ '确定', '取消' ]
		//按钮
		}, function() {
			$.ajax({
				url : "/backend/picmanage/delete",
				data : {
					"ids" : ids
				},
				dataType : "json",
				success : function(data) {
					if (data === "ok") {
						table.ajax.reload();
						layer.msg('删除成功！', {
							icon : 1
						});
					}
				}
			});
		}, function() {
		});
	}
	function checkAll(obj) {
		if ($(obj).is(':checked')) {
			$("input[name='checkList']").each(function(i) {
				var objs = $(this);
				objs.prop("checked", true);
			});
		} else {
			$("input[name='checkList']").each(function(i) {
				var objs = $(this);
				objs.prop("checked", false);
			});
		}
	}
</script>
</html>