package com.dragonsoft.EasyTest.thread;

import java.util.ArrayList;
import java.util.List;

public class TestThread {
    public static void main(String args[]) {
//        RunnableDemo R1 = new RunnableDemo( "Thread-1");
//        R1.start();
//
//        RunnableDemo R2 = new RunnableDemo( "Thread-2");
//        R2.start();

        List<String> list=new ArrayList<>();
        for(int i=1;i<=100;i++){
            list.add("string-"+i);
        }
        List<RunnableDemo> demos=new ArrayList<>();
        for(int j=0;j<list.size();j++){
            if(j%10==0){
                RunnableDemo demo=new RunnableDemo("thread-"+j);
                demos.add(demo);
            }
        }
        for (RunnableDemo demo:demos){
            demo.start();
        }

    }
}
