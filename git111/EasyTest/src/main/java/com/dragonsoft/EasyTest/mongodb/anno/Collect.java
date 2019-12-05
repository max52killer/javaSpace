package com.dragonsoft.EasyTest.mongodb.anno;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * mongodb中的表注解  声明表名
 */
@Target(TYPE)
@Retention(RUNTIME)
public @interface Collect {
    //name不允许为空 需要与数据库一致
    String name();
    String catalog() default "";
    String schema() default "";
}
