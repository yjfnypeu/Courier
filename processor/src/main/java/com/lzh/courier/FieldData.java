package com.lzh.courier;

import com.lzh.courier.annoapi.FieldType;
import com.squareup.javapoet.TypeName;

/**
 * @author Administrator
 */
public class FieldData {

    private String name;
    private String doc;
    private TypeName type;
    private FieldType fieldType;
    private String defValue;

    public String getDefValue() {
        return defValue;
    }

    public void setDefValue(String defValue) {
        this.defValue = defValue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDoc() {
        return doc;
    }

    public void setDoc(String doc) {
        this.doc = doc;
    }

    public TypeName getType() {
        return type;
    }

    public void setType(TypeName type) {
        this.type = type;
    }

    public FieldType getFieldType() {
        return fieldType;
    }

    public void setFieldType(FieldType fieldType) {
        this.fieldType = fieldType;
    }

}
