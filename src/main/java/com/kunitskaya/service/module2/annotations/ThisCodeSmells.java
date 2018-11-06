package com.kunitskaya.service.module2.annotations;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.ANNOTATION_TYPE,
        ElementType.CONSTRUCTOR,
        ElementType.FIELD,
        ElementType.LOCAL_VARIABLE,
        ElementType.METHOD,
        ElementType.PARAMETER,
        ElementType.TYPE})
@Repeatable(value = ThisCodeSmells.List.class)
public @interface ThisCodeSmells {

    String reviewer() default "Petya";

    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.ANNOTATION_TYPE,
            ElementType.CONSTRUCTOR,
            ElementType.FIELD,
            ElementType.LOCAL_VARIABLE,
            ElementType.METHOD,
            ElementType.PARAMETER,
            ElementType.TYPE})
    @interface List {
        ThisCodeSmells[] value();
    }
}
