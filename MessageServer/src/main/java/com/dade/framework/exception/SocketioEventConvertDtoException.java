package com.dade.framework.exception;

/**
 * socket io事件转换Dto异常
 * Created by dade on 2016/1/10
 */
public class SocketioEventConvertDtoException extends RuntimeException
{

    private static final long serialVersionUID = 1L;

    public SocketioEventConvertDtoException()
    {
        super();
    }

    public SocketioEventConvertDtoException(String message)
    {
        super(message);
    }

    public SocketioEventConvertDtoException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public SocketioEventConvertDtoException(Throwable cause)
    {
        super(cause);
    }
}
