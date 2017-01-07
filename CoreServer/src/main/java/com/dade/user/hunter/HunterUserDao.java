package com.dade.user.hunter;

import com.dade.commons.mongodb.BasicMongoDao;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

/**
 * Created by Dade on 2017/1/7.
 */
@Component
public class HunterUserDao extends BasicMongoDao<HunterUser> {
    @Override
    public Class<HunterUser> getReturnClass() {
        return HunterUser.class;
    }

    public HunterUser findByPhoneNumber(String phoneNumber){
        Criteria criteria = Criteria.where(HunterUser.FIELD_PHONE_NUMBER).is(phoneNumber);
        return mongoOperations.findOne(Query.query(criteria), HunterUser.class);

    }

}
