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

        System.out.println("Class name: " + name + "\n");
        System.out.println("Super class name: " + superName + "\n");
        System.out.println("Fields: " + Arrays.toString(fields) + "\n");
        System.out.println("Methods: " + Arrays.toString(methods) + "\n");
        System.out.println("Interfaces: " + Arrays.toString(interfaces) + "\n");
        System.out.println("Annotations: " + Arrays.toString(annotations) + "\n");
        System.out.println("Constructors: " + Arrays.toString(constructors) + "\n");
        System.out.println("Modifiers: " + modifiers + "\n");


    }
}
