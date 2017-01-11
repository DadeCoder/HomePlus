package com.dade.module.notice.dto;


import com.dade.module.notice.Notice;
import com.dade.network.IDto;

import java.util.ArrayList;
import java.util.List;

/**
 * 通知集合
 * Created by dade on 2016/1/10
 */
public class NoticeListDto implements IDto
{
    private List<NoticeDto> notices;

    public NoticeListDto(List<Notice> notices)
    {
        this.notices = new ArrayList<>();
        if (notices == null || notices.isEmpty())
           return;

        for (Notice notice : notices)
            this.notices.add(new NoticeDto(notice.getId(), notice.getNoticeType(), notice.getText()));
    }

    public List<NoticeDto> getNotices() {
        return notices;
    }

    public void setNotices(List<NoticeDto> notices) {
        this.notices = notices;
    }
}
