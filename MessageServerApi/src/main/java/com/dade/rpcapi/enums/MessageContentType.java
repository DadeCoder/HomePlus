package com.dade.rpcapi.enums;

/**
 * 消息内容种类
 * Created by Dade on 2017/1/10.
 */
public enum MessageContentType
{
    TEXT                            (1, "普通文本文本内容"),
    OPERATE_MESSAGE                 (2, "运营消息"),

    PROCESS_ACCOUNT_COMPANY         (3, "通知-客户已审核"),
    SHARE_WECHAT_POSITION_CANDIDATE (4, "通知-分享微信的职位有应聘者"),
    AUTH_NEW_ACCOUNT_COMPANY        (5, "通知-授权新客户"),
    RECOMMEND_POSITION_TO_USER      (6, "职位匹配顾问-推荐职位给顾问"),

    RECOMMEND_NEW_RECOMMENDER       (11, "推荐流-推荐了候选人通知"),
    RECOMMEND_OPERATION_RECEIVE     (12, "推荐流-接受候选人通知"),
    RECOMMEND_OPERATION_RECOMMEND   (13, "推荐流-推荐到企业通知"),
    RECOMMEND_OPERATION_INTERVIEW   (14, "推荐流-安排面试通知"),
    RECOMMEND_OPERATION_OFFER       (15, "推荐流-发放OFFER通知"),
    RECOMMEND_OPERATION_INDUCTION   (16, "推荐流-入职通知"),
    RECOMMEND_OPERATION_REACTIVATE  (17, "推荐流-重新激活"),
    ;

    private final int code;
    private final String info;

    MessageContentType(int code, String info)
    {
        this.code = code;
        this.info = info;
    }

    public int code()
    {
        return code;
    }

    public String info ()
    {
        return info;
    }

    /**
     * 通过coded获得枚举
     * @param code  代码
     * @return      枚举
     */
    public static MessageContentType getEnum(int code)
    {
        for (MessageContentType enumType : values())
            if (enumType.code() == code)
                return enumType;

        return null;
    }
}
