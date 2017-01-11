package com.dade.module;


import com.dade.network.IDto;

/**
 * dto结果包装器
 * Created by dade on 2016/1/10
 */
public final class DtoResultWrapper<T extends IDto>
{
    private final MessageErrorCode errorCode;   // 错误码
    private final T result;                     // dto结果

    public DtoResultWrapper(MessageErrorCode errorCode) {
        this(errorCode, null);
    }

    public DtoResultWrapper(T result) {

        this(MessageErrorCode.SUCCESS, result);
    }

    private DtoResultWrapper(MessageErrorCode errorCode, T result) {
        this.errorCode = errorCode;
        this.result = result;
    }

    public MessageErrorCode getErrorCode() {
        return errorCode;
    }

    public T getResult() {
        return result;
    }
}
