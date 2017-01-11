package com.dade.framework.exception;

/**
 * 发送包没找到发送类型异常
 * Created by dade on 2016/1/10
 */
public class SendMappingTypeNotFindException extends RuntimeException
{
    private static final long serialVersionUID = 1L;

    public SendMappingTypeNotFindException()
    {
        super();
    }

    public SendMappingTypeNotFindException(String message)
    {
        super(message);
    }

    public SendMappingTypeNotFindException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public SendMappingTypeNotFindException(Throwable cause)
    {
        super(cause);
    }
}
