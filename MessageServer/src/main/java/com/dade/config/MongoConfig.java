package com.dade.config;

import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

/**
 * mongo配置文件
 * Created by dade on 2016/1/10
 */
@Configuration
public class MongoConfig
{
    @Value( "${mongodb.host}" )
    private String host;

    @Value( "${mongodb.port}" )
    private String port;

    @Value( "${mongodb.database}" )
    private String dbName;

    @Bean
    public MongoDbFactory mongoDbFactory() throws Exception {

        return new SimpleMongoDbFactory(new MongoClient(host, Integer.valueOf(port)), dbName);
    }

    @Bean
    public MongoTemplate mongoTemplate() throws Exception
    {
        return new MongoTemplate(mongoDbFactory());
    }

    @Bean
    public MongoOperations mongoOperations() throws Exception {
        return mongoTemplate();
    }

}
