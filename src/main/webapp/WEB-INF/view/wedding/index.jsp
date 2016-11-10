﻿<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<meta name='viewport'
	content='width=device-width, initial-scale=1.0, maximum-scale=1.0' />
<link rel="stylesheet" href="/assets/css/swiper.min.css">
<link rel="stylesheet" href="/assets/css/animate.min.css">
<link rel="stylesheet" href="/assets/css/wedding-min.css">
<link href="/assets/lib/swipebox/swipebox-min.css" rel="stylesheet" />
</head>

<body>
	<span id="musicControl"> <a id="mc_play" class="stop"> <audio id="musicfx" loop="loop"
				autoplay="autoplay">
<!-- 				<source src="./src/audio/audio.mp3" type="audio/mpeg"> -->
			</audio>
	</a>
	</span>
	<div class="swiper-container">
		<div class="swiper-wrapper">
			<!-------------slide1----------------->
			<section class="swiper-slide">
				<div class="invitation-index">
					<img src="/assets/i/upload/logo5.png" 
						style="width: 250px; height: 96.167px;position:relative; left: 15%; top: 30px; z-index: 1;">
				</div>
			</section>
			<!---------------slide2-------------->
			<section class="swiper-slide">
				<div class="invitation-page2">
					<img src="/assets/i/upload/page2-center.png" class="ani resize"
						style="width: 235px; height: 96.167px; left: 40px; top: 333.5708px; z-index: 1;"
						swiper-animate-effect="zoomIn" swiper-animate-duration="0.5s"
						swiper-animate-delay="0.5s">
				</div>


			</section>
			<!----------------slide3-------------->
			<section class="swiper-slide">
					<div class="invitation-page3">
						
					</div>
<!-- 					<div style="width: 100%;height: 50%;float: left;"> -->
<!-- 				  		<div style="width: 50%;height: 100%;float: left;"> -->
<!-- 				  			<div style="width: 100%;height: 50%;"> -->
<!-- 				  				<img src="/assets/i/upload/page3-1.png"  -->
<!-- 									style="width: 100%; height: 100%;z-index: 1;" -->
<!-- 									swiper-animate-effect="zoomIn" swiper-animate-duration="0.5s" -->
<!-- 									swiper-animate-delay="0.5s"> -->
<!-- 				  			</div> -->
<!-- 				  			<div style="width: 100%;height: 50%;"> -->
<!-- 				  					<img src="/assets/i/upload/page3-2.png" -->
<!-- 									style="width: 100%; height: 100%;z-index: 1;" -->
<!-- 									swiper-animate-effect="zoomIn" swiper-animate-duration="0.5s" -->
<!-- 									swiper-animate-delay="0.5s"> -->
<!-- 				  			</div> -->
<!-- 				  		</div> -->
<!-- 				  		<div style="width: 50%;height: 100%;float: left;"> -->
<!-- 				  				<div style="width: 100%;height: 100%;"> -->
<!-- 				  					<img src="/assets/i/upload/page3-3.png" -->
<!-- 									style="width: 100%; height: 100%;z-index: 1;" -->
<!-- 									swiper-animate-effect="zoomIn" swiper-animate-duration="0.5s" -->
<!-- 									swiper-animate-delay="0.5s"> -->
<!-- 				  		</div> -->
<!-- 				  </div> -->
<!-- 				  <div style="width: 100%;height: 100%;background-color: red;clear: both;"> -->
				  
<!-- 				  </div> -->
			</section>
			<!----------------slide4-------------->
			<section class="swiper-slide">
					<div class="invitation-page4">
					
					</div>
			</section>
			<!----------------slide5-------------->
			<section class="swiper-slide">
				<div id="loading"></div>
				<div id="gallery"></div>
			</section>
			<!----------------slide6-------------->
			<section class="swiper-slide">

				<img src="/assets/i/upload/invitation.src" class="ani resize"
					style="width: 300px; height: 180px; top: 22.917px; z-index: 1;"
					swiper-animate-effect="fadeIn"> 
				<img
					src="/assets/i/upload/love-wed.png" class="ani resize"
					style="width: 182px; height: 52px; left: 72px; top: 242.917px; z-index: 1;"
					swiper-animate-effect="tada" swiper-animate-duration="0.5s"
					swiper-animate-delay="0.5s"> 
				<img
					src="/assets/i/upload/invation-address.png" class="ani resize"
					style="width: 262px; height: 82px; left: 32px; top: 300.917px; z-index: 1;"
					swiper-animate-effect="swing" swiper-animate-duration="1s"
					swiper-animate-delay="1s"> 
				<img
					src="/assets/i/upload/first-1.png" class="ani resize"
					style="width: 280px; height: 58px; left: 20px; top: 390.917px; z-index: 1;"
					swiper-animate-effect="bounceIn" swiper-animate-duration="1.5s"
					swiper-animate-delay="1.5s">
			</section>
			<!-------------slide7----------------->
			<section class="swiper-slide">
				<img src="/assets/i/upload/location.png" class="ani resize"
					style="width: 300px; height: 80px; top: 22.917px; z-index: 9999;"
					swiper-animate-effect="fadeIn" swiper-animate-duration="0.5s"
					swiper-animate-delay="0.5s">
				<div id="allmap" class="gallery-map"></div>
			</section>

<!-- 			<!-------------slide8-----------------> -->
<!-- 			<section class="swiper-slide"> -->
				
<!-- 			</section> -->
		</div>

		<img src="/assets/i/web-swipe-tip.png"
			style="width: 20px; height: 15px; top: 460px; left: 150px;"
			id="array" class="resize">
		<div class="swiper-pagination"></div>
	</div>
	<script type="text/javascript"
		src="http://api.map.baidu.com/api?v=2.0&ak=uYflEIpsIQyWGmlG5oWgX6EO"></script>
	<script src="/assets/js/jquery.min.js"></script>
	<script src="/assets/js/swiper.min.js"></script>
	<script src="/assets/js/swiper.animate.min.js"></script>
	<script src="/assets/js/wedding-min.js"></script>
	<script src="/assets/lib/swipebox/jquery.swipebox.min.js"></script>
	<script src="/assets/js/jquery.loadImage.js"></script>
</body>
</html>
