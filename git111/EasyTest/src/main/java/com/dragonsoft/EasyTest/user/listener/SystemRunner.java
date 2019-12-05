package com.dragonsoft.EasyTest.user.listener;

import com.dragonsoft.EasyTest.mongodb.base.MongoDbTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class SystemRunner implements ApplicationRunner {

    @Autowired
    private MongoDbTemplate mongoDbTemplate;


    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("系统启动调用。。。。");
        mongoDbTemplate.init();
    }
}
