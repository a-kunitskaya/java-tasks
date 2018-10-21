package com.kunitskaya.reflection;

import com.kunitskaya.service.annotations.ThisCodeSmells;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Set;

import static com.kunitskaya.logging.ProjectLogger.LOGGER;
import static com.kunitskaya.service.annotations.handlers.ThisCodeSmellsHandler.FOUND_MESSAGE;

public class MetaDataPrinter {

    public static void printMetaData(Class<?> clazz) {
        String name = clazz.getSimpleName();
        String superName = clazz.getSuperclass().getSimpleName();
        Field[] fields = clazz.getDeclaredFields();
        Method[] methods = clazz.getDeclaredMethods();
        Class<?>[] interfaces = clazz.getInterfaces();
        Annotation[] annotations = clazz.getAnnotations();
        Constructor<?>[] constructors = clazz.getConstructors();
        int modifiers = clazz.getModifiers();

        System.out.println("Class name: " + name + "\n"
                + "Super class name: " + superName + "\n"
                + "Fields: " + Arrays.toString(fields) + "\n"
                + "Methods: " + Arrays.toString(methods) + "\n"
                + "Interfaces: " + Arrays.toString(interfaces) + "\n"
                + "Annotations: " + Arrays.toString(annotations) + "\n"
                + "Constructors: " + Arrays.toString(constructors) + "\n"
                + "Modifiers: " + modifiers + "\n");


    }

    public static void printThisCodeSmellsClasses(Set<Class<?>> annotatedClasses) {
        for (Class<?> clazz : annotatedClasses) {
            ThisCodeSmells annotation = clazz.getAnnotation(ThisCodeSmells.class);
            String type = "class";
            LOGGER.info(String.format(FOUND_MESSAGE, type, clazz.getSimpleName(), annotation.reviewer(), clazz.getSimpleName()));
        }
    }

    public static void printThisCodeSmellsMethods(Set<Method> annotatedMethods) {
        for (Method method : annotatedMethods) {
            ThisCodeSmells annotation = method.getAnnotation(ThisCodeSmells.class);
            String type = "method";
            LOGGER.info(String.format(FOUND_MESSAGE, type, method.getName(), annotation.reviewer(), method.getDeclaringClass().getSimpleName()));
        }
    }

    public static void printThisCodeSmellsFields(Set<Field> annotatedFields) {
        for (Field field : annotatedFields) {
            ThisCodeSmells annotation = field.getAnnotation(ThisCodeSmells.class);
            String type = "field";
            LOGGER.info(String.format(FOUND_MESSAGE, type, field.getName(), annotation.reviewer(), field.getDeclaringClass().getSimpleName()));
        }
    }
}
