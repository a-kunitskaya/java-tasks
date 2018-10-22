package com.kunitskaya.service.annotations.handlers;

import com.kunitskaya.service.annotations.ProdCode;
import com.kunitskaya.service.annotations.ThisCodeSmells;

public class BaseAnnotationHandler {
    public static final String NOT_FOUND_MESSAGE = "No annotations of type @%s is found in %s: %s";
    public static final String FOUND_MESSAGE = "Found %s: %s, annotated with: @%s, value: %s, in class: %s";
    public static final String EXECUTED_MESSAGE = "Executed method: %s, annotated with: @%s, value: %s, in class: %s";
    public static final String BASE_PACKAGE = "com.kunitskaya";
    public static final String PROD_CODE_ANNOTATION = ProdCode.class.getSimpleName();
    public static final String THIS_CODE_SMELLS_ANNOTATION = ThisCodeSmells.class.getSimpleName();
}
