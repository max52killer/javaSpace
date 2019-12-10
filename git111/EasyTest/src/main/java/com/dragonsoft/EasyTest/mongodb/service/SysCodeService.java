package com.dragonsoft.EasyTest.mongodb.service;

import com.dragonsoft.EasyTest.mongodb.base.Result;
import com.dragonsoft.EasyTest.mongodb.po.TSysCode;

import java.util.List;

public interface SysCodeService {
    List<TSysCode> getByType(String type);

    TSysCode save(TSysCode sysCode);
    List<TSysCode> queryList();

    void deleteByField(String title,String vals);
}
