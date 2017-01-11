package com.dade.module.notice.mapping;


import com.dade.MessageServerApplication;
import com.dade.framework.MappingResponse;
import com.dade.module.MessageBasicMapping;
import com.dade.module.notice.NoticeService;
import com.dade.network.DefaultEmptyDto;

/**
 * 获得所有消息提醒
 * Created by dade on 2016/1/10
 */
public class NoticeGetAllMapping extends MessageBasicMapping<DefaultEmptyDto>
{
    @Override
    public Class<DefaultEmptyDto> getDtoClass() {
        return DefaultEmptyDto.class;
    }

    @Override
    public MappingResponse execute()
    {
        String userId = getClient().getUserId();

        NoticeService noticeService = MessageServerApplication.getContext().getBean(NoticeService.class);
        noticeService.getAll(userId);
        return ok();
    }
}