package com.dragonsoft.EasyTest.enums;

/**
 * 字符集表码
 */
public enum DateEnum implements IconEnum {
    NUBMER14("yyyyMMddHHmmss", "14位时间格式"),
    NUBMER19("yyyyMMddHHmmssSSSSS", "19位时间格式"),
    NUMBER_18("yyyy-MM-dd HH:mm:ss", "以-和：分割的时间格式"),
    NUBMER_10("yyyy-MM-dd","以-分割的10未月份时间格式");

    private String code;
    private String desc;

    DateEnum(String code, String desc) {
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
