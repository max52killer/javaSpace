package com.dragonsoft.EasyTest.mongodb.po;

import com.dragonsoft.EasyTest.mongodb.anno.Collect;
import com.dragonsoft.EasyTest.mongodb.anno.Column;
import com.dragonsoft.EasyTest.mongodb.anno.GeneratedValue;
import com.dragonsoft.EasyTest.mongodb.anno.Id;

import java.io.Serializable;

/**
 * @program: EasyTest
 * @description: 用户信息
 * @author: songzm
 * @create: 2019-12-04 11:41
 **/
@Collect(name = "t_user")
public class TUser implements Serializable{
    @Id
    @Column(name="_id")
    @GeneratedValue
    private String _id;
    @Column(name="username")
    private String username;
    @Column(name="password")
    private String password;
    @Column(name="age")
    private String age;
    @Column(name="sfzh")
    private String sfzh;
    @Column(name="nickName")
    private String nickName;
    @Column(name="sex")
    private String sex;
    @Column(name="dept")
    private String dept;
    @Column(name="image")
    private String image;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSfzh() {
        return sfzh;
    }

    public void setSfzh(String sfzh) {
        this.sfzh = sfzh;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
