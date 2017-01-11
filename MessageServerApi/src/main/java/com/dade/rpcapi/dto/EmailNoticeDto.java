package com.dade.rpcapi.dto;

import java.io.Serializable;

/**
 * 接收到新邮件的通知
 * Created by Dade on 2017/1/10.
 */
public class EmailNoticeDto implements Serializable {

    private String userId; // 平台用户ID
    private int newNum; // 新邮件数量

    public EmailNoticeDto() {
        super();
    }

    public EmailNoticeDto(String userId, int newNum) {
        super();
        this.userId = userId;
        this.newNum = newNum;
    }

    @Override
    public String toString() {
        return "EmailNoticeDto [userId=" + userId + ", newNum=" + newNum + "]";
    }

    /// --------------
    /// --------------

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getNewNum() {
        return newNum;
    }

    public void setNewNum(int newNum) {
        this.newNum = newNum;
    }

}
