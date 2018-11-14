package com.kunitskaya.module6.service;

import org.springframework.beans.factory.support.MethodReplacer;

import java.lang.reflect.Method;

import static com.kunitskaya.logging.ProjectLogger.LOGGER;

public class EMethodReplacer implements MethodReplacer {

    @Override
    public Object reimplement(Object obj, Method method, Object[] args) {
        String message = "Method: '%s' has successfully been replaced";
        LOGGER.info(String.format(message, method.getName()));
        return obj;
    }
}
