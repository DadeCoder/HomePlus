package com.dade.module.chat.model;

import com.dade.common.mongodb.MongoObject;
import com.dade.module.user.MessageUser;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * 单条聊天消息
 * Created by dade on 2016/1/10
 */
@Document(collection="chat_message")
public class ChatMessage implements MongoObject
{
    @Id
    private String id;              // 消息ID
    private String ownerUserId;     // 消息拥有者用户ID
    private String chatUserId;      // 聊天对象的用户ID

    private MessageUser sender;     // 消息发送者
    private MessageUser receiver;   // 接收者
    private MessageContent content; // 消息内容
    private Date sendDate;          // 消息发送时间

    private Boolean read;           // 消息是否已读
    private Date readDate;          // 消息阅读时间

    public static final String FIELD_ID = "id";
    public static final String FIELD_OWNER_USER_ID = "ownerUserId";
    public static final String FIELD_CHAT_USER_ID = "chatUserId";
    public static final String FIELD_READ = "read";
    public static final String FIELD_READ_DATE = "readDate";

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOwnerUserId() {
        return ownerUserId;
    }

    public void setOwnerUserId(String ownerUserId) {
        this.ownerUserId = ownerUserId;
    }

    public MessageUser getSender() {
        return sender;
    }

    public void setSender(MessageUser sender) {
        this.sender = sender;
    }

    public MessageUser getReceiver() {
        return receiver;
    }

    public void setReceiver(MessageUser receiver) {
        this.receiver = receiver;
    }

    public MessageContent getContent() {
        return content;
    }

    public void setContent(MessageContent content) {
        this.content = content;
    }

    public Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }

    public Date getReadDate() {
        return readDate;
    }

    public void setReadDate(Date readDate) {
        this.readDate = readDate;
    }

    public Boolean getRead() {
        return read;
    }

    public void setRead(Boolean read) {
        this.read = read;
    }

    public String getChatUserId() {
        return chatUserId;
    }

    public void setChatUserId(String chatUserId) {
        this.chatUserId = chatUserId;
    }

    @Override
    public String toString() {
        return "ChatMessage{" +
                "id='" + id + '\'' +
                ", ownerUserId='" + ownerUserId + '\'' +
                ", chatUserId='" + chatUserId + '\'' +
                ", sender=" + sender +
                ", receiver=" + receiver +
                ", content=" + content +
                ", sendDate=" + sendDate +
                ", read=" + read +
                ", readDate=" + readDate +
                '}';
    }
}
