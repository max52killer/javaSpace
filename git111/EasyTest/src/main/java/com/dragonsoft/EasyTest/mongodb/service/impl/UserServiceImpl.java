package com.dragonsoft.EasyTest.mongodb.service.impl;

import com.dragonsoft.EasyTest.mongodb.base.Result;
import com.dragonsoft.EasyTest.mongodb.base.TokenProccessor;
import com.dragonsoft.EasyTest.mongodb.dao.UserDao;
import com.dragonsoft.EasyTest.mongodb.enums.StatusCodeEnum;
import com.dragonsoft.EasyTest.mongodb.po.TUser;
import com.dragonsoft.EasyTest.mongodb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    /**
     * 全局存储当前登陆用户
     */
    private TUser currentUser;
    @Override
    public TUser save(TUser user) {
        return userDao.save(user);
    }

    @Override
    public List<TUser> queryList() {
        return userDao.findAll();
    }

    @Override
    public Result login(TUser user) {
        List<String> params=new ArrayList<>();
        params.add("username");
        params.add("password");
        List<String> vals=new ArrayList<>();
        vals.add(user.getUsername());
        vals.add(user.getPassword());
        List<TUser> users=userDao.find(params,vals);
        List<TUser> userList=userDao.find("username",user.getUsername());
        if(userList.size()>0&&users.size()<=0){
            return new Result(StatusCodeEnum.FAIL.getCode(),"登陆失败",userList);
        }
        if(users.size()<=0){
            return new Result(StatusCodeEnum.ERROR.getCode(),"查询无结果");
        }
        if(users.size()>1){
            return new Result(StatusCodeEnum.WARNING.getCode(),"查询结果不唯一",users);
        }
        Map<String,Object> res=new HashMap<>();
        res.put("users",users);
        this.currentUser=users.get(0);
        res.put("token",TokenProccessor.getInstance().makeToken());
        return new Result(StatusCodeEnum.SUCCESS.getCode(),"查询成功",res);
    }

    @Override
    public Result deleteById( String val) {
        return userDao.deleteById(val);
    }

    @Override
    public Result current() {
        if(this.currentUser!=null){
            return new Result(StatusCodeEnum.SUCCESS.getCode(),"当前用户查询成功",currentUser);
        }else{
            return new Result(StatusCodeEnum.WARNING.getCode(),"用户信息获取异常");
        }
    }
}
