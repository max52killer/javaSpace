package com.dragonsoft.EasyTest.mongodb.po;

import com.dragonsoft.EasyTest.mongodb.anno.Collect;
import com.dragonsoft.EasyTest.mongodb.anno.Column;
import com.dragonsoft.EasyTest.mongodb.anno.Id;

@Collect(name="person")
public class Person {
    @Id
    @Column(name="_id")
    private String _id;
    @Column(name="name")
    private String name;
    @Column(name="sex")
    private String sex;
    @Column(name="age")
    private String age;
    @Column(name="mz")
    private String mz;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getMz() {
        return mz;
    }

    public void setMz(String mz) {
        this.mz = mz;
    }
}
