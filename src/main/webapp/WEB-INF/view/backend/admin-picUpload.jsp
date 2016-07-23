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
<link rel="stylesheet" type="text/css" href="/assets/lib/webuploader/webuploader.css">
<link rel="stylesheet" type="text/css" href="/assets/lib/webuploader/base.css">
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
						<strong class="am-text-primary am-text-lg">相册管理</strong> / <small>图片上传</small>
					</div>
				</div>

				<hr />

				<div class="am-u-sm-12">
					<div class="page-container">
						<p>您可以尝试文件拖拽，使用QQ截屏工具，然后激活窗口后粘贴，或者点击添加图片按钮，来上传图片.</p>

						<div id="uploader" class="wu-example">
							<div class="queueList">
								<div id="dndArea" class="placeholder">
									<div id="filePicker"></div>
									<p>或将照片拖到这里，单次最多可选300张</p>
								</div>
							</div>
							<div class="statusBar" style="display: none;">
								<div class="progress">
									<span class="text">0%</span> <span class="percentage"></span>
								</div>
								<div class="info"></div>
								<div class="btns">
									<div id="filePicker2"></div>
									<div class="uploadBtn">开始上传</div>
								</div>
							</div>
						</div>

					</div>
				</div>
			</div>
		</div>

		<footer class="admin-content-footer">
			<hr>
			<p class="am-padding-left">© 2014 AllMobilize, Inc. Licensed under MIT license.</p>
		</footer>

	</div>
	<a href="#" class="am-icon-btn am-icon-th-list am-show-sm-only admin-menu"
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
	<script type="text/javascript" src="/assets/lib/webuploader/webuploader.js"></script>
	<script type="text/javascript" src="/assets/lib/layer/layer.js"></script>
