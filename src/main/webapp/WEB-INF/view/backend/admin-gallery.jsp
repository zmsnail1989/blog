<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!doctype html>
<html class="no-js">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>SNAIL的个人博客</title>
<meta name="description" content="这是一个 gallery 页面">
<meta name="keywords" content="gallery">
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
	<!--[if lte IE 9]>
<p class="browsehappy">你正在使用<strong>过时</strong>的浏览器，Amaze UI 暂不支持。 请 <a href="http://browsehappy.com/" target="_blank">升级浏览器</a>
  以获得更好的体验！</p>
<![endif]-->

	<header class="am-topbar am-topbar-inverse admin-header">
		<div class="am-topbar-brand">
			<strong>Snail</strong> <small>博客</small>
		</div>

		<button class="am-topbar-btn am-topbar-toggle am-btn am-btn-sm am-btn-success am-show-sm-only"
			data-am-collapse="{target: '#topbar-collapse'}">
			<span class="am-sr-only">导航切换</span> <span class="am-icon-bars"></span>
		</button>

		<div class="am-collapse am-topbar-collapse" id="topbar-collapse">

			<ul class="am-nav am-nav-pills am-topbar-nav am-topbar-right admin-header-list">
				<li><a href="javascript:;"><span class="am-icon-envelope-o"></span> 收件箱 <span
						class="am-badge am-badge-warning">5</span></a></li>
				<li class="am-dropdown" data-am-dropdown><a class="am-dropdown-toggle" data-am-dropdown-toggle
					href="javascript:;"> <span class="am-icon-users"></span> 管理员 <span class="am-icon-caret-down"></span>
				</a>
					<ul class="am-dropdown-content">
						<li><a href="#"><span class="am-icon-user"></span> 资料</a></li>
						<li><a href="#"><span class="am-icon-cog"></span> 设置</a></li>
						<li><a href="#"><span class="am-icon-power-off"></span> 退出</a></li>
					</ul></li>
				<li class="am-hide-sm-only"><a href="javascript:;" id="admin-fullscreen"><span class="am-icon-arrows-alt"></span>
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
						<p>时光静好，与君语；细水流年，与君同。—— Amaze UI</p>
					</div>
				</div>

				<div class="am-panel am-panel-default admin-sidebar-panel">
					<div class="am-panel-bd">
						<p>
							<span class="am-icon-tag"></span> wiki
						</p>
						<p>Welcome to the Amaze UI wiki!</p>
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
						<strong class="am-text-primary am-text-lg">相册</strong> / <small>Gallery</small>
					</div>
				</div>

				<hr>
				<ul id="gallery-list" class="am-avg-sm-2 am-avg-md-4 am-avg-lg-6 am-margin gallery-list">
				</ul>

				<div class="am-margin am-cf">
					<hr />
					<div id="gallery_page"></div>
				</div>
			</div>

			<footer class="admin-content-footer">
				<hr>
				<p class="am-padding-left">© 2014 AllMobilize, Inc. Licensed under MIT license.</p>
			</footer>

		</div>
		<!-- content end -->

	</div>

	<a href="#" class="am-icon-btn am-icon-th-list am-show-sm-only admin-menu"
		data-am-offcanvas="{target: '#admin-offcanvas'}"></a>

	<footer>
		<hr>
		<p class="am-padding-left">© 2014 AllMobilize, Inc. Licensed under MIT license.</p>
	</footer>

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
	<script src="/assets/lib/laypage/laypage.js"></script>
	<script src="/assets/lib/layer/layer.js"></script>
	<script type="text/javascript">
	//以下将以jquery.ajax为例，演示一个异步分页
			function query(curr){
			    $.getJSON('/backend/gallery/view', {
			    	pagesize:12,
			        page: curr || 1 //向服务端传的参数，此处只是演示
			    }, function(data){
			    	var res=data.records;
			    	var total=data.total;
			    	if(res){
				        //此处仅仅是为了演示变化的内容
				        var temp_html="";
				        $(".gallery-list").html("");
				        for(var i=0;i<res.length;i++){
				        	temp_html+="<li  onclick=\"detail('"+res[i].id+"','"+res[i].name+"','"+res[i].url+"')\"><a href=\"#\"> <img class=\"am-img-thumbnail am-img-bdrs\" src=\""+res[i].url+"\"/>";
				        	temp_html+="<div class=\"gallery-title\">"+res[i].name+"</div>";
				        	temp_html+="<div class=\"gallery-desc\">"+res[i].update+"</div>";
				        	temp_html+="</a></li>";
				        }
				        $(".gallery-list").html(temp_html);
				        var pages = Math.ceil(total/12);
				        //显示分页
				        laypage({
				            cont: 'gallery_page', //容器。值支持id名、原生dom对象，jquery对象。【如该容器为】：<div id="page1"></div>
				            pages: pages, //通过后台拿到的总页数
				            curr: curr || 1, //当前页
				            jump: function(obj, first){ //触发分页后的回调
				                if(!first){ //点击跳页触发函数自身，并传递当前页：obj.curr
				                    query(obj.curr);
				                }
				            }
				        });
			    	}
			    });
			};
			//运行
			query();
			function detail(id,name,url){
				var json={
						  "title": "个人相册", //相册标题
						  "id": 123, //相册id
						  "start": 0, //初始显示的图片序号，默认0
						  "data": [   //相册包含的图片，数组格式
						    {
						      "alt": name,
						      "pid": id, //图片id
						      "src": url, //原图地址
						      "thumb":url //缩略图地址
						    }
						  ]
						};
				layer.photos({
				    photos: json
				  });
			}
	</script>
</body>
</html>
