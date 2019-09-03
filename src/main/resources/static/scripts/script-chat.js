/**
 * 聊天室JS文件
 */
$(document).ready(function() {
	
	var $context = $("#context");
	var $chatMessages = $("#chat-messages");
	var $clientIp = $("#clientIp").val();
	
	// 点击发送按钮，发送消息
	$("#send").on("click", function(e) {
		e.preventDefault();
		// 获取输入框的内容
		var content = $context.val();
		// 如果发送的值为null ，就不发送请求
		if(content.trim()!=''){
			// 渲染数据到展示页面
			var time1 = $.data(new Date());
			var $send = $("<div class=\"message\"><img src=\"../images/1_copy.jpg\" /><div class=\"bubble\">"+content+"<div class=\"corner\"></div><span id=\"left_message\">"+time1+"</span></div></div>");
			$chatMessages.append($send);
			// 将滚动条滚动到可视位置
			$(".message")[$(".message").length-1].scrollIntoView();
			$("#context").val('');
			$.post("/eweb/front/chat/send",{sendContent:content,clientIp:$clientIp},function(result){
				console.log("result的结果是---"+result);
				var time2 = $.data(new Date());
				var $receive = $("<div class=\"message right\"><img src=\"../images/6_copy.jpg\" /><div class=\"bubble\">"+result+"<div class=\"corner\"></div><span id=\"right_message\">"+time2+"</span></div></div>");
				$chatMessages.append($receive);
				$(".message")[$(".message").length-1].scrollIntoView();
			});
		}
	});
	
	$context.keydown(function() {
        if (event.keyCode == "13") {	//keyCode=13是回车键
        	$("#send").click();
        }
    });
	
    $.data = function(time){
    	var y = time.getFullYear();
    	var m = time.getMonth()+1;
    	var d = time.getDate();
    	var h = time.getHours();
    	var mm = time.getMinutes();
    	var s = time.getSeconds();
    	return /*y+'-'+$.add0(m)+'-'+$.add0(d)+' '+*/$.add0(h)+':'+$.add0(mm)+':'+$.add0(s);
    }
    
    $.add0 = function(m){
    	return m<10?'0'+m:m;
    }
});