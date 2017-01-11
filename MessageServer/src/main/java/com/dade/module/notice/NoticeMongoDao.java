package com.dade.module.notice;

import com.dade.common.mongodb.BasicMongoDao;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 业务提醒mongo操作类
 * Created by dade on 2016/1/10
 */
@Component
public class NoticeMongoDao extends BasicMongoDao<Notice>
{

    @Override
    public Class<Notice> getReturnClass()
    {
        return Notice.class;
    }

    public List<Notice> findByUserId(String userId) {
        Criteria criteria = Criteria.
                where(Notice.FIELD_USER_ID).
                is(userId);

        return mongoOperations.find(Query.query(criteria), getReturnClass());
    }
}
