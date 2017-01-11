package com.dade.module.chat;

import com.dade.common.mongodb.BasicMongoDao;
import com.dade.module.chat.model.ChatMessage;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 聊天消息mongo操作类
 * Created by dade on 2016/1/10
 */
@Component
public class ChatMessageMongoDao extends BasicMongoDao<ChatMessage>
{

    private final static int MAX_LIMIT_COUNT = 1000;    // 最大limit数量限制

    @Override
    public Class<ChatMessage> getReturnClass()
    {
        return ChatMessage.class;
    }

    /**
     * 通过拥有者ID获得聊天消息列表
     * @param ownerUserId   拥有者ID
     * @param chatUserId    聊天用户ID
     * @param messageId     倒序起始消息ID, 无时为最新count条记录
     * @param count         数量
     * @return
     */
    public List<ChatMessage> findListByOwnerId(String ownerUserId, String chatUserId, String messageId, int count)
    {
        if (ownerUserId == null || "".equals(ownerUserId) || count <= 0 || count > MAX_LIMIT_COUNT)
            return new ArrayList<>();

        Criteria criteria = Criteria
                .where(ChatMessage.FIELD_OWNER_USER_ID).is(ownerUserId)
                .and(ChatMessage.FIELD_CHAT_USER_ID).is(chatUserId);

        if (messageId != null && !"".equals(messageId))
            criteria.and(ChatMessage.FIELD_ID).lte(messageId);

        Sort sort = new Sort(Sort.Direction.DESC, ChatMessage.FIELD_ID);
        Query query = new Query(criteria).with(sort).limit(count);

        return mongoOperations.find(query, getReturnClass());
    }

    /**
     * 更新消息已读状态
     * @param ownerUserId   拥有者ID
     * @param chatUserId    聊天用户ID
     */
    public void updateReadStatus (String ownerUserId, String chatUserId)
    {
        Query query = new Query();
        query.addCriteria(Criteria
                .where(ChatMessage.FIELD_OWNER_USER_ID).is(ownerUserId)
                .and(ChatMessage.FIELD_CHAT_USER_ID).is(chatUserId)
                .and(ChatMessage.FIELD_READ).is(false));

        Update update = new Update();
        update.set(ChatMessage.FIELD_READ, true);
        update.set(ChatMessage.FIELD_READ_DATE, new Date());

        mongoOperations.updateMulti(query, update, getReturnClass());
    }
}