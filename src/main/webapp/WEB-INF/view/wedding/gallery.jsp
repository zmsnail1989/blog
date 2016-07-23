<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!doctype html>
<html class="no-js">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>SNAIL的个人博客</title>
<meta name="description" content="">
<meta name="keywords" content="">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="renderer" content="webkit">
<meta http-equiv="Cache-Control" content="no-siteapp" />
<link rel="icon" type="image/png" href="/assets/i/favicon.png">
<link rel="stylesheet" href="/assets/css/amazeui.min.css">
<link rel="stylesheet" href="/assets/css/app.css">
<!-- <script type="text/javascript" src="http://api.map.baidu.com/api?type=quick&ak=uYflEIpsIQyWGmlG5oWgX6EO&v=1.0"></script> -->
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=uYflEIpsIQyWGmlG5oWgX6EO"></script>
<style type="text/css">
#allmap {
	width: 100%;
	height: 250px;
}
</style>
</head>
<body>
	<!--[if lte IE 9]>
<p class="browsehappy">你正在使用<strong>过时</strong>的浏览器，Amaze UI 不提供支持。 请 <a
  href="http://browsehappy.com/" target="_blank">升级浏览器</a>
  以获得更好的体验！</p>
<![endif]-->

	<script type="text/x-handlebars-template" id="amz-tpl-1">
  {{>slider slider}}
</script>
	<div id="allmap" class="gallery-map"></div>
	<script type="text/x-handlebars-template" id="amz-tpl-2">
  {{>gallery gallery}}
