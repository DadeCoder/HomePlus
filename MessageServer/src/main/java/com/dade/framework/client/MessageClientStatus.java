package com.dade.framework.client;

/**
 * 连接状态
 * Created by dade on 2016/1/10
 */
public enum MessageClientStatus
{
    WAIT_AUTH   ("等待授权"),
    LOGINED     ("已登录"),
    CLOSE       ("连接关闭")
    ;

    MessageClientStatus (String info)
    {

    }

}
