package com.dragonsoft.EasyTest.mongodb.except;

/**
 * 自定义异常
 */
public class MongoDbException extends Exception {
    public MongoDbException(){}
    public MongoDbException(String str){
        super(str);
    }
}
