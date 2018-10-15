package com.kunitskaya.reflection;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

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
}
