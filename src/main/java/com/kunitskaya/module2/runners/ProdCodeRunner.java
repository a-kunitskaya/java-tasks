package com.kunitskaya.module2.runners;

import com.kunitskaya.logging.ProjectLogger;
import com.kunitskaya.module2.annotations.ProdCode;
import com.kunitskaya.module2.handlers.ProdCodeHandler;
import com.kunitskaya.module2.reflection.MethodsExecutor;

import java.lang.reflect.Method;
import java.util.Set;

import static com.kunitskaya.module2.handlers.ProdCodeHandler.PROD_CODE_ANNOTATION;

public class ProdCodeRunner {
    public static final String EXECUTED_MESSAGE = "Executed method: %s, annotated with: @%s, value: %s, in class: %s";

    public static void runProdCodeMethods() {
        Set<Method> annotatedMethods = ProdCodeHandler.getAnnotatedMethods();

        if (!annotatedMethods.isEmpty()) {
            for (Method method : annotatedMethods) {
                String name = method.getName();
                Class<?> clazz = method.getDeclaringClass();
                MethodsExecutor.executeMethod(name, clazz);

                String value = method.getAnnotation(ProdCode.class).name();
                ProjectLogger.LOGGER.info(String.format(EXECUTED_MESSAGE, name, PROD_CODE_ANNOTATION, value, clazz.getSimpleName()));
            }
        } else {
            throw new IllegalArgumentException("No methods are found for " + PROD_CODE_ANNOTATION);
        }
    }
}
