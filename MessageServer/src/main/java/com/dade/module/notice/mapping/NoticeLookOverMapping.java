package com.dade.module.notice.mapping;


import com.dade.MessageServerApplication;
import com.dade.framework.MappingResponse;
import com.dade.module.MessageBasicMapping;
import com.dade.module.notice.NoticeService;
import com.dade.network.DefaultStringListDto;

/**
 * 移除通知集合
 * Created by dade on 2016/1/10
 */
public class NoticeLookOverMapping extends MessageBasicMapping<DefaultStringListDto>
{
    @Override
    public Class<DefaultStringListDto> getDtoClass() {
        return DefaultStringListDto.class;
    }

    @Override
    public MappingResponse execute()
    {
        NoticeService noticeService = MessageServerApplication.getContext().getBean(NoticeService.class);
        noticeService.removeNotice(getDto().getList());
        return ok();
    }
}
