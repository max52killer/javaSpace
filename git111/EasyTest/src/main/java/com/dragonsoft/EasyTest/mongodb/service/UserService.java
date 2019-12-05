package com.dragonsoft.EasyTest.mongodb.service;


import com.dragonsoft.EasyTest.mongodb.po.TUser;

import java.util.List;

public interface UserService {
    TUser save(TUser user);
    List<TUser> queryList();

}
