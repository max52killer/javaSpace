package com.dragonsoft.EasyTest.mongodb.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *字段注解
 */
@Target({ElementType.FIELD})    //作用域为在字段上
@Retention(RetentionPolicy.RUNTIME)    //生命周期为运行时
public @interface Column {
    //行字段名称
    String name();
    int length() default 255;
}
