package com.lzh.courier.compiler;

import com.lzh.courier.FieldData;
import com.lzh.courier.annoapi.Field;
import com.lzh.courier.annoapi.Params;
import com.lzh.courier.util.UtilMgr;
import com.squareup.javapoet.TypeName;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.MirroredTypeException;
import javax.lang.model.type.TypeMirror;

/**
 * @author Administrator
 */
public class ElementParser {

    private static final String ACT_NAME = "android.app.Activity";
    private static final String FRAG_NAME = "android.app.Fragment";
    private static final String V4_FRAG_NAME = "android.support.v4.app.Fragment";
    /**
     * class name use @Params
     */
    private String clzName;

    /**
     * activity or fragment
     */
    private ElementType type;
    /**
     * annotations filed list
     */
    private List<FieldData> fieldList;
    /**
     * annotations filed list in parent class
     */
    private List<FieldData> parentFieldList;
    private TypeElement parentElement;
    /**
     * use @Params annotation class
     */
    private TypeElement element;

    public String getClzName() {
        return clzName;
    }

    public List<FieldData> getParentFieldList() {
        return parentFieldList;
    }

    public TypeElement getParentElement() {
        return parentElement;
    }

    public List<FieldData> getFieldList() {
        return fieldList;
    }

    public TypeElement getElement() {
        return element;
    }

    public static ElementParser createParser(TypeElement element) {
        ElementParser parser = new ElementParser();
        parser.element = element;
        parser.parse();
        return parser;
    }

    void parse() {
        checkIsCorrectClass();
        clzName = element.getSimpleName().toString();
        fieldList = parseField(element);
        parentFieldList = parseParentField();
        checkIsDuplicate(fieldList, parentFieldList);
    }

    private void checkIsDuplicate(List<FieldData> fieldList, List<FieldData> parentFieldList) {
        for (FieldData parentData : parentFieldList) {
            for (FieldData subData : fieldList) {
                if (subData.getName().equals(parentData.getName())) {
                    throw new IllegalArgumentException(
                            String.format("The field %s was defined in parent class",subData.getName())
                    );
                }
            }
        }
    }

    /**
     * check out if is extends from Activity or fragment
     */
    private void checkIsCorrectClass() {
        if (checkIsSubClass(ACT_NAME)) {
            type = ElementType.ACTTIVITY;
        } else if (checkIsSubClass(FRAG_NAME) || checkIsSubClass(V4_FRAG_NAME)) {
            type = ElementType.FRAGMENT;
        } else {
            throw new IllegalArgumentException(String.format("class %s must be extends from %s or %s or %s",
                    element.getQualifiedName(),ACT_NAME,FRAG_NAME,V4_FRAG_NAME));
        }
    }

    private List<FieldData> parseParentField() {
        List<FieldData> parentFieldList = new ArrayList<>();
        TypeMirror superTM = element.getSuperclass();
        TypeElement curElement;
        do {
            curElement = (TypeElement) UtilMgr.getMgr().getTypeUtils().asElement(superTM);
            if (curElement == null) {
                return parentFieldList;
            }
            List<FieldData> parentList = parseField(curElement);
            if (parentList.size() > 0 && parentElement == null)  {
                parentElement = curElement;
            }
            parentFieldList.addAll(parentList);
            superTM = curElement.getSuperclass();
        }while (superTM != null);
        return parentFieldList;
    }

    private List<FieldData> parseField(TypeElement element) {
        List<FieldData> fieldList = new ArrayList<>();
        Params annotation = element.getAnnotation(Params.class);
        if (annotation == null) {
            return fieldList;
        }
        Field[] fields = annotation.fields();
        for (int i = 0; i < (fields == null ? 0 : fields.length); i++) {
            Field field = fields[i];
            FieldData data = new FieldData();
            data.setDoc(field.doc());
            data.setName(field.name());
            data.setType(getClzType(field));
            data.setFieldType(field.fieldType());
            data.setDefValue(field.defValue());
            fieldList.add(data);
        }
        return fieldList;
    }

    private TypeName getClzType (Field field) {
        TypeName typeName;
        try {
            Class<?> clazz = field.type();
            typeName = TypeName.get(clazz);
        } catch (MirroredTypeException mte) {
            typeName = TypeName.get(mte.getTypeMirror());
        }
        return typeName;
    }

    /**
     * check out if is target's subclass
     */
    private boolean checkIsSubClass(String target) {
        TypeElement type = element;
        while (true) {
            if (type == null) {
                return false;
            } else if (target.equals(type.getQualifiedName().toString())) {
                return true;
            }
            type = getParentClass(type);
        }
    }

    TypeElement getParentClass (TypeElement child) {
        return (TypeElement) UtilMgr.getMgr().getTypeUtils().asElement(child.getSuperclass());
    }

    public void generateCode() throws IOException {
        switch (type) {
            case ACTTIVITY:
                new ActivityFactory(this).generateCode();
                break;
            case FRAGMENT:
                new FragmentFactory(this).generateCode();
                break;
        }
    }

    public boolean isAbstract() {
        Set<Modifier> modifiers = element.getModifiers();
        return modifiers.contains(Modifier.ABSTRACT);
    }

    enum ElementType {
        ACTTIVITY,FRAGMENT
    }
}
