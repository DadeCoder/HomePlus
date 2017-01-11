package com.dade.rpcapi.api;


import com.dade.rpcapi.enums.NoticeEnum;

/**
 * 业务提醒通知rpc
 * Created by Dade on 2017/1/10.
 */
public interface NoticeRpc
{
    /**
     * 消息通知
     * @param userId        通知用户
     * @param noticeType    通知类型
     * @param text          通知文本
     */
    void notice(String userId, NoticeEnum noticeType, String text);
}