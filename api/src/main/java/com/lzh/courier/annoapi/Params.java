package com.lzh.courier.annoapi;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Administrator
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.CLASS)
public @interface Params {
    Field[] fields() default {};
}
