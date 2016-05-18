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
     * field type,to indicate the real type you need pass.this type will used with {fieldType}
     */
    Class type();

    /**
     * field javadoc,to generate java doc in java code
     */
    String doc() default "";

    /**
     * default value.to generate this field default value in ArgsData class
     */
    String defValue() default "";

    /**
     * associate with {type},to indicate the real type you want:<br>
     *     rules:<br>
     *         Serializable + type : {type}<br>
     *         <xmp>list + type : List<type></xmp><br>
     *         <xmp>set + type : Set<type></xmp><br>
     *         array + type : type[]
     */
    FieldType fieldType() default FieldType.Serializable;

}
