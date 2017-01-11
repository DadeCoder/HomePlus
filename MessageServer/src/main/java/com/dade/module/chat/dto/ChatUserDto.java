package com.dade.module.chat.dto;


import com.dade.common.LogUtil;
import com.dade.network.IDto;
import com.dade.rpcapi.dto.chat.MessageContentDto;
import com.dade.rpcapi.dto.user.MessageUserDto;

import java.util.Date;

/**
 * 聊天用户dto
 * Created by dade on 2016/1/10
 */
public class ChatUserDto implements IDto, Comparable<ChatUserDto> {
    private MessageUserDto user;                    // 用户
    private Integer unreadCount;                    // 未读消息条目数
    private Date lastMessageDate;                   // 最新一条消息时间
    private MessageContentDto lastMessageContent;   // 最新一条消息内容

    public MessageUserDto getUser() {
        return user;
    }

    public void setUser(MessageUserDto user) {
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

    public MessageContentDto getLastMessageContent() {
        return lastMessageContent;
    }

    public void setLastMessageContent(MessageContentDto lastMessageContent) {
        this.lastMessageContent = lastMessageContent;
    }

    @Override
    public String toString() {
        return "ChatUserDto{" +
                "user=" + user +
                ", unreadCount=" + unreadCount +
                ", lastMessageDate=" + lastMessageDate +
                ", lastMessageContent=" + lastMessageContent +
                '}';
    }

    @Override
    public int compareTo(ChatUserDto o) {
        try {
            return o.getLastMessageDate().compareTo(this.getLastMessageDate());
        } catch (NullPointerException e) {
            LogUtil.error("ChatUserDto.compareTo() has a NullPointException", e);
            return 0;   // when Exception show up, return equal value simply;
        }
    }
}