package com.dragonsoft.EasyTest.mongodb.service.impl;

import com.dragonsoft.EasyTest.mongodb.base.Result;
import com.dragonsoft.EasyTest.mongodb.dao.TestDao;
import com.dragonsoft.EasyTest.mongodb.enums.StatusCodeEnum;
import com.dragonsoft.EasyTest.mongodb.po.Test;
import com.dragonsoft.EasyTest.mongodb.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TestServiceImpl implements TestService {
    @Autowired
    private TestDao testDao;
    @Override
    public List<Test> queryAll() {
        List<Test> list=testDao.findAll();
        System.out.println(list);
        return list;
    }

    @Override
    public Test save(Test test) {
        //自动生成UUID
        test.set_id(getUUID());
        return testDao.save(test);
    }

    @Override
    public Test update(Test test) {
        return testDao.update(test);
    }

    @Override
    public Result deleteById(String id) {
        return testDao.deleteById(id);
    }

    @Override
    public List<Test> findByTitle(String title, String value) {
        return testDao.find(title,value);
    }

    @Override
    public Result deleteByField(String name, String var) {
        testDao.deleteByField(name,var);

        return new Result(StatusCodeEnum.SUCCESS.getCode(),"删除成功！");
    }

    public String getUUID(){
        return String.valueOf(UUID.randomUUID()).replace("-","");
    }
}
