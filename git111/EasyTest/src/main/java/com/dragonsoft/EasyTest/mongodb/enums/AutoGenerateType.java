package com.dragonsoft.EasyTest.mongodb.enums;

import com.dragonsoft.EasyTest.enums.IconEnum;

/**
 * 自动生成数据枚举
 */
public enum AutoGenerateType  {
    UUID("UUID"),
    RANDOM_NUM("RANDOM_NUM"),
    CUSTOM("CUSTOM");

    private String code;

    AutoGenerateType(String code) {
        this.code = code;
    }


    public String getCode() {
        return this.code;
    }
}
