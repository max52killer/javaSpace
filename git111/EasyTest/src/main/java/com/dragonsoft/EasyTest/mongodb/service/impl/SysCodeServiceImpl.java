package com.dragonsoft.EasyTest.mongodb.service.impl;

import com.dragonsoft.EasyTest.mongodb.base.Result;
import com.dragonsoft.EasyTest.mongodb.dao.SysCodeDao;
import com.dragonsoft.EasyTest.mongodb.po.TSysCode;
import com.dragonsoft.EasyTest.mongodb.service.SysCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: EasyTest
 * @description: 表码service实现类
 * @author: songzm
 * @create: 2019-12-04 16:37
 **/
@Service
public class SysCodeServiceImpl implements SysCodeService {
    @Autowired
    private SysCodeDao sysCodeDao;

    @Override
    public List<TSysCode> getByType(String type) {
        return sysCodeDao.find("type",type);
    }

    @Override
    public TSysCode save(TSysCode sysCode) {
        return sysCodeDao.save(sysCode);
    }

    @Override
    public List<TSysCode> queryList() {
        return sysCodeDao.findAll();
    }

    @Override
    public void deleteByField(String title, String vals) {
        sysCodeDao.deleteByField(title,vals);
    }
}
