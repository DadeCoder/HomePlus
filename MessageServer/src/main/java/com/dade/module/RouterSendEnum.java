package com.dade.module;


import com.dade.common.LogUtil;
import com.dade.framework.MessageRouterFactory;
import com.dade.module.chat.dto.ChatMessageDto;
import com.dade.module.notice.dto.NoticeDto;
import com.dade.module.notice.dto.NoticeListDto;
import com.dade.network.IDto;

/**
 * 路由发送包映射枚举
 * Created by dade on 2016/1/10
 */
public enum RouterSendEnum
{
    CHAT_MESSAGE    (ChatMessageDto.class, "聊天系统-收到消息"),

    NOTICE          (NoticeDto.class, "业务提醒-通知"),
    NOTICE_ALL      (NoticeListDto.class, "务提醒-全部通知"),

    ;

    RouterSendEnum (Class<? extends IDto> dtoClass, String info)
    {
        MessageRouterFactory.getInstance().registerSendable(name(), dtoClass);
    }

    /**
     * 初始化方法
     */
    public static void init ()
    {
        LogUtil.info("初始化 RouterSendEnum 完毕");
    }
}
