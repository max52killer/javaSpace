package com.dragonsoft.EasyTest.mongodb.anno;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 标志主键的注解   不传值默认为当前注解标记的名字作为name值
 */
@Target({METHOD, FIELD})
@Retention(RUNTIME)
public @interface Id {
    String name() default "";
}
