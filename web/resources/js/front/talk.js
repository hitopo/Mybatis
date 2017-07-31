/**
 * 获取当前系统时间	yyyy-MM-dd HH:MM:SS
 */
function getCurrentDate() {
	var date = new Date();
	var seperator1 = "-";
	var seperator2 = ":";
	var month = date.getMonth() + 1;
	var hour = date.getHours();
	var minute = date.getMinutes();
	var strDate = date.getDate();
	if (month >= 1 && month <= 9) {
		month = "0" + month;
	}
	if (strDate >= 0 && strDate <= 9) {
		strDate = "0" + strDate;
	}
	if (hour >= 0 && hour <= 9) {
		hour = "0" + hour;
	}
	if (minute >= 0 && minute <= 9) {
		minute = "0" + minute;
	}
	var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate + " " + hour + seperator2 + minute;
	return currentdate;
}

/**
 * 在前台页面上追加显示发送以及回复内容
 * @param   myClass 样式
 * @param  name 显示的标题
 * @param  content 显示的内容
 */
function appendDialog(myClass, name, content) {
	//拼接html语句
	var div = "";
	div += "<div class='" + myClass + "'>";
	div += "<div class='user'><img src='" + $("#basePath").val() +
		"resources/images/thumbs/" + myClass + ".jpg'/>" + name + "</div>";
	""
	div += "<div class='talk_recordtextbg'>&nbsp;</div>";
	div += "<div class='talk_recordtext'>";
	div += "<h3>" + content + "</h3>";
	div += "<span class='talk_time'>" + getCurrentDate() + "</span>";
	div += "</div>";
	div += "</div>";
	//追加显示数据
	$("#jp-container").children().eq(0).children().eq(0).append(div);
}

/**
 * 发送消息
 */
function send() {
	//content前台验证
	var content = $("#content").val();
	// content = null;
	// content = undefined;
	// content = 0(数字0不是字符串);
	// !content是true
	if (!content) {
		//没有输入内容直接返回
		return;
	}
	//向后台发送ajax请求
	$.ajax({
		url: $("#basePath").val() + 'AutoReply.action',
		type: 'post',
		dataType: 'text',
		data: {
			'content': content
		},
		timeout: 10000,
		success: function(data) {
			//追加显示发送的内容和后台回复的内容
			appendDialog("talk_recordboxme", "My账号", content);
			appendDialog("talk_recordbox", "公众号", data);
			// 输入框重置为空
			$("#content").val('');
			// 页面渲染
			render();
		}
	})
}


/**
 * 渲染方法，加载滚动条
 * 这里使用了插件，不必了解具体细节，知道是什么功能即可
 */
