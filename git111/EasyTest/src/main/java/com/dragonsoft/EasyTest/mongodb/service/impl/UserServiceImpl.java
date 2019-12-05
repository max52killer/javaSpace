package com.dragonsoft.EasyTest.mongodb.service.impl;

import com.dragonsoft.EasyTest.mongodb.dao.UserDao;
import com.dragonsoft.EasyTest.mongodb.po.TUser;
import com.dragonsoft.EasyTest.mongodb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: EasyTest
 * @description: 用户信息实现类
 * @author: songzm
 * @create: 2019-12-04 13:25
 **/
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;


    @Override
    public TUser save(TUser user) {
        return userDao.save(user);
    }

    @Override
    public List<TUser> queryList() {
        return userDao.findAll();
    }
}
