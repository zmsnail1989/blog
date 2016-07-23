<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
<link rel="alternate" media="only screen and(max-width: 640px)">
<style>
@media only screen and (min-width: 1200px) {
	.blog-g-fixed {
		max-width: 1200px;
	}
}

@media only screen and (min-width: 641px) {
	.blog-sidebar {
		font-size: 1.4rem;
	}
}

.blog-main {
	padding: 20px 0;
}

.blog-title {
	margin: 10px 0 20px 0;
}

.blog-meta {
	font-size: 14px;
	margin: 10px 0 20px 0;
	color: #222;
}

.blog-meta a {
	color: #27ae60;
}

.blog-pagination a {
	font-size: 1.4rem;
}

.blog-team li {
	padding: 4px;
}

.blog-team img {
	margin-bottom: 0;
}

.blog-content img, .blog-team img {
	max-width: 100%;
	height: auto;
}

.blog-footer {
	padding: 10px 0;
	text-align: center;
}
</style>
</head>
<body>
	<header class="am-topbar">
		<h1 class="am-topbar-brand">
			<a href="#">Snail</a>
		</h1>

		<button class="am-topbar-btn am-topbar-toggle am-btn am-btn-sm am-btn-success am-show-sm-only"
			data-am-collapse="{target: '#doc-topbar-collapse'}">
			<span class="am-sr-only">导航切换</span> <span class="am-icon-bars"></span>
		</button>

		<div class="am-collapse am-topbar-collapse" id="doc-topbar-collapse">
			<ul class="am-nav am-nav-pills am-topbar-nav">
				<li class="am-active"><a href="#">首页</a></li>
				<li><a href="#">项目</a></li>
				<li class="am-dropdown" data-am-dropdown><a class="am-dropdown-toggle" data-am-dropdown-toggle
					href="javascript:;"> 菜单 <span class="am-icon-caret-down"></span>
				</a>
					<ul class="am-dropdown-content">
						<li class="am-dropdown-header">标题</li>
						<li><a href="#">关于我们</a></li>
						<li><a href="#">关于字体</a></li>
						<li><a href="#">TIPS</a></li>
					</ul></li>
				<li><a href="/backend/home">管理控制台</a></li>
			</ul>

			<form class="am-topbar-form am-topbar-left am-form-inline am-topbar-right" role="search">
				<div class="am-form-group">
					<input type="text" class="am-form-field am-input-sm" placeholder="搜索文章">
				</div>
				<button type="submit" class="am-btn am-btn-default am-btn-sm">搜索</button>
			</form>

		</div>
	</header>

	<div class="am-g am-g-fixed blog-g-fixed">
		<div class="am-u-md-8">
			<article class="blog-main">
				<h3 class="am-article-title blog-title">
					<a href="#">个人博客</a>
				</h3>
				<h4 class="am-article-meta blog-meta">
					by <a href="">open</a> posted on 2016/04/01
				</h4>

				<div class="am-g blog-content">
					<div class="am-u-lg-7">
						<p>为学日益，为道日损，损之又损，以至于无为，无为而无不为。</p>
						<p>愚人节这天，我的博客终于算是开张了</p>
						<p>博客是自己从0做起，涵盖了内部管理系统，初衷就是想让自己动起手来，技术不是说说而已。</p>
						<p>编程是一件很有意思的事情，很多人都这么认为。但是就如同你问一个没吃过鱼的人，鱼肉的问道怎么样，其实对方根本没办法回答你。所以如果你不会编程，那即使我将编程形容得如何天花乱坠也无济于事。如果将编程看成一种任务，甚至是负担，那此中的快乐就可能在你的每个厌倦的眼神中悄悄溜掉。这一针一线、一雕一刻、一笔一墨都是作者思想的凝聚。一个程序设计高手的乐趣就汇聚在那一行一行的代码里。</p>
					</div>
					<div class="am-u-lg-5">
						<p>
							<img src="http://f.cl.ly/items/451O3X0g47320D203D1B/不夠活潑.jpg">
						</p>
					</div>
				</div>
			</article>

			<hr class="am-article-divider blog-hr">

			<article class="blog-main">
				<h3 class="am-article-title">
					<a href="#">身邊的字體</a>
				</h3>
				<h4 class="am-article-meta blog-meta">
					by <a href="">ben</a> posted on 2016/03/17 under <a href="#">javascript</a>
				</h4>

				<div class="am-g blog-content">
					<div class="am-u-lg-7">
						<p>
							<!--本demo文字来自 http://blog.justfont.com/-->
							这次要介绍的是大家似乎都狠熟悉却又狠陌生的字体：Arial。不只是对 Typography 特别有兴趣的人、碰过排版的人，就算毫无接触，只要打开过电脑的字型选单，应该都有看过这个字型吧。尤其它还是以 A
							开头，总是会出现在选单最前面。
						</p>

						<p>Arial 常常跟 Helvetica 搞混，也常被当作是没有 Helvetica 时的替代字体使用。事实上 Arial 确实就是故意做得跟 Helvetica 狠相似，连每个字母的宽度都刻意做得一模一样。</p>
					</div>
					<div class="am-u-lg-5">
						<p>
							<img src="https://farm3.staticflickr.com/2917/14186214720_5d0b8ca2e3_b.jpg">
						</p>
					</div>
				</div>
				<div class="am-g">
					<div class="am-u-sm-12">
						<p>在欧美的排版业界中，使用 Arial 的作品意即是「不使用 Helvetica 的作品」，会被认為是设计师对字体的使用没有概念或是太容易妥协，基本上我大致也是同意。</p>

						<p>因為 Helvetica 只有 Mac 上才有內建，Windows 用戶除非花錢買，不然是沒有 Helvetica 能用，所以使用 Arial 的設計師往往被看成是不願意對 Typography
							花錢，專業素養不到家的人。除了在確保網頁相容性等絕對必需的情況外，幾乎可以說是不應該使用的字體。</p>

						<p>但是，在此之前，我們對 Arial 又有多少認識呢？</p>
					</div>
				</div>
			</article>

			<hr class="am-article-divider blog-hr">
			<ul class="am-pagination blog-pagination">
				<li class="am-pagination-prev"><a href="">&laquo; 上一页</a></li>
				<li class="am-pagination-next"><a href="">下一页 &raquo;</a></li>
			</ul>
		</div>

		<div class="am-u-md-4 blog-sidebar">
			<div class="am-panel-group">
				<section class="am-panel am-panel-default">
					<div class="am-panel-hd">关于我</div>
					<div class="am-panel-bd">
						<p>Snail,89年生。爱好广，技术宅</p>
						<a class="am-btn am-btn-success am-btn-sm" href="#">查看更多 →</a>
					</div>
				</section>
				<section class="am-panel am-panel-default">
					<div class="am-panel-hd">文章目录</div>
					<ul class="am-list blog-list">
						<li><a href="#">个人博客</a></li>
					</ul>
				</section>

				<section class="am-panel am-panel-default">
					<div class="am-panel-hd">团队成员</div>
					<div class="am-panel-bd">
						<ul class="am-avg-sm-4 blog-team">
							<li><img class="am-thumbnail" src="" alt="snail" /></li>
						</ul>
					</div>
				</section>
			</div>
		</div>

	</div>

	<footer class="blog-footer">
		<p>
			个人博客<br /> <small>© Copyright snail. by the AmazeUI Team.</small>
		</p>
	</footer>
	<!--[if lt IE 9]>
		<script src="http://libs.baidu.com/jquery/1.11.1/jquery.min.js"></script>
		<script src="http://cdn.staticfile.org/modernizr/2.8.3/modernizr.js"></script>
		<script src="/js/amazeui.ie8polyfill.min.js"></script>
	<![endif]-->

	<!--[if (gte IE 9)|!(IE)]><!-->
	<script src="/assets/js/jquery.min.js"></script>
	<!--<![endif]-->
	<script src="http://cdn.amazeui.org/amazeui/2.7.0/js/amazeui.min.js"></script>
	<script src="/assets/lib/device/device.min.js"></script>
	<script type="text/javascript" src="/assets/lib/layer/layer.js"></script>
	<script type="text/javascript">
	var devicejs = device.noConflict();
	if(devicejs.mobile()){
		layer.confirm('是否要跳转到移动版?', {
			  btn: ['确定','取消'] //按钮
			}, function(){
				 window.location.href = "/m";  
			}, function(){});
		
	}
	</script>
</body>
</html>