package com.dragonsoft.EasyTest.mongodb.anno;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 实体注解  声明实体
 */
@Documented
@Target(TYPE)
@Retention(RUNTIME)
public @interface Entity {
    String name() default "";
}
