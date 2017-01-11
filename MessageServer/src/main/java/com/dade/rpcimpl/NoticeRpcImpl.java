package com.dade.rpcimpl;


import com.dade.module.notice.NoticeService;
import com.dade.rpcapi.api.NoticeRpc;
import com.dade.rpcapi.enums.NoticeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 业务提醒封装服务提供者
 * Created by dade on 2016/1/10
 */
@Service
public class NoticeRpcImpl implements NoticeRpc
{
    @Autowired
    NoticeService noticeService;

    @Override
    public void notice(String userId, NoticeEnum noticeType, String text)
    {
        noticeService.notice(userId, noticeType, text);
    }
}
