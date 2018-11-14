package com.kunitskaya.module2.handlers;

import com.kunitskaya.module2.annotations.ProdCode;
import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;

import java.lang.reflect.Method;
import java.util.Set;

public class ProdCodeHandler {
    public static final String PROD_CODE_PACKAGE = "com.kunitskaya";
    public static final String PROD_CODE_ANNOTATION = ProdCode.class.getSimpleName();

    public static Set<Method> getAnnotatedMethods() {
        Reflections ref = new Reflections(PROD_CODE_PACKAGE, new MethodAnnotationsScanner());
        return ref.getMethodsAnnotatedWith(ProdCode.class);
    }
}
