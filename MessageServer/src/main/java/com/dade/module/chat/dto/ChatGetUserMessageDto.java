package com.dade.module.chat.dto;


import com.dade.network.IDto;

/**
 * 获得用户消息请求Dto
 * Created by dade on 2016/1/10
 */
public class ChatGetUserMessageDto implements IDto
{
    private String userId;      // 获得指定用户ID
    private String messageId;   // 结尾的消息ID
    private Integer count;      // 读取消息条目数

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
