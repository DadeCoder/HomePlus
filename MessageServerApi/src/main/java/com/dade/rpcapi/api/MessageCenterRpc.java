package com.dade.rpcapi.api;

/**
 * 消息中心服务对外API
 * Created by Dade on 2017/1/10.
 */
public interface MessageCenterRpc
{
    /**
     * 强制下线
     * @param userId
     */
    void logout (String userId);
}
