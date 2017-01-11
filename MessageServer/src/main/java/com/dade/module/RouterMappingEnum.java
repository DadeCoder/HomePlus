package com.dade.module;


import com.dade.common.LogUtil;
import com.dade.framework.MessageRouterFactory;
import com.dade.module.chat.mapping.ChatAddUserToListMapping;
import com.dade.module.chat.mapping.ChatGetUserListMapping;
import com.dade.module.chat.mapping.ChatGetUserMessageMapping;
import com.dade.module.chat.mapping.ChatSendMessageMapping;
import com.dade.module.notice.mapping.NoticeGetAllMapping;
import com.dade.module.notice.mapping.NoticeLookOverMapping;
import com.dade.module.user.mapping.LoginMapping;

/**
 * 路由映射枚举
 * Created by dade on 2016/1/10
 */
public enum RouterMappingEnum
{
    LOGIN                   (LoginMapping.class, "socket登录"),

    NOTICE_LOOK_OVER        (NoticeLookOverMapping.class, "消息提醒-查看指定通知"),
    NOTICE_GET_ALL          (NoticeGetAllMapping.class, "消息提醒-获得所有通知"),


    CHAT_GET_USER_LIST          (ChatGetUserListMapping.class, "聊天系统-获取聊天用户列表"),
    CHAT_GET_USER_MESSAGE_LIST  (ChatGetUserMessageMapping.class, "聊天系统-获得消息list"),
    CHAT_SEND_MESSAGE           (ChatSendMessageMapping.class, "聊天系统-户端发送消息"),

    CHAT_ADD_USER_TO_LIST       (ChatAddUserToListMapping.class,"聊天系统-客户端添加一个聊天好友到聊天用户列表"),

    ;

    RouterMappingEnum (Class<? extends MessageBasicMapping> mappingClass, String info)
    {
        MessageRouterFactory.getInstance().registerMapping(name(), mappingClass);
    }

    /**
     * 初始化方法
     */
    public static void init ()
    {
        LogUtil.info("初始化 RouterMappingEnum 完毕");
    }
}
