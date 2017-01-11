package com.dade.rpcapi.enums;

/**
 * 消息内容 附加对象Key枚举
 * Created by Dade on 2017/1/10.
 */
public enum MessageContentAttachKeyEnum
{
    MIN_REVENUE ("minRevenue", "最小收益"),
    MAX_REVENUE ("maxRevenue", "最大收益"),
    MIN_SALARY  ("minSalary", "最小年薪"),
    MAX_SALARY  ("maxSalary", "最小年薪"),

    INDUCTION_DATE ("inductionDate","入职时间"),
    EXPIRY_DATE    ("expiryDate","保证期到期时间"),

    ;

    private final String code;
    private final String info;

    MessageContentAttachKeyEnum (String code, String info)
    {
        this.code = code;
        this.info = info;
    }

    public String code() {
        return code;
    }

    public String info() {
        return info;
    }
}
