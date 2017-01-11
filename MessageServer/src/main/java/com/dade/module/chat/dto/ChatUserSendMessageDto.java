package com.dade.module.chat.dto;


import com.dade.network.IDto;
import com.dade.rpcapi.dto.chat.MessageContentDto;

/**
 * 发送消息给好友
 * Created by dade on 2016/1/10
 */
public class ChatUserSendMessageDto implements IDto
{
    private String receiverId;          // 发送给的接收用户ID
    private MessageContentDto message;  // 消息内容

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    public MessageContentDto getMessage() {
        return message;
    }

    public void setMessage(MessageContentDto message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ChatUserSendMessageDto{" +
                "receiverId='" + receiverId + '\'' +
                ", message=" + message +
                '}';
    }
}
