package com.qingshixun.project.eshop.module.message.service;

import com.alibaba.fastjson.JSONObject;
import com.qingshixun.project.eshop.dto.Message;
import com.qingshixun.project.eshop.module.message.dao.MessageMapper;
import com.qingshixun.project.eshop.util.HttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;



/**
 * 发送和接收用户消息
 *
 * @author Golovin
 */
@Service
public class MessageService {

	@Autowired
	MessageMapper messageMapper;

	/**
	 * 接收前端页面的消息，并把消息通过图灵机器人的API传输给机器人，并给出回答.
	 * 
	 * @param content  接收消息的内容
	 * @param clientIp 发送消息的用户IP
	 * @return 图灵机器人的回复
	 */
	public String sendMessage(String content, String clientIp) {

		// 日期格式化类
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");

		// 得到格式化后的日期字符串
		String date = simpleDateFormat.format(new Date());

		// 截取日期字符串
		date = date.substring(11, 14);

		// 获取随机数
		double random = Math.floor(Math.random() * 100);

		// 组合随机数和日期字符串
		String randomID = date + new Double(random).longValue();

		// 生成图灵机器人需要的参数格式，简化定义一个id
		Long userid = 123456L;
		String generateContent = generate(content, userid);

		// 访问图灵机器人得到返回结果
		String result = HttpUtil.sendPost(HttpUtil.getTulingUrl(), generateContent);

		// 格式化返回结果为JSON格式数据
		JSONObject object = JSONObject.parseObject(result);

		// 如果是文本，取出values中的text
		String text = object.getJSONArray("results").getJSONObject(0).getJSONObject("values").getString("text");

		// 构造消息实体类
		Message message = new Message();
		Long id = Long.parseLong(randomID);
		message.setId(id);
		message.setClientIp(clientIp);
		message.setSendContent(content);
		message.setReplyContent(text);
		message.setCreateTime(new Date());

		// 保存为历史消息
		messageMapper.saveMessage(message);
		return text;
	}

	/**
	 * 生成图灵机器人API需要的字符串数据
	 * 
	 * @param content 接收消息的内容
	 * @param userid  这里随意编写的用户ID
	 * @return 图灵机器人API需要的字符串数据
	 */
	private String generate(String content, Long userid) {
		// userInfo
		JSONObject userInfo = new JSONObject();
		userInfo.put("apiKey", "5f8cf93cd0a346198af6cdc2700e2c0f");
		userInfo.put("userId", userid);

		// inputText 输入的文本
		JSONObject inputText = new JSONObject();
		inputText.put("text", content);

		// location
		JSONObject location = new JSONObject();
		location.put("city", "湖北");
		location.put("province", "武汉");
		location.put("street", "光谷三路");

		// selfInfo
		JSONObject selfInfo = new JSONObject();
		selfInfo.put("location", location);

		// perception
		JSONObject perception = new JSONObject();
		perception.put("selfInfo", selfInfo);
		perception.put("inputText", inputText);

		JSONObject json = new JSONObject();
		json.put("reqType", 0);
		json.put("perception", perception);
		json.put("userInfo", userInfo);
		return json.toJSONString();
	}
}