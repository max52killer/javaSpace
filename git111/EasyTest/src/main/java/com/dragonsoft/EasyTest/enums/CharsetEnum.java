package com.dragonsoft.EasyTest.enums;

/**
 * 字符集表码
 */
public enum CharsetEnum implements IconEnum {
    UTF8("utf-8", "utf-8格式"), GBK("GBK", "国标"), GB2312("GB2312", "GB2312");

    private String code;
    private String desc;

    CharsetEnum(String code, String desc) {
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
