package com.dade.rpcapi.exception;

/**
 * dubbo 消息服 异常
 * Created by Dade on 2017/1/10.
 */
public class MessageServerDubboException extends RuntimeException
{
    private static final long serialVersionUID = 1L;

    public MessageServerDubboException()
    {
        super();
    }

    public MessageServerDubboException(String message)
    {
        super(message);
    }

    public MessageServerDubboException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public MessageServerDubboException(Throwable cause)
    {
        super(cause);
    }
}