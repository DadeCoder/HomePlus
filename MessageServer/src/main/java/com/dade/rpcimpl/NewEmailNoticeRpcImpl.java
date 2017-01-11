package com.dade.rpcimpl;


import com.dade.common.LogUtil;
import com.dade.rpcapi.api.NewEmailNoticeRpc;
import com.dade.rpcapi.dto.EmailNoticeDto;
import org.springframework.stereotype.Service;

/**
 * Created by dade on 2016/1/10
 */
@Service
public class NewEmailNoticeRpcImpl implements NewEmailNoticeRpc {

    @Override
    public void notice(EmailNoticeDto msgBody) {
        LogUtil.info("------------");
        LogUtil.info(String.format("用户[%s]接收到新邮件，邮件数[%d]", msgBody.getUserId(), msgBody.getNewNum()));
    }

}
