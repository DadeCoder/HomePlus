package com.dade.rpcapi.enums;

/**
 * 消息类型
 * Created by Dade on 2017/1/10.
 */
public enum ChatMessageEnum
{
    ERROR       (0, "错误类型"),
    CONTENT     (1, "文本内容"),
    IMAGE       (2, "图片"),
    FILE        (3, "文件"),
    POSITION    (4, "职位"),
    CANDIDATE   (5, "候选人"),
    ;

    private final int code;

    ChatMessageEnum (int code, String info)
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
    public static ChatMessageEnum getEnum (int code)
    {
        for (ChatMessageEnum enumType : values())
        {
            if (enumType.code() == code)
                return enumType;
        }

        return ERROR;
    }
}
