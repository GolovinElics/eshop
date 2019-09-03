package com.qingshixun.project.eshop.dto;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * POJO：简单的Java对象，实际就是普通JavaBeans，类似于我们之前所讲的实体类
 */
public class Message {

    private final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private Long id; // 消息id
    private String clientIp; // 发消息的客户端IP
    private String sendContent; // 客户端发送的聊天消息内容
    private String replyContent; // 机器人的回复
    private Date createTime; // 消息发送时间

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSendContent() {
        return sendContent;
    }

    public void setSendContent(String sendContent) {
        this.sendContent = sendContent;
    }

    public String getReplyContent() {
        return replyContent;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }

    public String getCreateTime() {
        return formatter.format(createTime);
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

}