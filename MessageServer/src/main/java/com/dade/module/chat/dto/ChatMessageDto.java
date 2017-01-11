package com.dade.module.chat.dto;


import com.dade.network.IDto;
import com.dade.rpcapi.dto.chat.MessageContentDto;
import com.dade.rpcapi.dto.user.MessageUserDto;

import java.util.Date;

/**
 * 消息内容
 * Created by dade on 2016/1/10
 */
public class ChatMessageDto implements IDto
{
    private String id;                  // 消息ID

    private MessageUserDto sender;      // 发送者
    private MessageUserDto receiver;    // 接收者
    private MessageContentDto content;  // 消息内容
    private Date sendDate;              // 消息发送(创建)时间

    private Boolean isInChatList;       // 是否在好友列表里面

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public MessageUserDto getReceiver() {
        return receiver;
    }

    public void setReceiver(MessageUserDto receiver) {
        this.receiver = receiver;
    }

    public MessageUserDto getSender() {
        return sender;
    }

    public void setSender(MessageUserDto sender) {
        this.sender = sender;
    }

    public MessageContentDto getContent() {
        return content;
    }

    public void setContent(MessageContentDto content) {
        this.content = content;
    }

    public Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }

    public Boolean getInChatList() {
        return isInChatList;
    }

    public void setInChatList(Boolean inChatList) {
        isInChatList = inChatList;
    }

    @Override
    public String toString() {
        return "ChatMessageDto{" +
                "id='" + id + '\'' +
                ", sender=" + sender +
                ", receiver=" + receiver +
                ", content=" + content +
                ", sendDate=" + sendDate +
                ", isInChatList=" + isInChatList +
                '}';
    }
}
