package com.dade.module.notice;


import com.dade.module.SendFacotry;
import com.dade.module.notice.dto.NoticeDto;
import com.dade.module.notice.dto.NoticeListDto;
import com.dade.module.user.UserService;
import com.dade.rpcapi.enums.NoticeEnum;
import com.dade.rpcapi.exception.MessageServerDubboException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * 消息通知服务类
 * Created by dade on 2016/1/10
 */
@Component
public class NoticeService
{

    @Autowired
    UserService userService;

    @Autowired
    NoticeMongoDao noticeMongoDao;

    /**
     * 通知
     * @param userId        通知用户
     * @param noticeType    通知类型
     * @param text          通知文本
     */
    public void notice (String userId, NoticeEnum noticeType, String text)
    {
        if (userId == null || "".equals(userId))
            throw new MessageServerDubboException("业务提醒通知用户ID错误 userId: "+userId + " type: " + noticeType);

        if (noticeType == null || noticeType == NoticeEnum.ERROR)
            throw new MessageServerDubboException("业务提醒通知类型错误 userId: "+userId + " type: " + noticeType);

        Notice notice = new Notice();
        notice.setUserId(userId);
        notice.setNoticeType(noticeType.code());
        notice.setText(text);
        notice.setCreateDate(new Date());
        noticeMongoDao.insert(notice);

        // 在线则发送客户端
        if (userService.isOnline(userId))
            SendFacotry.sendUser(userId, new NoticeDto(notice.getId(), noticeType.code(), text));
    }

    /**
     * 移除通知
     * @param ids   id集合
     */
    public void removeNotice (List<String> ids)
    {
        noticeMongoDao.remove(ids);
    }

    /**
     * db获得通知并发送 (上线处理)
     * @param userId   用户ID
     */
    public void getAll (String userId)
    {
        List<Notice> notices = noticeMongoDao.findByUserId(userId);
        NoticeListDto dto = new NoticeListDto(notices);
        SendFacotry.sendUser(userId, dto);
    }
}
