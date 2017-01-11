package com.dade.module;

/**
 * 错误码
 * Created by dade on 2016/1/10
 */
public enum MessageErrorCode
{
    SUCCESS             (0, "成功"),
    OTHER_ERROR         (1, "其他错误"),

    REPETITION_LOGIN    (10001, "重复登录"),
    LOGIN_NO_USER       (10002, "不存在的用户"),
    TOKEN_ERROR         (10003, "token error"),
    ;

    private final int code;
    private final String info;

    MessageErrorCode (int code, String info)
    {
        this.code = code;
        this.info = info;
    }

    public int code ()
    {
        return code;
    }

    public String info ()
    {
        return info;
    }

    /**
     * 获得枚举
     * @param code
     * @return
     */
    public static MessageErrorCode getEnum (int code)
    {
        for (MessageErrorCode errorCode : MessageErrorCode.values())
        {
            if (errorCode.code() == code)
                return errorCode;
        }

        return OTHER_ERROR;
    }
}
