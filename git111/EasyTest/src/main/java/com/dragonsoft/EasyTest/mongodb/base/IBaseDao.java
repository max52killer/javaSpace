package com.dragonsoft.EasyTest.mongodb.base;

import com.mongodb.client.result.DeleteResult;

import java.io.Serializable;
import java.util.List;

public interface IBaseDao<T> {

    T get(String var1);

    T load(String var1);

    T update(T var1);

    T save(T var1);

    Result deleteById(String var1);

    void deleteByField(String name,String val);

    void saveOrUpdate(T var1);

    void delete(T var1);


    List<T> findAll();

    List find(String var1);

    List<T> find(String var1, Object var2);

    List find(String var1, Object[] var2);

    List<T> find(List<String> params,List<String> vals);

    void initialize(T var1);
}
