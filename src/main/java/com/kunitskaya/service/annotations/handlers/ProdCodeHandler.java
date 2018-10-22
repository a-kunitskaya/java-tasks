package com.kunitskaya.service.annotations.handlers;

import com.kunitskaya.service.annotations.ProdCode;
import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;

import java.lang.reflect.Method;
import java.util.Set;

public class ProdCodeHandler extends BaseAnnotationHandler {
    public static Set<Method> getAnnotatedMethods() {
        Reflections ref = new Reflections(BASE_PACKAGE, new MethodAnnotationsScanner());
        return ref.getMethodsAnnotatedWith(ProdCode.class);
    }
}
