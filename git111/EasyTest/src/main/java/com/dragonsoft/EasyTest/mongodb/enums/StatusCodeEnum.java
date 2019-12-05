package com.dragonsoft.EasyTest.mongodb.enums;

import com.dragonsoft.EasyTest.enums.IconEnum;

/**
 * 字符集表码
 */
public enum StatusCodeEnum implements IconEnum {
    SUCCESS("200", "成功"),
    ERROR("500", "失败"),
    WARNING("300", "警告");

    private String code;
    private String desc;

    StatusCodeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getDesc() {
        return this.desc;
    }
}
