package com.dade.framework.exception;

/**
 * 接收映射实例化失败异常
 * Created by dade on 2016/1/10
 */
public class MappingObjectCreateException extends RuntimeException
{
    private static final long serialVersionUID = 1L;

    public MappingObjectCreateException()
    {
        super();
    }

    public MappingObjectCreateException(String message)
    {
        super(message);
    }

    public MappingObjectCreateException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public MappingObjectCreateException(Throwable cause)
    {
        super(cause);
    }
}
