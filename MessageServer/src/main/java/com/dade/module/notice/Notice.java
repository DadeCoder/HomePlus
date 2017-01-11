package com.dade.module.notice;

import com.dade.common.mongodb.MongoObject;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * 消息提醒
 * Created by dade on 2016/1/10
 */
@Document(collection="notice")
public class Notice implements MongoObject
{
    @Id
    private String id;
    private String userId;      // 用户ID
    private Integer noticeType; // 提醒类型, 参见NoticeEnum
    private String text;        // 提醒内容
    private Date createDate;    // 创建时间、


    public static final String FIELD_USER_ID = "userId";


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getNoticeType() {
        return noticeType;
    }

    public void setNoticeType(Integer noticeType) {
        this.noticeType = noticeType;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "Notice{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", noticeType=" + noticeType +
                ", text='" + text + '\'' +
                ", createDate=" + createDate +
                '}';
    }
}
