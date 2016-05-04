package com.lzh.courier.annoapi;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Administrator
 */
@Inherited
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Field {
    /**
     * field name
     */
    String name();

    /**
     * field type
     */
    Class type();

    /**
     * field javadoc
     */
    String doc() default "";

    String defValue() default "";

    FieldType fieldType() default FieldType.Serializable;

}
