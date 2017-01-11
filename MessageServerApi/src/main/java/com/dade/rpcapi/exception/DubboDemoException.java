package com.dade.rpcapi.exception;

/**
 * dubbo demo 异常
 * Created by Dade on 2017/1/10.
 */
public class DubboDemoException extends RuntimeException
{
    private static final long serialVersionUID = 1L;

    public DubboDemoException()
    {
        super();
    }

    public DubboDemoException(String message)
    {
        super(message);
    }

    public DubboDemoException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public DubboDemoException(Throwable cause)
    {
        super(cause);
    }
}