</body>
<script type="text/javascript">
	jQuery(function() {
		var $ = jQuery, // just in case. Make sure it's not an other libaray.

		$wrap = $('#uploader'),

		// 图片容器
		$queue = $('<ul class="filelist"></ul>').appendTo($wrap.find('.queueList')),

		// 状态栏，包括进度和控制按钮
		$statusBar = $wrap.find('.statusBar'),

		// 文件总体选择信息。
		$info = $statusBar.find('.info'),

		// 上传按钮
		$upload = $wrap.find('.uploadBtn'),

		// 没选择文件之前的内容。
		$placeHolder = $wrap.find('.placeholder'),

		// 总体进度条
		$progress = $statusBar.find('.progress').hide(),

		// 添加的文件数量
		fileCount = 0,

		// 添加的文件总大小
		fileSize = 0,

		// 优化retina, 在retina下这个值是2
		ratio = window.devicePixelRatio || 1,

		// 缩略图大小
		thumbnailWidth = 110 * ratio, thumbnailHeight = 110 * ratio,

		// 可能有pedding, ready, uploading, confirm, done.
		state = 'pedding',

		// 所有文件的进度信息，key为file id
		percentages = {},

		supportTransition = (function() {
			var s = document.createElement('p').style, r = 'transition' in s
					|| 'WebkitTransition' in s || 'MozTransition' in s
					|| 'msTransition' in s || 'OTransition' in s;
			s = null;
			return r;
		})(),

		// WebUploader实例
		uploader;

		if (!WebUploader.Uploader.support()) {
			alert('Web Uploader 不支持您的浏览器！如果你使用的是IE浏览器，请尝试升级 flash 播放器');
			throw new Error('WebUploader does not support the browser you are using.');
		}

		// 实例化
		uploader = WebUploader.create({
			pick : {
				id : '#filePicker',
				label : '点击选择图片'
			},
			dnd : '#uploader .queueList',
			paste : document.body,

			accept : {
				title : 'Images',
				extensions : 'gif,jpg,jpeg,bmp,png',
				mimeTypes : 'image/*'
			},

			// swf文件路径
			swf : '/assets/lib/webuploader/Uploader.swf',

			disableGlobalDnd : true,

			chunked : true,
			// server: 'http://webuploader.duapp.com/server/fileupload.php',
			server : '/backend/picmanage/uploadFile',
			fileNumLimit : 300,
			fileSizeLimit : 300 * 1024 * 1024, // 200 M
			fileSingleSizeLimit : 300 * 1024 * 1024
		// 50 M
		});

		// 添加“添加文件”的按钮，
		uploader.addButton({
			id : '#filePicker2',
			label : '继续添加'
		});

		// 当有文件添加进来时执行，负责view的创建
		function addFile(file) {
			var $li = $('<li id="' + file.id + '">' + '<p class="title">'
					+ file.name + '</p>' + '<p class="imgWrap"></p>'
					+ '<p class="progress"><span></span></p>' + '</li>'),

			$btns = $(
					'<div class="file-panel">'
							+ '<span class="cancel">删除</span>'
							+ '<span class="rotateRight">向右旋转</span>'
							+ '<span class="rotateLeft">向左旋转</span></div>')
					.appendTo($li), $prgress = $li.find('p.progress span'), $wrap = $li
					.find('p.imgWrap'), $info = $('<p class="error"></p>'),

			showError = function(code) {
				switch (code) {
				case 'exceed_size':
					text = '文件大小超出';
					break;

				case 'interrupt':
					text = '上传暂停';
					break;

				default:
					text = '上传失败，请重试';
					break;
				}

				$info.text(text).appendTo($li);
			};

			if (file.getStatus() === 'invalid') {
				showError(file.statusText);
			} else {
				// @todo lazyload
				$wrap.text('预览中');
				uploader.makeThumb(file, function(error, src) {
					if (error) {
						$wrap.text('不能预览');
						return;
					}

					var img = $('<img src="'+src+'">');
					$wrap.empty().append(img);
				}, thumbnailWidth, thumbnailHeight);

				percentages[file.id] = [ file.size, 0 ];
				file.rotation = 0;
			}

			file.on('statuschange', function(cur, prev) {
				if (prev === 'progress') {
					$prgress.hide().width(0);
				} else if (prev === 'queued') {
					$li.off('mouseenter mouseleave');
					$btns.remove();
				}

				// 成功
				if (cur === 'error' || cur === 'invalid') {
					console.log(file.statusText);
					showError(file.statusText);
					percentages[file.id][1] = 1;
				} else if (cur === 'interrupt') {
					showError('interrupt');
				} else if (cur === 'queued') {
					percentages[file.id][1] = 0;
				} else if (cur === 'progress') {
					$info.remove();
					$prgress.css('display', 'block');
				} else if (cur === 'complete') {
					$li.append('<span class="success"></span>');
				}

				$li.removeClass('state-' + prev).addClass('state-' + cur);
			});

			$li.on('mouseenter', function() {
				$btns.stop().animate({
					height : 30
				});
			});

			$li.on('mouseleave', function() {
				$btns.stop().animate({
					height : 0
				});
			});

			$btns.on('click', 'span', function() {
				var index = $(this).index(), deg;

				switch (index) {
				case 0:
					uploader.removeFile(file);
					return;

				case 1:
					file.rotation += 90;
					break;

				case 2:
					file.rotation -= 90;
					break;
				}

				if (supportTransition) {
					deg = 'rotate(' + file.rotation + 'deg)';
					$wrap.css({
						'-webkit-transform' : deg,
						'-mos-transform' : deg,
						'-o-transform' : deg,
						'transform' : deg
					});
				} else {
					$wrap.css('filter',
							'progid:DXImageTransform.Microsoft.BasicImage(rotation='
									+ (~~((file.rotation / 90) % 4 + 4) % 4)
									+ ')');
					// use jquery animate to rotation
					// $({
					//     rotation: rotation
					// }).animate({
					//     rotation: file.rotation
					// }, {
					//     easing: 'linear',
					//     step: function( now ) {
					//         now = now * Math.PI / 180;

					//         var cos = Math.cos( now ),
					//             sin = Math.sin( now );

					//         $wrap.css( 'filter', "progid:DXImageTransform.Microsoft.Matrix(M11=" + cos + ",M12=" + (-sin) + ",M21=" + sin + ",M22=" + cos + ",SizingMethod='auto expand')");
					//     }
					// });
				}

			});

			$li.appendTo($queue);
		}

		// 负责view的销毁
		function removeFile(file) {
			var $li = $('#' + file.id);

			delete percentages[file.id];
			updateTotalProgress();
			$li.off().find('.file-panel').off().end().remove();
		}

		function updateTotalProgress() {
			var loaded = 0, total = 0, spans = $progress.children(), percent;

			$.each(percentages, function(k, v) {
				total += v[0];
				loaded += v[0] * v[1];
			});

			percent = total ? loaded / total : 0;

			spans.eq(0).text(Math.round(percent * 100) + '%');
			spans.eq(1).css('width', Math.round(percent * 100) + '%');
			updateStatus();
		}

		function updateStatus() {
			var text = '', stats;

			if (state === 'ready') {
				text = '选中' + fileCount + '张图片，共'
						+ WebUploader.formatSize(fileSize) + '。';
			} else if (state === 'confirm') {
				stats = uploader.getStats();
				if (stats.uploadFailNum) {
					text = '已成功上传'
							+ stats.successNum
							+ '张照片至XX相册，'
							+ stats.uploadFailNum
							+ '张照片上传失败，<a class="retry" href="#">重新上传</a>失败图片或<a class="ignore" href="#">忽略</a>'
				}

			} else {
				stats = uploader.getStats();
				text = '共' + fileCount + '张（'
						+ WebUploader.formatSize(fileSize) + '），已上传'
						+ stats.successNum + '张';

				if (stats.uploadFailNum) {
					text += '，失败' + stats.uploadFailNum + '张';
				}
				text+="    <button type=\"button\" class=\"am-btn am-btn-success\" onclick=\"javascript:location.reload()\"><span class=\"am-icon-arrow-circle-left\"></span> 继续上传</button>";
			}

			$info.html(text);
		}

		function setState(val) {
			var file, stats;

			if (val === state) {
				return;
			}

			$upload.removeClass('state-' + state);
			$upload.addClass('state-' + val);
			state = val;

			switch (state) {
			case 'pedding':
				$placeHolder.removeClass('element-invisible');
				$queue.parent().removeClass('filled');
				$queue.hide();
				$statusBar.addClass('element-invisible');
				uploader.refresh();
				break;

			case 'ready':
				$placeHolder.addClass('element-invisible');
				$('#filePicker2').removeClass('element-invisible');
				$queue.parent().addClass('filled');
				$queue.show();
				$statusBar.removeClass('element-invisible');
				uploader.refresh();
				break;

			case 'uploading':
				$('#filePicker2').addClass('element-invisible');
				$progress.show();
				$upload.text('暂停上传');
				break;

			case 'paused':
				$progress.show();
				$upload.text('继续上传');
				break;

			case 'confirm':
				$progress.hide();
				$upload.text('开始上传').addClass('disabled');

				stats = uploader.getStats();
				if (stats.successNum && !stats.uploadFailNum) {
					setState('finish');
					return;
				}
				break;
			case 'finish':
				stats = uploader.getStats();
				if (stats.successNum) {
					layer.alert('上传成功！', {icon: 6});
				} else {
					// 没有成功的图片，重设
					state = 'done';
					location.reload();
				}
				break;
			}

			updateStatus();
		}

		uploader.onUploadProgress = function(file, percentage) {
			var $li = $('#' + file.id), $percent = $li.find('.progress span');

			$percent.css('width', percentage * 100 + '%');
			percentages[file.id][1] = percentage;
			updateTotalProgress();
		};

		uploader.onFileQueued = function(file) {
			fileCount++;
			fileSize += file.size;

			if (fileCount === 1) {
				$placeHolder.addClass('element-invisible');
				$statusBar.show();
			}

			addFile(file);
			setState('ready');
			updateTotalProgress();
		};

		uploader.onFileDequeued = function(file) {
			fileCount--;
			fileSize -= file.size;

			if (!fileCount) {
				setState('pedding');
			}

			removeFile(file);
			updateTotalProgress();

		};

		uploader.on('all', function(type) {
			var stats;
			switch (type) {
			case 'uploadFinished':
				setState('confirm');
				break;

			case 'startUpload':
				setState('uploading');
				break;

			case 'stopUpload':
				setState('paused');
				break;

			}
		});

		uploader.onError = function(code) {
			alert('Eroor: ' + code);
		};

		$upload.on('click', function() {
			if ($(this).hasClass('disabled')) {
				return false;
			}

			if (state === 'ready') {
				uploader.upload();
			} else if (state === 'paused') {
				uploader.upload();
			} else if (state === 'uploading') {
				uploader.stop();
			}
		});

		$info.on('click', '.retry', function() {
			uploader.retry();
		});

		$info.on('click', '.ignore', function() {
			alert('todo');
		});

		$upload.addClass('state-' + state);
		updateTotalProgress();
	});
</script>
</html>