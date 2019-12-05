package com.dragonsoft.EasyTest.mongodb.base;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;

/**
 * 返回结果标识
 */
public class Result {
    public Result(){}

    public Result(String code, String message) {
        this.code = code;
        this.message = message;
    }
    public Result(String code, String message, Object object) {
        this.code = code;
        this.message = message;
        this.object = object;
    }
    //返回码
    private String code;
    //返回消息
    private String message;
    //返回内容
    private Object object;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
