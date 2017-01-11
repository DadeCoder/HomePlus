package com.dade.rpcapi.api.impl;


import com.dade.rpcapi.api.NewEmailNoticeRpc;
import com.dade.rpcapi.dto.EmailNoticeDto;

public class NewEmailNoticeRpcImpl implements NewEmailNoticeRpc {

    @Override
    public void notice(EmailNoticeDto msgBody) {
        System.out.printf("用户[%s]接收到新邮件，邮件数[%d]", msgBody.getUserId(), msgBody.getNewNum());
    }

}
