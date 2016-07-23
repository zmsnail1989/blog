﻿<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<!doctype html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>Wedding Invitation</title>
    <meta name='viewport' content='width=device-width, initial-scale=1.0, maximum-scale=1.0'/>
    <link rel="stylesheet" href="/assets/css/swiper.min.css">
    <link rel="stylesheet" href="/assets/css/animate.min.css">
    <link rel="stylesheet" href="/assets/css/wedding.css">
</head>

<body>
<span id="musicControl">
        <a id="mc_play" class="stop" onclick="play_music();">
            <audio id="musicfx" loop="loop" autoplay="autoplay">
                <source src="./src/audio/audio.mp3" type="audio/mpeg">
            </audio>
        </a>
 </span>
<div class="swiper-container">
    <div class="swiper-wrapper">
        <!-------------slide1----------------->
        <section class="swiper-slide">
            <div class="invitation-index">
                <img src="/assets/i/upload/logo5.png" class="ani resize"
                     style="width: 250px;height: 96.167px;left: 30px;top: 22.917px;z-index: 1;"
                     swiper-animate-effect="fadeIn"
                     swiper-animate-duration="0.5s" swiper-animate-delay="0.5s">
            </div>
        </section>
        <!---------------slide2-------------->
        <section class="swiper-slide">
            <%--<img src="/assets/i/upload/s2-i5.png" class="ani resize"--%>
            <%--style="width:263px;height:177px;left:31px;top:262px;z-index:5; border-radius:20px;"--%>
            <%--swiper-animate-effect="fadeInLeft" swiper-animate-duration="0.5s" swiper-animate-delay="2s">--%>
            <%--<img src="/assets/i/upload/s2-i4.png" class="ani resize"--%>
            <%--style="width:195px;height:163px;left:31px;top:48px;z-index:4;" swiper-animate-effect="fadeInRight"--%>
            <%--swiper-animate-duration="0.5s" swiper-animate-delay="1.3s">--%>
            <%--<img src="/assets/i/upload/s2-i3.png" class="ani resize"--%>
            <%--style="width:212px;height:186px;left:24px;top:36px;z-index:3;" swiper-animate-effect="fadeInLeft"--%>
            <%--swiper-animate-duration="0.5s" swiper-animate-delay="1s">--%>
            <%--<img src="/assets/i/upload/s2-i2.png" class="ani resize"--%>
            <%--style="width:64px;height:221px;left:237px;top:20px;z-index:2;" swiper-animate-effect="fadeInUp"--%>
            <%--swiper-animate-duration="0.5s" swiper-animate-delay="0.3s">--%>
            <%--<img src="/assets/i/upload/s2-i1.png" class="ani resize"--%>
            <%--style="width:42px;height:192px;left:248px;top:27px;z-index:1;" swiper-animate-effect="fadeInDown"--%>
            <%--swiper-animate-duration="0.5s" swiper-animate-delay="0s">--%>

        </section>
        <!----------------slide3-------------->
        <section class="swiper-slide">

            <%--<img src="/assets/i/upload/s3-i5.png" class="ani resize"--%>
            <%--style="width:263px;height:177px;left:31px;top:262px;z-index:5; border-radius:20px;"--%>
            <%--swiper-animate-effect="fadeInLeft" swiper-animate-duration="0.5s" swiper-animate-delay="2s">--%>
            <%--<img src="/assets/i/upload/s3-i4.png" class="ani resize"--%>
            <%--style="width:195px;height:163px;left:31px;top:48px;z-index:4;" swiper-animate-effect="fadeInRight"--%>
            <%--swiper-animate-duration="0.5s" swiper-animate-delay="1.3s">--%>
            <%--<img src="/assets/i/upload/s2-i3.png" class="ani resize"--%>
            <%--style="width:212px;height:186px;left:24px;top:36px;z-index:3;" swiper-animate-effect="fadeInLeft"--%>
            <%--swiper-animate-duration="0.5s" swiper-animate-delay="1s">--%>
            <%--<img src="/assets/i/upload/s3-i2.png" class="ani resize"--%>
            <%--style="width:64px;height:221px;left:237px;top:20px;z-index:2;" swiper-animate-effect="fadeInUp"--%>
            <%--swiper-animate-duration="0.5s" swiper-animate-delay="0.3s">--%>
            <%--<img src="/assets/i/upload/s2-i1.png" class="ani resize"--%>
            <%--style="width:42px;height:192px;left:248px;top:27px;z-index:1;" swiper-animate-effect="fadeInDown"--%>
            <%--swiper-animate-duration="0.5s" swiper-animate-delay="0s">--%>

        </section>
        <!-------------slide4----------------->
        <section class="swiper-slide">
            <div class="invitation-logo"></div>
            <%--<div class="ani resize" style="width:120px;height:110px;left:110px;top:184px;z-index:3;"--%>
            <%--swiper-animate-effect="fadeInLeft" swiper-animate-duration="0.5s" swiper-animate-delay="1s">--%>
            <%--<div class="txt">--%>
            <%--<p style="width:15px;color:#b60927; display:inline-block; font-size:90%; line-height:1; margin-right:10px;">--%>
            <%--愿您羊年运旺福旺</p>--%>
            <%--<p style="width:15px;color:#b60927; display:inline-block; font-size:90%; line-height:1; margin-right:10px;">--%>
            <%--条条祝福给您送上</p>--%>
            <%--<p style="width:15px;color:#b60927; display:inline-block; font-size:90%; line-height:1; margin-right:10px;">--%>
            <%--美丽花灯传递吉祥</p>--%>
            <%--<p style="width:15px;color:#b60927; display:inline-block; font-size:90%; line-height:1; margin-right:10px;">--%>
            <%--圆圆汤圆味道芬芳</p></div>--%>

            <%--</div>--%>
            <%--<div class="ani resize"--%>
            <%--style="width:60px;height:110px;left:260px;top:185px;z-index:3;color:#b60927; font-size:160%; font-weight:bold;"--%>
            <%--swiper-animate-effect="fadeInRight" swiper-animate-duration="1s" swiper-animate-delay="1s">--%>
            <%--<div class="txt">--%>
            <%--元<br>宵<br>节--%>
            <%--</div>--%>
            <%--</div>--%>
            <%--<img src="/assets/i/upload/flower.png" class="ani resize" style="width:180px;height:220px;left:0;top:0;z-index:2;"--%>
            <%--swiper-animate-effect="fadeInLeft" swiper-animate-duration="0.5s" swiper-animate-delay="0s">--%>
            <%--<img src="/assets/i/upload/s1.png" class="ani resize" style="width:320px;height:200px;left:0px;top:140px;z-index:1;"--%>
            <%--swiper-animate-effect="fadeIn" swiper-animate-duration="0.5s" swiper-animate-delay="0.5s">--%>

        </section>


    </div>

    <img src="/assets/i/web-swipe-tip.png" style="width:20px;height:15px; top:460px; left:150px;" id="array"
         class="resize">
    <div class="swiper-pagination"></div>
</div>

<script src="/assets/js/swiper.min.js"></script>
<script src="/assets/js/swiper.animate.min.js"></script>
<script src="/assets/js/zepto.min.js"></script>
<script src="/assets/js/wedding.js"></script>
</body>
</html>
