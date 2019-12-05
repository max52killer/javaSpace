package com.dragonsoft.EasyTest.mongodb.service;

import com.dragonsoft.EasyTest.mongodb.base.Result;
import com.dragonsoft.EasyTest.mongodb.po.Test;
import com.mongodb.client.result.DeleteResult;

import java.util.List;

public interface TestService {
    List<Test> queryAll();
    Test save(Test test);
    Test update(Test test);
    Result deleteById(String id);
    List<Test> findByTitle(String title,String value);
}
