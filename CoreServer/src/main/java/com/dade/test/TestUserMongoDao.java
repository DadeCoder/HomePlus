package com.dade.test;

import com.dade.commons.mongodb.BasicMongoDao;
import org.springframework.stereotype.Component;

/**
 * Created by Dade on 2016/12/24.
 */
@Component
public class TestUserMongoDao extends BasicMongoDao<TestUser> {
    @Override
    public Class<TestUser> getReturnClass() {
        return TestUser.class;
    }
}
