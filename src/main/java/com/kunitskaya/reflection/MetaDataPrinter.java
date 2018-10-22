package com.kunitskaya.reflection;

import com.kunitskaya.service.annotations.ThisCodeSmells;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Set;

import static com.kunitskaya.logging.ProjectLogger.LOGGER;
import static com.kunitskaya.service.annotations.handlers.BaseAnnotationHandler.THIS_CODE_SMELLS_ANNOTATION;
import static com.kunitskaya.service.annotations.handlers.ThisCodeSmellsHandler.FOUND_MESSAGE;

public class MetaDataPrinter {
    private static final String message = "%s, name: %s, type: %s, modifier: %s";

    public static void printMetaData(Class<?> clazz) {
        String name = clazz.getSimpleName();
        String superName = clazz.getSuperclass().getSimpleName();
        Field[] fields = clazz.getDeclaredFields();
        Method[] methods = clazz.getDeclaredMethods();
        Class<?>[] interfaces = clazz.getInterfaces();
        Annotation[] annotations = clazz.getDeclaredAnnotations();
        Constructor<?>[] constructors = clazz.getDeclaredConstructors();
        String modifiers = Modifier.toString(clazz.getModifiers());

        System.out.println("Class name: " + name + "\n" + "Super class name: " + superName + "\nModifiers: " + modifiers);
        Arrays.stream(fields).map(f -> String.format(message, "Field", f.getName(), f.getType(), Modifier.toString(f.getModifiers()))).forEach(System.out::println);
        Arrays.stream(methods).map(m -> String.format(message, "Method", m.getName(), m.getReturnType().getName(), Modifier.toString(m.getModifiers()))).forEach(System.out::println);
        Arrays.stream(interfaces).map(i -> String.format(message, "Interface", i.getName(), i.getTypeName(), Modifier.toString(i.getModifiers()))).forEach(System.out::println);
        Arrays.stream(annotations).map(a -> String.format(message, "Annotation", a.annotationType().getName(), a.annotationType().getTypeName(), Modifier.toString(a.annotationType().getModifiers()))).forEach(System.out::println);
        Arrays.stream(constructors).map(c -> String.format(message, "Constructor", c.getName(), Arrays.toString(c.getParameterTypes()), Modifier.toString(c.getModifiers()))).forEach(System.out::println);
    }

    public static void printThisCodeSmellsClasses(Set<Class<?>> annotatedClasses) {
        for (Class<?> clazz : annotatedClasses) {
            ThisCodeSmells annotation = clazz.getAnnotation(ThisCodeSmells.class);
            String type = "class";
            LOGGER.info(String.format(FOUND_MESSAGE, type, clazz.getSimpleName(), THIS_CODE_SMELLS_ANNOTATION, annotation.reviewer(), clazz.getSimpleName()));
        }
    }

    public static void printThisCodeSmellsMethods(Set<Method> annotatedMethods) {
        for (Method method : annotatedMethods) {
            ThisCodeSmells annotation = method.getAnnotation(ThisCodeSmells.class);
            String type = "method";
            LOGGER.info(String.format(FOUND_MESSAGE, type, method.getName(), THIS_CODE_SMELLS_ANNOTATION, annotation.reviewer(), method.getDeclaringClass().getSimpleName()));
        }
    }

    public static void printThisCodeSmellsFields(Set<Field> annotatedFields) {
        for (Field field : annotatedFields) {
            ThisCodeSmells annotation = field.getAnnotation(ThisCodeSmells.class);
            String type = "field";
            LOGGER.info(String.format(FOUND_MESSAGE, type, field.getName(), THIS_CODE_SMELLS_ANNOTATION, annotation.reviewer(), field.getDeclaringClass().getSimpleName()));
        }
    }
}
