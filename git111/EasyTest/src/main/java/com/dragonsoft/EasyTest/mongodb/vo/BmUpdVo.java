package com.dragonsoft.EasyTest.mongodb.vo;

import com.dragonsoft.EasyTest.mongodb.po.TSysCode;

import java.util.List;

/**
 * @program: EasyTest
 * @description: 表码更新VO
 * @author: songzm
 * @create: 2019-12-09 09:22
 **/
public class BmUpdVo {
    private String type;
    private List<TSysCode> sysCodes;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<TSysCode> getSysCodes() {
        return sysCodes;
    }

    public void setSysCodes(List<TSysCode> sysCodes) {
        this.sysCodes = sysCodes;
    }
}
