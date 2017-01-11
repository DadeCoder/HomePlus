package com.dade.framework.exception;

/**
 * 映射返回对象null 异常
 * Created by dade on 2016/1/10
 */
public class MappingResponseNullException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public MappingResponseNullException()
    {
        super();
    }

    public MappingResponseNullException(String message)
    {
        super(message);
    }

    public MappingResponseNullException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public MappingResponseNullException(Throwable cause)
    {
        super(cause);
    }
}
