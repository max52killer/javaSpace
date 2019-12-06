package com.dragonsoft.EasyTest.mongodb.po;

import com.dragonsoft.EasyTest.mongodb.anno.Collect;
import com.dragonsoft.EasyTest.mongodb.anno.Column;
import com.dragonsoft.EasyTest.mongodb.anno.GeneratedValue;
import com.dragonsoft.EasyTest.mongodb.anno.Id;

import java.io.Serializable;

/**
 * @program: EasyTest
 * @description: 系统表码
 * @author: songzm
 * @create: 2019-12-04 16:29
 **/
@Collect(name="t_sys_code")
public class TSysCode implements Serializable{
    @Id
    @GeneratedValue
    @Column(name="_id")
    private String _id;
    @Column(name="code",desc = "表码编号")
    private String code;
    @Column(name="code_name",desc = "表码名称")
    private String codeName;
    @Column(name="type",desc = "表码类型")
    private String type;
    @Column(name="type_name",desc = "类型中文描述")
    private String typeName;
    @Column(name="no",desc = "编号")
    private String no;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCodeName() {
        return codeName;
    }

    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }
}
