package com.dragonsoft.EasyTest.mongodb.anno;

import com.dragonsoft.EasyTest.mongodb.enums.AutoGenerateType;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({METHOD, FIELD})
@Retention(RUNTIME)
public @interface GeneratedValue {
    String generator() default "UUID";
}
