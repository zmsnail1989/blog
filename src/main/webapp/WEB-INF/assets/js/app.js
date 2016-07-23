$(function() {
		//设置AJAX的全局默认选项
		$.ajaxSetup( {
		    beforeSend : function() {
				// Handle the beforeSend event
		    	$.AMUI.progress.start();
			},
			complete : function() {
				// Handle the complete event
				$.AMUI.progress.done();
			},
		    error: function(jqXHR, textStatus, errorMsg){ // 出错时默认的处理函数
		    	$.AMUI.progress.done();   
		    }
		} );
		var $fullText = $('.admin-fullText');
		$('#admin-fullscreen').on('click', function() {
			$.AMUI.fullscreen.toggle();
		});
		$(document).on($.AMUI.fullscreen.raw.fullscreenchange, function() {
			$fullText.text($.AMUI.fullscreen.isFullscreen ? '退出全屏' : '开启全屏');
		});
		var store = $.AMUI.store;
		 // 获取 'username'
		var cacheobj_menu= store.get('blog_system_menu');
		if (cacheobj_menu) {
			$(".admin-sidebar-list").html(cacheobj_menu);
		}else{
			//加载菜单
			$.ajax({type: "GET",
				  url: "/backend/menu",
				  dataType: "json",
				  success: function(data){
					   var html="<li><a href=\"#\"><span class=\"am-icon-home\"></span> 个人资料</a></li>";
					   for (var i = 0; i < data.length; i++) {
						  var parent=data[i];
						  html+="<li class=\"admin-parent\"><a class=\"am-cf am-collapsed\" data-am-collapse=\"{target: '#"+parent.id+"'}\"><span class=\"am-icon-file\"></span> "+parent.name+" <span class=\"am-icon-angle-right am-fr am-margin-right\"></span></a>";
						  html+="<ul class=\"am-list am-collapse admin-sidebar-sub am-collapse\" id="+parent.id+">";
						  for(var j = 0; j < parent.submenu.length; j++){
							  var sub = parent.submenu[j];
							  html+="<li><a href="+sub.url+" class=\"am-cf\">"+sub.name+"<span class=\"am-icon-star am-fr am-margin-right admin-icon-yellow\"></span></a></li>";
						  }
						  html+="</ul></</li>";
					   }
					   // 存储 'username' 的值为 'marcus'
					   store.set('blog_system_menu', html);
					   $(".admin-sidebar-list").html(html);
				  }
				});
		}
		//注销
		$(".am-icon-power-off").parent().click(function(){
			store.remove('blog_system_menu');
			window.location.href="/backend/logout";
		});
});
