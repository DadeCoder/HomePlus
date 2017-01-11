package com.dade.module.notice.dto;


import com.dade.network.IDto;

/**
 * 新邮件通知 推送Dto
 * Created by dade on 2016/1/10
 */
public class NoticeDto implements IDto
{
    private String id;
    private Integer noticeType; // 通知类型
    private String text;        // 通知文本

    public NoticeDto(String id, Integer noticeType, String text) {
        this.id = id;
        this.noticeType = noticeType;
        this.text = text;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
}
