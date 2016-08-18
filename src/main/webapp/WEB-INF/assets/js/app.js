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
/*
 * jquery 初始化form插件，传入一个json对象，为form赋值 
 * version: 1.0.0-2013.06.24
 * @requires jQuery v1.5 or later
 * Copyright (c) 2013
 * note:  1、此方法能赋值一般所有表单，但考虑到checkbox的赋值难度，以及表单中很少用checkbox，这里不对checkbox赋值
 *		  2、此插件现在只接收json赋值，不考虑到其他的来源数据
 *		  3、对于特殊的textarea，比如CKEditor,kindeditor...，他们的赋值有提供不同的自带方法，这里不做统一，如果项目中有用到，不能正确赋值，请单独赋值
 */	
(function($){
	$.fn.extend({
		initForm:function(options){
			//默认参数
			var defaults = {
				jsonValue:"",
				isDebug:false	//是否需要调试，这个用于开发阶段，发布阶段请将设置为false，默认为false,true将会把name value打印出来
			}
			//设置参数
			var setting = $.extend({}, defaults, options);
			var form = this;
			jsonValue = setting.jsonValue;
			//如果传入的json字符串，将转为json对象
			if($.type(setting.jsonValue) === "string"){
				jsonValue = $.parseJSON(jsonValue);
			}
			//如果传入的json对象为空，则不做任何操作
			if(!$.isEmptyObject(jsonValue)){
				var debugInfo = "";
				$.each(jsonValue,function(key,value){
					//是否开启调试，开启将会把name value打印出来
					if(setting.isDebug){
						alert("name:"+key+"; value:"+value);
						debugInfo += "name:"+key+"; value:"+value+" || ";
					}
					var formField = form.find("[name='"+key+"']");
					if($.type(formField[0]) === "undefined"){
						if(setting.isDebug){
							alert("can not find name:["+key+"] in form!!!");	//没找到指定name的表单
						}
					} else {
						var fieldTagName = formField[0].tagName.toLowerCase();
						if(fieldTagName == "input"){
							if(formField.attr("type") == "radio"){
								$("input:radio[name='"+key+"'][value='"+value+"']").attr("checked","checked");
							} else {
								formField.val(value);
							}
						} else if(fieldTagName == "select"){
							//do something special
							formField.val(value);
						} else if(fieldTagName == "textarea"){
							//do something special
							formField.val(value);
						} else {
							formField.val(value);
						}
					}
				})
				if(setting.isDebug){
					alert(debugInfo);
				}
			}
			return form;	//返回对象，提供链式操作
		}
	});
})(jQuery)