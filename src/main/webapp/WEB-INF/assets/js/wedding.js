/*Mr.Zhang wedding*/
$(function(){
	var page = 0,
		per_page = 100,
		photo_default_size = 150,
		picture_width = photo_default_size,
		picture_height = photo_default_size,
		max_w_photos, max_h_photos
		data = [];

	// Global variables that cache selectors

	var win = $(window),
		loading = $('#loading'),
		gallery = $('#gallery');

	// Fetch all the available images with 
	// a GET AJAX request

	//$.getJSON('/wedding/gallery', function(response){
    //
	//	// response.data holds the photos
    //
	//	data = response.data;
    //
	//	// Trigger our custom data-ready event
	//	gallery.trigger('data-ready');
    //
	//});

	var json ={"data":[{"large":"http://blog-10046991.cos.myqcloud.com/large/007.jpg","thumb":"/assets/photos/thumbs/007.jpg"},{"large":"http://blog-10046991.cos.myqcloud.com/large/008.jpg","thumb":"/assets/photos/thumbs/008.jpg"},{"large":"http://blog-10046991.cos.myqcloud.com/large/010.jpg","thumb":"/assets/photos/thumbs/010.jpg"},{"large":"http://blog-10046991.cos.myqcloud.com/large/011.jpg","thumb":"/assets/photos/thumbs/011.jpg"},{"large":"http://blog-10046991.cos.myqcloud.com/large/013.jpg","thumb":"/assets/photos/thumbs/013.jpg"},{"large":"http://blog-10046991.cos.myqcloud.com/large/016.jpg","thumb":"/assets/photos/thumbs/016.jpg"},{"large":"http://blog-10046991.cos.myqcloud.com/large/018.jpg","thumb":"/assets/photos/thumbs/018.jpg"},{"large":"http://blog-10046991.cos.myqcloud.com/large/019.jpg","thumb":"/assets/photos/thumbs/019.jpg"},{"large":"http://blog-10046991.cos.myqcloud.com/large/020.jpg","thumb":"/assets/photos/thumbs/020.jpg"},{"large":"http://blog-10046991.cos.myqcloud.com/large/022.jpg","thumb":"/assets/photos/thumbs/022.jpg"},{"large":"http://blog-10046991.cos.myqcloud.com/large/023.jpg","thumb":"/assets/photos/thumbs/023.jpg"},{"large":"http://blog-10046991.cos.myqcloud.com/large/024.jpg","thumb":"/assets/photos/thumbs/024.jpg"},{"large":"http://blog-10046991.cos.myqcloud.com/large/026.jpg","thumb":"/assets/photos/thumbs/026.jpg"},{"large":"http://blog-10046991.cos.myqcloud.com/large/028.jpg","thumb":"/assets/photos/thumbs/028.jpg"},{"large":"http://blog-10046991.cos.myqcloud.com/large/029.jpg","thumb":"/assets/photos/thumbs/029.jpg"},{"large":"http://blog-10046991.cos.myqcloud.com/large/037.jpg","thumb":"/assets/photos/thumbs/037.jpg"},{"large":"http://blog-10046991.cos.myqcloud.com/large/038.jpg","thumb":"/assets/photos/thumbs/038.jpg"},{"large":"http://blog-10046991.cos.myqcloud.com/large/039.jpg","thumb":"/assets/photos/thumbs/039.jpg"},{"large":"http://blog-10046991.cos.myqcloud.com/large/043.jpg","thumb":"/assets/photos/thumbs/043.jpg"},{"large":"http://blog-10046991.cos.myqcloud.com/large/044.jpg","thumb":"/assets/photos/thumbs/044.jpg"},{"large":"http://blog-10046991.cos.myqcloud.com/large/045.jpg","thumb":"/assets/photos/thumbs/045.jpg"},{"large":"http://blog-10046991.cos.myqcloud.com/large/046.jpg","thumb":"/assets/photos/thumbs/046.jpg"},{"large":"http://blog-10046991.cos.myqcloud.com/large/050.jpg","thumb":"/assets/photos/thumbs/050.jpg"},{"large":"http://blog-10046991.cos.myqcloud.com/large/058.jpg","thumb":"/assets/photos/thumbs/058.jpg"},{"large":"http://blog-10046991.cos.myqcloud.com/large/059.jpg","thumb":"/assets/photos/thumbs/059.jpg"},{"large":"http://blog-10046991.cos.myqcloud.com/large/063.jpg","thumb":"/assets/photos/thumbs/063.jpg"},{"large":"http://blog-10046991.cos.myqcloud.com/large/064.jpg","thumb":"/assets/photos/thumbs/064.jpg"},{"large":"http://blog-10046991.cos.myqcloud.com/large/066.jpg","thumb":"/assets/photos/thumbs/066.jpg"},{"large":"http://blog-10046991.cos.myqcloud.com/large/068.jpg","thumb":"/assets/photos/thumbs/068.jpg"},{"large":"http://blog-10046991.cos.myqcloud.com/large/069.jpg","thumb":"/assets/photos/thumbs/069.jpg"},{"large":"http://blog-10046991.cos.myqcloud.com/large/073.jpg","thumb":"/assets/photos/thumbs/073.jpg"},{"large":"http://blog-10046991.cos.myqcloud.com/large/074.jpg","thumb":"/assets/photos/thumbs/074.jpg"},{"large":"http://blog-10046991.cos.myqcloud.com/large/075.jpg","thumb":"/assets/photos/thumbs/075.jpg"},{"large":"http://blog-10046991.cos.myqcloud.com/large/078.jpg","thumb":"/assets/photos/thumbs/078.jpg"},{"large":"http://blog-10046991.cos.myqcloud.com/large/079.jpg","thumb":"/assets/photos/thumbs/079.jpg"},{"large":"http://blog-10046991.cos.myqcloud.com/large/080.jpg","thumb":"/assets/photos/thumbs/080.jpg"},{"large":"http://blog-10046991.cos.myqcloud.com/large/081.jpg","thumb":"/assets/photos/thumbs/081.jpg"},{"large":"http://blog-10046991.cos.myqcloud.com/large/085.jpg","thumb":"/assets/photos/thumbs/085.jpg"},{"large":"http://blog-10046991.cos.myqcloud.com/large/086.jpg","thumb":"/assets/photos/thumbs/086.jpg"},{"large":"http://blog-10046991.cos.myqcloud.com/large/087.jpg","thumb":"/assets/photos/thumbs/087.jpg"},{"large":"http://blog-10046991.cos.myqcloud.com/large/089.jpg","thumb":"/assets/photos/thumbs/089.jpg"},{"large":"http://blog-10046991.cos.myqcloud.com/large/090.jpg","thumb":"/assets/photos/thumbs/090.jpg"},{"large":"http://blog-10046991.cos.myqcloud.com/large/094.jpg","thumb":"/assets/photos/thumbs/094.jpg"},{"large":"http://blog-10046991.cos.myqcloud.com/large/100.jpg","thumb":"/assets/photos/thumbs/100.jpg"},{"large":"http://blog-10046991.cos.myqcloud.com/large/101.jpg","thumb":"/assets/photos/thumbs/101.jpg"},{"large":"http://blog-10046991.cos.myqcloud.com/large/106.jpg","thumb":"/assets/photos/thumbs/106.jpg"},{"large":"http://blog-10046991.cos.myqcloud.com/large/108.jpg","thumb":"/assets/photos/thumbs/108.jpg"},{"large":"http://blog-10046991.cos.myqcloud.com/large/110.jpg","thumb":"/assets/photos/thumbs/110.jpg"},{"large":"http://blog-10046991.cos.myqcloud.com/large/112.jpg","thumb":"/assets/photos/thumbs/112.jpg"},{"large":"http://blog-10046991.cos.myqcloud.com/large/113.jpg","thumb":"/assets/photos/thumbs/113.jpg"},{"large":"http://blog-10046991.cos.myqcloud.com/large/115.jpg","thumb":"/assets/photos/thumbs/115.jpg"},{"large":"http://blog-10046991.cos.myqcloud.com/large/117.jpg","thumb":"/assets/photos/thumbs/117.jpg"},{"large":"http://blog-10046991.cos.myqcloud.com/large/119.jpg","thumb":"/assets/photos/thumbs/119.jpg"},{"large":"http://blog-10046991.cos.myqcloud.com/large/121.jpg","thumb":"/assets/photos/thumbs/121.jpg"},{"large":"http://blog-10046991.cos.myqcloud.com/large/122.jpg","thumb":"/assets/photos/thumbs/122.jpg"},{"large":"http://blog-10046991.cos.myqcloud.com/large/123.jpg","thumb":"/assets/photos/thumbs/123.jpg"},{"large":"http://blog-10046991.cos.myqcloud.com/large/124.jpg","thumb":"/assets/photos/thumbs/124.jpg"},{"large":"http://blog-10046991.cos.myqcloud.com/large/127.jpg","thumb":"/assets/photos/thumbs/127.jpg"},{"large":"http://blog-10046991.cos.myqcloud.com/large/128.jpg","thumb":"/assets/photos/thumbs/128.jpg"},{"large":"http://blog-10046991.cos.myqcloud.com/large/130.jpg","thumb":"/assets/photos/thumbs/130.jpg"},{"large":"http://blog-10046991.cos.myqcloud.com/large/133.jpg","thumb":"/assets/photos/thumbs/133.jpg"},{"large":"http://blog-10046991.cos.myqcloud.com/large/135.jpg","thumb":"/assets/photos/thumbs/135.jpg"},{"large":"http://blog-10046991.cos.myqcloud.com/large/136.jpg","thumb":"/assets/photos/thumbs/136.jpg"},{"large":"http://blog-10046991.cos.myqcloud.com/large/139.jpg","thumb":"/assets/photos/thumbs/139.jpg"},{"large":"http://blog-10046991.cos.myqcloud.com/large/140.jpg","thumb":"/assets/photos/thumbs/140.jpg"},{"large":"http://blog-10046991.cos.myqcloud.com/large/141.jpg","thumb":"/assets/photos/thumbs/141.jpg"}]};
	data = json.data;
	gallery.trigger('data-ready');


	// Redraw the photos on screen
	gallery.on('data-ready window-resized page-turned', function(event, direction){

		var cache = [],
			deferreds = [];

		gallery.trigger('loading');

		// The photos that we should be showing on the new screen
		var set = data.slice(get_page_start(), get_page_start() + get_per_page());

		$.each(set, function(){

			// Create a deferred for each image, so
			// we know when they are all loaded
			deferreds.push($.loadImage(this.thumb));

			// build the cache
			cache.push('<a href="' + this.large + '" class="swipebox"' +
						'style="width:' + picture_width + 'px;height:' + picture_height + 'px;background-image:url(' + this.thumb + ')">'+
						'</a>');
		});

		if(is_prev_page()){
			cache.unshift('<a class="prev" style="width:' + picture_width + 'px;height:' + picture_height + 'px;"></a>');
		}

		if(is_next_page()){
			cache.push('<a class="next" style="width:' + picture_width + 'px;height:' + picture_height + 'px;"></a>');
		}

		if(!cache.length){
			// There aren't any images
			return false;
		}

		// Call the $.when() function using apply, so that 
		// the deferreds array is passed as individual arguments.
		// $.when(arg1, arg2) is the same as $.when.apply($, [arg1, arg2])

		$.when.apply($, deferreds).always(function(){

			// All images have been loaded!

			if(event.type == 'window-resized'){

				// No need to animate the photos
				// if this is a resize event

				gallery.html(cache.join(''));
				show_photos_static();

				// Re-initialize the swipebox
				$('#gallery .swipebox').swipebox();

			}
			else{

				// Create a fade out effect
				gallery.fadeOut(function(){

					// Add the photos to the gallery
					gallery.html(cache.join(''));

					if(event.type == 'page-turned' && direction == 'br'){
						show_photos_with_animation_br();
					}
					else{
						show_photos_with_animation_tl();
					}

					// Re-initialize the swipebox
					$('#gallery .swipebox').swipebox();

					gallery.show();

				});
			}

			gallery.trigger('loading-finished');
		});

	});


	gallery.on('loading',function(){
		// show the preloader
		loading.show();
	});

	gallery.on('loading-finished',function(){
		// hide the preloader
		loading.hide();
	});

	gallery.on('click', '.next', function(){
		page++;
		gallery.trigger('page-turned',['br']);
	});

	gallery.on('click', '.prev', function(){
		page--;
		gallery.trigger('page-turned',['tl']);
	});

	
	// Monitor window resizing or changing device orientation
	win.on('resize', function(e){

		var width = win.width(),
			height = win.height(),
			gallery_width, gallery_height,
			difference;

		// How many photos can we fit on one line?
		max_w_photos = Math.ceil(width/photo_default_size);

		// Difference holds how much we should shrink each of the photos
		difference = (max_w_photos * photo_default_size - width) / max_w_photos;

		// Set the global width variable of the pictures.
		picture_width = Math.ceil(photo_default_size - difference);

		// Set the gallery width
		gallery_width = max_w_photos * picture_width;

		// Let's do the same with the height:

		max_h_photos = Math.ceil(height/photo_default_size);
		difference = (max_h_photos * photo_default_size - height) / max_h_photos;
		picture_height = Math.ceil(photo_default_size - difference);
		gallery_height = max_h_photos * picture_height;

		// How many photos to show per page?
		per_page = max_w_photos*max_h_photos;

		// Resize the gallery holder
		gallery.width(gallery_width).height(gallery_height);

		gallery.trigger('window-resized');

	}).resize();

	function show_photos_static(){

		// Show the images without any animations
		gallery.find('a').addClass('static');

	}

	function show_photos_with_animation_tl(){

		// Animate the images from the top-left

		var photos = gallery.find('a');

		for(var i=0; i<max_w_photos + max_h_photos; i++){

			var j = i;

			// Loop through all the lines
			for(var l = 0; l < max_h_photos; l++){

				// If the photo is not of the current line, stop.
				if(j < l*max_w_photos) break;

				// Schedule a timeout. It is wrapped in an anonymous
				// function to preserve the value of the j variable

				(function(j){
					setTimeout(function(){
						photos.eq(j).addClass('show');
					}, i*50);
				})(j);

				// Increment the counter so it points to the photo
				// to the left on the line below

				j += max_w_photos - 1;
			}
		}
	}

	function show_photos_with_animation_br(){

		// Animate the images from the bottom-right

		var photos = gallery.find('a');

		for(var i=0; i<max_w_photos + max_h_photos; i++){

			var j = per_page - i;

			// Loop through all the lines
			for(var l = max_h_photos-1; l >= 0; l--){

				// If the photo is not of the current line, stop.
				if(j > (l+1)*max_w_photos-1) break;

				// Schedule a timeout. It is wrapped in an anonymous
				// function to preserve the value of the j variable

				(function(j){
					setTimeout(function(){
						photos.eq(j).addClass('show');
					}, i*50);
				})(j);

				// Decrement the counter so it points to the photo
				// to the right on the line above

				j -= max_w_photos - 1;
			}
		}
	}

	/* Helper functions */

	function get_per_page(){

		// How many pictures should be shown on current page

		// The first page has only one arrow,
		// so we decrease the per_page argument with 1
		if(page == 0){
			return per_page - 1;
		}

		// Is this the last page?
		if(get_page_start() + per_page - 1 > data.length - 1){
			// It also has 1 arrow.
			return per_page - 1;
		}

		// The other pages have two arrows.
		return per_page - 2;
	}

	function get_page_start(p){

		// Which position holds the first photo
		// that is to be shown on the give page

		if(p === undefined){
			p = page;
		}

		if(p == 0){
			return 0;
		}

		// (per_page - 2) because the arrows take up two places for photos
		// + 1 at the end because the first page has only a next arrow.

		return (per_page - 2)*p + 1;
	}

	function is_next_page(){

		// Should we show the next arrow?

		return data.length > get_page_start(page + 1);
	}

	function is_prev_page(){

		// Should we show the previous arrow?

		return page > 0;
	}

		// 百度地图API功能
		var map = new BMap.Map("allmap");
		map.centerAndZoom(new BMap.Point(108.946223, 34.214037), 15);
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
	
	
		/**  
		 * music play
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
		$("#mc_play").on('click',function(){
			play_music();
		});
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
});