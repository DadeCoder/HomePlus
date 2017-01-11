package com.dade.rpcapi.api;


import com.dade.rpcapi.dto.EmailNoticeDto;

/**
 * 新邮件提醒
 * Created by Dade on 2017/1/10.
 */
public interface NewEmailNoticeRpc {

    /**
     * 通知
     * 
     * @param msgBody
     */
    void notice(EmailNoticeDto msgBody);

}
