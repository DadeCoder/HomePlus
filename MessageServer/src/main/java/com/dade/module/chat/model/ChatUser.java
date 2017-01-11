package com.dade.module.chat.model;


import com.dade.module.user.MessageUser;

import java.util.Date;

/**
 * 聊天用户对象
 * Created by dade on 2016/1/10
 */
public class ChatUser
{
    private MessageUser user;                   // 用户
    private Integer unreadCount;                // 未读消息条目数
    private Date lastMessageDate;               // 最新一条消息时间
    private MessageContent lastMessageContent;  // 最新一条消息内容

    public MessageUser getUser() {
        return user;
    }

    public void setUser(MessageUser user) {
        this.user = user;
    }

    public Integer getUnreadCount() {
        return unreadCount;
    }

    public void setUnreadCount(Integer unreadCount) {
        this.unreadCount = unreadCount;
    }

    public Date getLastMessageDate() {
        return lastMessageDate;
    }

    public void setLastMessageDate(Date lastMessageDate) {
        this.lastMessageDate = lastMessageDate;
    }

    public MessageContent getLastMessageContent() {
        return lastMessageContent;
    }

    public void setLastMessageContent(MessageContent lastMessageContent) {
        this.lastMessageContent = lastMessageContent;
    }

    @Override
    public String toString() {
        return "ChatUser{" +
                "user=" + user +
                ", unreadCount=" + unreadCount +
                ", lastMessageDate=" + lastMessageDate +
                ", lastMessageContent=" + lastMessageContent +
                '}';
    }
}
