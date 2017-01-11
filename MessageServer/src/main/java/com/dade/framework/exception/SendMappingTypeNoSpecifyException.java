package com.dade.framework.exception;

/**
 * 发送包没有指定发送类型异常
 * 在同个发送DTO映射多个发送类型才存在的异常, DTO与类型一一对应的情况就不需要指定类型
 * Created by dade on 2016/1/10
 */
public class SendMappingTypeNoSpecifyException extends RuntimeException
{
    private static final long serialVersionUID = 1L;

    public SendMappingTypeNoSpecifyException()
    {
        super();
    }

    public SendMappingTypeNoSpecifyException(String message)
    {
        super(message);
    }

    public SendMappingTypeNoSpecifyException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public SendMappingTypeNoSpecifyException(Throwable cause)
    {
        super(cause);
    }
}
