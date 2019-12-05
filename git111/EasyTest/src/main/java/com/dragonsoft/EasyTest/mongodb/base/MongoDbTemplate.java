package com.dragonsoft.EasyTest.mongodb.base;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MongoDbTemplate {

    public  MongoClient mongoClient;
    public  MongoDatabase mongoDatabase;

    @Autowired
    private MongodbConfig config;

    public void init() {
        //连接到 mongodb 服务
        mongoClient = new MongoClient(config.getHost(), config.getPort());
        //连接到数据库
        mongoDatabase = mongoClient.getDatabase(config.getDatabase());
        System.out.println("链接javaTest数据库成功");
    }
}
