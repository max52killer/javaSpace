package com.dragonsoft.EasyTest.mongodb;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

//mongodb 连接数据库工具类
public class MongoDBUtil {

    //不通过认证获取连接数据库对象
    public MongoDatabase getConnect(){
        //连接到 mongodb 服务
        MongoClient mongoClient = new MongoClient("localhost", 27017);

        //连接到数据库
        MongoDatabase mongoDatabase = mongoClient.getDatabase("javaTest");
        System.out.println("链接javaTest数据库成功");
        //返回连接数据库对象
        return mongoDatabase;
    }

    //需要密码认证方式连接
    public MongoDatabase getConnect2(){
        List<ServerAddress> adds = new ArrayList<>();
        //ServerAddress()两个参数分别为 服务器地址 和 端口
        ServerAddress serverAddress = new ServerAddress("20.20.30.72", 27017);
        adds.add(serverAddress);

        List<MongoCredential> credentials = new ArrayList<>();
        //MongoCredential.createScramSha1Credential()三个参数分别为 用户名 数据库名称 密码
        MongoCredential mongoCredential = MongoCredential.createScramSha1Credential("admin", "admin", "dragon".toCharArray());
        credentials.add(mongoCredential);

        //通过连接认证获取MongoDB连接
        MongoClient mongoClient = new MongoClient(adds, credentials);

        //连接到数据库
        MongoDatabase mongoDatabase = mongoClient.getDatabase("test");

        //返回连接数据库对象
        return mongoDatabase;
    }
}
