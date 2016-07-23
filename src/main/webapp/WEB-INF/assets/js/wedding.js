/**
 * Created by zhangmin2 on 2016/7/20.
 */

var scaleW = window.innerWidth / 320;
var scaleH = window.innerHeight / 480;
var resizes = document.querySelectorAll('.resize');
for (var j = 0; j < resizes.length; j++) {
    resizes[j].style.width = parseInt(resizes[j].style.width) * scaleW + 'px';
    resizes[j].style.height = parseInt(resizes[j].style.height) * scaleH + 'px';
    resizes[j].style.top = parseInt(resizes[j].style.top) * scaleH + 'px';
    resizes[j].style.left = parseInt(resizes[j].style.left) * scaleW + 'px';

}
var scales = document.querySelectorAll('.txt');
for (var i = 0; i < scales.length; i++) {
    var ss = scales[i].style;
    ss.webkitTransform = ss.MsTransform = ss.msTransform = ss.MozTransform = ss.OTransform = ss.transform = 'translateX(' + scales[i].offsetWidth * (scaleW - 1) / 2 + 'px) translateY(' + scales[i].offsetHeight * (scaleH - 1) / 2 + 'px)scaleX(' + scaleW + ') scaleY(' + scaleH + ') ';
}


new Swiper('.swiper-container', {
    direction: 'vertical',
    pagination: '.swiper-pagination',
    mousewheelControl: true,
    onInit: function (swiper) {
        swiperAnimateCache(swiper);
        swiperAnimate(swiper);
    },
    onSlideChangeEnd: function (swiper) {
        swiperAnimate(swiper);
    }
})

/*music play */
function play_music(){
    if ($('#mc_play').hasClass('on')){
        $('#mc_play audio').get(0).pause();
        $('#mc_play').attr('class','stop');
    }else{
        $('#mc_play audio').get(0).play();
        $('#mc_play').attr('class','on');
    }
    event.stopPropagation(); //阻止冒泡
}
function just_play(id){
    $('#mc_play audio').get(0).play();
    $('#mc_play').attr('class','on');
    event.stopPropagation(); //阻止冒泡
}
