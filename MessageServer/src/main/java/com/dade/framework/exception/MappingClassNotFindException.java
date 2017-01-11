package com.dade.framework.exception;

/**
 * 接收映射class找不到异常
 * Created by dade on 2016/1/10
 */
public final class MappingClassNotFindException extends RuntimeException
{
    private static final long serialVersionUID = 1L;

    public MappingClassNotFindException()
    {
        super();
    }

    public MappingClassNotFindException(String message)
    {
        super(message);
    }

    public MappingClassNotFindException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public MappingClassNotFindException(Throwable cause)
    {
        super(cause);
    }
}
