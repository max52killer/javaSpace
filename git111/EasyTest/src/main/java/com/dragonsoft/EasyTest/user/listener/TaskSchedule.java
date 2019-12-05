package com.dragonsoft.EasyTest.user.listener;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class TaskSchedule implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println("任务调度任务。。。。");
    }
}
