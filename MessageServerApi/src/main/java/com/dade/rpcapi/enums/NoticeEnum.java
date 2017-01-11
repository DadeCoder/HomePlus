package com.dade.rpcapi.enums;

/**
 * 消息通知枚举
 * Created by Dade on 2017/1/10.
 */
public enum NoticeEnum
{
    ERROR       (0, "错误类型"),
    NEW_MAIL    (1, "新邮件通知"),

    ;

    private final int code;

    NoticeEnum (int code, String info)
    {
        this.code = code;
    }

    public int code()
    {
        return code;
    }

    /**
     * 通过code获取枚举
     * @param code
     * @return
     */
    public static NoticeEnum getEnum (int code)
    {
        for (NoticeEnum enumType : values())
        {
            if (enumType.code() == code)
                return enumType;
        }

        return ERROR;
    }
}
