package com.lzh.courier.compiler;


import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;

import javax.lang.model.element.Modifier;

/**
 * @author Administrator
 */
public class FragmentFactory extends FileFactory {
    static final String SUFFIX = "Builder";
    static final String BUILD_METHOD = "build";
    static final String GET_DATA_METHOD = "getArguments";
    static final String CREATE_BUNDLE_NAME = "createBundle";

    public static final String BUNDLE_NAME = "android.os.Bundle";

    public FragmentFactory(com.lzh.courier.compiler.ElementParser parser) {
        super(parser);
    }

    @Override
    String getSuffix() {
        return SUFFIX;
    }

    @Override
    void generateCode() throws IOException {
        TypeSpec.Builder typeBuilder = generateTypeBuilder();
        if (!isEmptyParams) {
            // add class RequestData
            TypeSpec.Builder argsBuilder = generateRequestData();
            // create get data method
            argsBuilder.addMethod(createGetDataMethod());

            build(argsBuilder);
            // add get ArgsData
            typeBuilder.addMethod(createGetArgsDataMethod());
        }
        // create filed
        createFields(typeBuilder);
        // create private constructor method
        typeBuilder.addMethod(createPrivateConstructor());
        // add create bundle method
        typeBuilder.addMethod(createBundle());
        // add create method
        typeBuilder.addMethod(createMethod());
        // create set params method
        addParamsSetMethod(typeBuilder);
        if (!isAbstract) {
            // create build method
            typeBuilder.addMethod(buildMethod());
        }
        build(typeBuilder);
    }

    private MethodSpec createBundle() {
        TypeName bundle = getTypeName(BUNDLE_NAME);
        MethodSpec.Builder builder = MethodSpec.methodBuilder(CREATE_BUNDLE_NAME)
                .addModifiers(Modifier.PUBLIC)
                .returns(bundle)
                .addStatement("$T bundle = new $T()", bundle, bundle);

        if (!isEmptyParams) {
            builder.addStatement("bundle.putSerializable($L,$L)",TAG_FIELD,REQUEST_DATA_FIELD_NAME);
        }

        if (generateParentClassName != null) {
            builder.addStatement("bundle.putAll($L.$L())",PARENT_CLASS_FIELD_NAME,CREATE_BUNDLE_NAME);
        }
        builder.addStatement("return bundle");

        return builder.build();
    }

    private MethodSpec createGetDataMethod() {
        String params = "target";
        TypeName type = getTypeName(parser.getElement());
        TypeName returnType = getTypeName(REQUEST_DATA_CLASS);
        return MethodSpec.methodBuilder(GET_DATA_METHOD)
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                .addJavadoc("receive passed data,get data from bundle by tag : $L",TAG_FIELD)
                .returns(returnType)
                .addParameter(type, params)
                .addStatement("String TAG = $S",parser.getClzName())
                .addStatement("$L data = $L.getArguments()",BUNDLE_NAME,params)
                .beginControlFlow("if (data == null || data.getSerializable(TAG) == null)")
                .addStatement("return new $T()", returnType)
                .endControlFlow()
                .beginControlFlow("else")
                .addStatement("return ($T) data.getSerializable(TAG)", returnType)
                .endControlFlow()
                .build();
    }

    private MethodSpec buildMethod() {
        TypeName bundle = getTypeName(BUNDLE_NAME);
        TypeName clz = getTypeName(parser.getElement());
        MethodSpec.Builder builder = MethodSpec.methodBuilder(BUILD_METHOD)
                .addModifiers(Modifier.PUBLIC)
                .addJavadoc("build fragment instance of $L",clz)
                .returns(getTypeName(parser.getElement()))
                .addStatement("$T instance = new $T()",clz,clz)
                .addStatement("$T bundle = $L()",bundle,CREATE_BUNDLE_NAME)
                .addStatement("instance.setArguments(bundle)")
                .addStatement("return instance");

        return builder.build();
    }

    private void createFields(TypeSpec.Builder typeBuilder) {
        // add tag
        typeBuilder.addField(FieldSpec.builder(TypeName.get(String.class), TAG_FIELD, Modifier.PRIVATE, Modifier.FINAL, Modifier.STATIC)
                .initializer("$S", parser.getClzName()).build());
        if (!isEmptyParams) {
            // add RequestData filed
            typeBuilder.addField(FieldSpec.builder(getTypeName(REQUEST_DATA_CLASS), REQUEST_DATA_FIELD_NAME, Modifier.PUBLIC).build());
        }
        if (generateParentClassName != null) {
            typeBuilder.addField(FieldSpec.builder(generateParentClassName,PARENT_CLASS_FIELD_NAME,Modifier.PRIVATE).build());
        }
    }

    /**
     * create generate class builder
     */
    private TypeSpec.Builder generateTypeBuilder() {
        String clzName = parser.getClzName();
        clzName = clzName + SUFFIX;
        return TypeSpec.classBuilder(clzName)
                .addModifiers(Modifier.PUBLIC);
    }
}
