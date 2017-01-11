package com.dade.module.chat;

import com.dade.common.mongodb.BasicMongoDao;
import com.dade.module.chat.model.UserChatObject;
import org.springframework.stereotype.Component;

/**
 * 用户聊天系统信息Mongo操作类
 * Created by dade on 2016/1/10
 */
@Component
public class UserChatMongoDao extends BasicMongoDao<UserChatObject>
{
    @Override
    public Class<UserChatObject> getReturnClass()
    {
        return UserChatObject.class;
    }
}