function render() {
	// the element we want to apply the jScrollPane
	var $el = $('#jp-container').jScrollPane({
			verticalGutter: -16
		}),
		// the extension functions and options 	
		extensionPlugin = {
			extPluginOpts: {
				// speed for the fadeOut animation
				mouseLeaveFadeSpeed: 500,
				// scrollbar fades out after hovertimeout_t milliseconds
				hovertimeout_t: 1000,
				// if set to false, the scrollbar will be shown on mouseenter and hidden on mouseleave
				// if set to true, the same will happen, but the scrollbar will be also hidden on mouseenter after "hovertimeout_t" ms
				// also, it will be shown when we start to scroll and hidden when stopping
				useTimeout: true,
				// the extension only applies for devices with width > deviceWidth
				deviceWidth: 980
			},
			hovertimeout: null, // timeout to hide the scrollbar
			isScrollbarHover: false, // true if the mouse is over the scrollbar
			elementtimeout: null, // avoids showing the scrollbar when moving from inside the element to outside, passing over the scrollbar
			isScrolling: false, // true if scrolling
			addHoverFunc: function() {
				// run only if the window has a width bigger than deviceWidth
				if ($(window).width() <= this.extPluginOpts.deviceWidth) return false;
				var instance = this;
				// functions to show / hide the scrollbar
				$.fn.jspmouseenter = $.fn.show;
				$.fn.jspmouseleave = $.fn.fadeOut;
				// hide the jScrollPane vertical bar
				var $vBar = this.getContentPane().siblings('.jspVerticalBar').hide();
				/*
				 * mouseenter / mouseleave events on the main element
				 * also scrollstart / scrollstop - @James Padolsey : http://james.padolsey.com/javascript/special-scroll-events-for-jquery/
				 */
				$el.bind('mouseenter.jsp', function() {

					// show the scrollbar
					$vBar.stop(true, true).jspmouseenter();

					if (!instance.extPluginOpts.useTimeout) return false;

					// hide the scrollbar after hovertimeout_t ms
					clearTimeout(instance.hovertimeout);
					instance.hovertimeout = setTimeout(function() {
						// if scrolling at the moment don't hide it
						if (!instance.isScrolling)
							$vBar.stop(true, true).jspmouseleave(instance.extPluginOpts.mouseLeaveFadeSpeed || 0);
					}, instance.extPluginOpts.hovertimeout_t);
				}).bind('mouseleave.jsp', function() {
					// hide the scrollbar
					if (!instance.extPluginOpts.useTimeout)
						$vBar.stop(true, true).jspmouseleave(instance.extPluginOpts.mouseLeaveFadeSpeed || 0);
					else {
						clearTimeout(instance.elementtimeout);
						if (!instance.isScrolling)
							$vBar.stop(true, true).jspmouseleave(instance.extPluginOpts.mouseLeaveFadeSpeed || 0);
					}
				});
				if (this.extPluginOpts.useTimeout) {
					$el.bind('scrollstart.jsp', function() {
						// when scrolling show the scrollbar
						clearTimeout(instance.hovertimeout);
						instance.isScrolling = true;
						$vBar.stop(true, true).jspmouseenter();
					}).bind('scrollstop.jsp', function() {
						// when stop scrolling hide the scrollbar (if not hovering it at the moment)
						clearTimeout(instance.hovertimeout);
						instance.isScrolling = false;
						instance.hovertimeout = setTimeout(function() {
							if (!instance.isScrollbarHover)
								$vBar.stop(true, true).jspmouseleave(instance.extPluginOpts.mouseLeaveFadeSpeed || 0);
						}, instance.extPluginOpts.hovertimeout_t);
					});
					// wrap the scrollbar
					// we need this to be able to add the mouseenter / mouseleave events to the scrollbar
					var $vBarWrapper = $('<div/>').css({
						position: 'absolute',
						left: $vBar.css('left'),
						top: $vBar.css('top'),
						right: $vBar.css('right'),
						bottom: $vBar.css('bottom'),
						width: $vBar.width(),
						height: $vBar.height()
					}).bind('mouseenter.jsp', function() {
						clearTimeout(instance.hovertimeout);
						clearTimeout(instance.elementtimeout);
						instance.isScrollbarHover = true;
						// show the scrollbar after 100 ms.
						// avoids showing the scrollbar when moving from inside the element to outside, passing over the scrollbar								
						instance.elementtimeout = setTimeout(function() {
							$vBar.stop(true, true).jspmouseenter();
						}, 100);
					}).bind('mouseleave.jsp', function() {
						// hide the scrollbar after hovertimeout_t
						clearTimeout(instance.hovertimeout);
						instance.isScrollbarHover = false;
						instance.hovertimeout = setTimeout(function() {
							// if scrolling at the moment don't hide it
							if (!instance.isScrolling)
								$vBar.stop(true, true).jspmouseleave(instance.extPluginOpts.mouseLeaveFadeSpeed || 0);
						}, instance.extPluginOpts.hovertimeout_t);
					});
					$vBar.wrap($vBarWrapper);
				}
			}
		},
		// the jScrollPane instance
		jspapi = $el.data('jsp');
	// extend the jScollPane by merging	
	$.extend(true, jspapi, extensionPlugin);
	jspapi.addHoverFunc();
}

//页面初始化时显示公众号提示信息
$(document).ready(function() {
	//响应回车事件
	//实现点击回车键自动发送数据
	$("body").keyup(function(event) {
		if (event.keyCode === 13) {
			send();
		}
	});

	render();
	
	//添加开场白
	var startStr = "欢迎使用微信公众号，输入”关键字“即可获取最新信息<br/>";
	startStr+="回复“帮助”即可获取所有指令信息"
	appendDialog("talk_recordbox", "公众号", startStr);
	
	render();
});