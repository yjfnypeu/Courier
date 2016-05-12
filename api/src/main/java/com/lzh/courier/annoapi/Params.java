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
    /**
     * define all of the fields to pass;
     */
    Field[] fields() default {};

    /**
     * indicated whether or not to generate params setter with subClass use @Params.default is true.<br>
     * - when set to false,subClass generated builder will be disable all of fields defined by parent class<br>
     *
     *     ps:this feature is not apply to activity
     */
    boolean inherited() default true;
}
