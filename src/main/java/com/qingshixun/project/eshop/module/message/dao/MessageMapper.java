package com.qingshixun.project.eshop.module.message.dao;


import org.apache.ibatis.annotations.Param;


import com.qingshixun.project.eshop.dto.Message;
import com.qingshixun.project.eshop.web.MyBatisRepository;

@MyBatisRepository
public interface MessageMapper {

    /**
     * 保存发送和接收到的消息
     */
    //@Insert("INSERT INTO message values(null,#{message.clientIp},#{message.sendContent},#{message.replyContent},#{message.createTime}) ")
    void saveMessage(@Param("message") Message message);

}