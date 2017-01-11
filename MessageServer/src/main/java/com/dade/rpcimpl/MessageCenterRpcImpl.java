package com.dade.rpcimpl;


import com.dade.module.user.UserService;
import com.dade.rpcapi.api.MessageCenterRpc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 消息中心dubbo服务提供者
 * Created by dade on 2016/1/10
 */
@Service
public class MessageCenterRpcImpl implements MessageCenterRpc
{

    @Autowired
    UserService userService;

    @Override
    public void logout(String userId)
    {
        userService.logout(userId);
    }
}
