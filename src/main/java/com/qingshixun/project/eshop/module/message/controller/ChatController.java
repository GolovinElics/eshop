package com.qingshixun.project.eshop.module.message.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qingshixun.project.eshop.module.message.service.MessageService;

@Controller
@RequestMapping(value = "/front/chat")
public class ChatController {

	@Autowired
	MessageService messageService;

	/**
	 * 直接请求转发到聊天界面
	 * 
	 * @param request 通过request获取一下用户的ip地址
	 * @return 聊天界面
	 */
	@RequestMapping("")
	public String index(HttpServletRequest request) {
		// 获取一下本机的IP地址
		String clientIp = request.getRemoteAddr();
		request.setAttribute("clientIp", clientIp);
		return "/chat";
	}

	/**
	 * 用户发送消息给机器人，并且返回应答
	 * 
	 * @return 机器人的回复
	 */
	//@PostMapping("/send")
	@RequestMapping(value = "/send", method = RequestMethod.POST)
	@ResponseBody
	public String sendUserMessage(Model model, @RequestParam("sendContent") String sendContent,
			@RequestParam("clientIp") String clientIp) {
		String reply = messageService.sendMessage(sendContent, clientIp);
		return reply;
	}

}