</script>
	<script src="/assets/js/jquery.min.js"></script>
	<script src="/assets/js/amazeui.js"></script>
	<script src="/assets/js/handlebars.min.js"></script>
	<script src="/assets/js/venus_wedding.js"></script>
	<script>
		var $tpl1 = $('#amz-tpl-1');
		var source1 = $tpl1.text();
		var template1 = Handlebars.compile(source1);
		var $tpl2 = $('#amz-tpl-2');
		var source2 = $tpl2.text();
		var template2 = Handlebars.compile(source2);

		var slider = [ "http://blog-10046991.cos.myqcloud.com/image/007.jpg",
				"http://blog-10046991.cos.myqcloud.com/image/008.jpg",
				"http://blog-10046991.cos.myqcloud.com/image/010.jpg",
				"http://blog-10046991.cos.myqcloud.com/image/013.jpg",
				"http://blog-10046991.cos.myqcloud.com/image/016.jpg",
				"http://blog-10046991.cos.myqcloud.com/image/018.jpg",
				"http://blog-10046991.cos.myqcloud.com/image/020.jpg",
				"http://blog-10046991.cos.myqcloud.com/image/023.jpg",
				"http://blog-10046991.cos.myqcloud.com/image/024.jpg",
				"http://blog-10046991.cos.myqcloud.com/image/026.jpg",
				"http://blog-10046991.cos.myqcloud.com/image/028.jpg",
				"http://blog-10046991.cos.myqcloud.com/image/029.jpg",
				"http://blog-10046991.cos.myqcloud.com/image/037.jpg",
				"http://blog-10046991.cos.myqcloud.com/image/038.jpg",
				"http://blog-10046991.cos.myqcloud.com/image/039.jpg",
				"http://blog-10046991.cos.myqcloud.com/image/043.jpg",
				"http://blog-10046991.cos.myqcloud.com/image/044.jpg",
				"http://blog-10046991.cos.myqcloud.com/image/045.jpg",
				"http://blog-10046991.cos.myqcloud.com/image/046.jpg",
				"http://blog-10046991.cos.myqcloud.com/image/050.jpg",
				"http://blog-10046991.cos.myqcloud.com/image/058.jpg",
				"http://blog-10046991.cos.myqcloud.com/image/059.jpg",
				"http://blog-10046991.cos.myqcloud.com/image/063.jpg",
				"http://blog-10046991.cos.myqcloud.com/image/068.jpg",
				"http://blog-10046991.cos.myqcloud.com/image/069.jpg",
				"http://blog-10046991.cos.myqcloud.com/image/074.jpg",
				"http://blog-10046991.cos.myqcloud.com/image/075.jpg",
				"http://blog-10046991.cos.myqcloud.com/image/078.jpg",
				"http://blog-10046991.cos.myqcloud.com/image/079.jpg",
				"http://blog-10046991.cos.myqcloud.com/image/080.jpg",
				"http://blog-10046991.cos.myqcloud.com/image/081.jpg",
				"http://blog-10046991.cos.myqcloud.com/image/085.jpg",
				"http://blog-10046991.cos.myqcloud.com/image/086.jpg",
				"http://blog-10046991.cos.myqcloud.com/image/087.jpg",
				"http://blog-10046991.cos.myqcloud.com/image/089.jpg",
				"http://blog-10046991.cos.myqcloud.com/image/090.jpg",
				"http://blog-10046991.cos.myqcloud.com/image/094.jpg",
				"http://blog-10046991.cos.myqcloud.com/image/100.jpg",
				"http://blog-10046991.cos.myqcloud.com/image/101.jpg",
				"http://blog-10046991.cos.myqcloud.com/image/106.jpg",
				"http://blog-10046991.cos.myqcloud.com/image/110.jpg",
				"http://blog-10046991.cos.myqcloud.com/image/112.jpg",
				"http://blog-10046991.cos.myqcloud.com/image/113.jpg",
				"http://blog-10046991.cos.myqcloud.com/image/115.jpg",
				"http://blog-10046991.cos.myqcloud.com/image/117.jpg",
				"http://blog-10046991.cos.myqcloud.com/image/119.jpg",
				"http://blog-10046991.cos.myqcloud.com/image/121.jpg",
				"http://blog-10046991.cos.myqcloud.com/image/122.jpg",
				"http://blog-10046991.cos.myqcloud.com/image/123.jpg",
				"http://blog-10046991.cos.myqcloud.com/image/124.jpg",
				"http://blog-10046991.cos.myqcloud.com/image/127.jpg",
				"http://blog-10046991.cos.myqcloud.com/image/128.jpg",
				"http://blog-10046991.cos.myqcloud.com/image/130.jpg",
				"http://blog-10046991.cos.myqcloud.com/image/133.jpg",
				"http://blog-10046991.cos.myqcloud.com/image/135.jpg",
				"http://blog-10046991.cos.myqcloud.com/image/136.jpg",
				"http://blog-10046991.cos.myqcloud.com/image/139.jpg",
				"http://blog-10046991.cos.myqcloud.com/image/140.jpg",
				"http://blog-10046991.cos.myqcloud.com/image/141.jpg" ];
		//输出数组
		var out = [];
		//输出个数
		var num = 4;
		while (out.length < num) {
			var temp = (Math.random() * slider.length) >> 0;
			out.push({
				"img" : slider.splice(temp, 1)
			});
		}
		var data = {
			slider : {
				"theme" : "a2",
				//"sliderConfig": "{\"directionNav\":false}",
				"content" : out
			},
			gallery : {
				"options" : {
					"cols" : 2,
					"gallery" : true
				},
				"content" : [ {
					"img" : "http://s.amazeui.org/media/i/demos/bing-1.jpg",
					"link" : "http://s.amazeui.org/media/i/demos/bing-1.jpg",
					"title" : "远方 有一个地方 那里种有我们的梦想",
					"desc" : "2375-09-26"
				}, {
					"img" : "http://s.amazeui.org/media/i/demos/bing-2.jpg",
					"link" : "http://s.amazeui.org/media/i/demos/bing-2.jpg",
					"title" : "某天 也许会相遇 相遇在这个好地方",
					"desc" : "2375-09-26"
				}, {
					"img" : "http://s.amazeui.org/media/i/demos/bing-3.jpg",
					"link" : "http://s.amazeui.org/media/i/demos/bing-3.jpg",
					"title" : "不要太担心 只因为我相信",
					"desc" : "2375-09-26"
				}, {
					"img" : "http://s.amazeui.org/media/i/demos/bing-4.jpg",
					"link" : "http://s.amazeui.org/media/i/demos/bing-4.jpg",
					"title" : "终会走过这条遥远的道路",
					"desc" : "2375-09-26"
				} ]
			}
		};
		var html1 = template1(data);
		var html2 = template2(data);

		$tpl1.before(html1);
		$tpl2.before(html2);

		/*
		// 如果 Handlebars 渲染出来的 HTML 在 DOM ready 事件之后插入文档，需要手动初始化组件
		 $.each(['slider', 'menu', 'gallery', 'footer', 'navbar'], function(i, m) {
		   var module = $.AMUI[m];
		   module && module.init && module.init();
		 })
		 */

		// 百度地图API功能	
		map = new BMap.Map("allmap");
		map.centerAndZoom(new BMap.Point(108.946223,34.214037), 16);
		var data_info = [ [ 108.946236, 34.212639, "雁塔区朱雀大街19号南方星座D座(杨家村西口，北侧)" ] ];
		var opts = {
			width : 250, // 信息窗口宽度
			height : 80, // 信息窗口高度
			title : "徐记海鲜(明德门店)", // 信息窗口标题
			enableMessage : true
		//设置允许信息窗发送短息
		};
		for (var i = 0; i < data_info.length; i++) {
			var marker = new BMap.Marker(new BMap.Point(data_info[i][0],
					data_info[i][1])); // 创建标注
			var content = data_info[i][2];
			map.addOverlay(marker); // 将标注添加到地图中
			map.disableDragging();

			map.addControl(new BMap.NavigationControl()); //添加默认缩放平移控件

			map.addControl(new BMap.NavigationControl({
				anchor : BMAP_ANCHOR_BOTTOM_RIGHT,
				type : BMAP_NAVIGATION_CONTROL_ZOOM
			})); //右下角，仅包含缩放按钮

			map.enableScrollWheelZoom(); //启用滚轮放大缩小，默认禁用

			map.enableContinuousZoom(); //启用地图惯性拖拽，默认禁用

			addClickHandler(content, marker);
		}
		function addClickHandler(content, marker) {
			marker.addEventListener("click", function(e) {
				openInfo(content, e)
			});
		}
		function openInfo(content, e) {
			var p = e.target;
			var point = new BMap.Point(p.getPosition().lng, p.getPosition().lat);
			var infoWindow = new BMap.InfoWindow(content, opts); // 创建信息窗口对象 
			map.openInfoWindow(infoWindow, point); //开启信息窗口
		}
	</script>
</body>
</html>