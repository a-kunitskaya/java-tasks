package com.kunitskaya.service.annotations.handlers;

import com.kunitskaya.service.annotations.ThisCodeSmells;
import org.apache.commons.lang3.ArrayUtils;
import org.reflections.Reflections;
import org.reflections.scanners.FieldAnnotationsScanner;
import org.reflections.scanners.MethodAnnotationsScanner;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

import static com.kunitskaya.logging.ProjectLogger.LOGGER;

public class ThisCodeSmellsHandler {
    public static final String ANNOTATION_NAME = ThisCodeSmells.class.getSimpleName();
    public static final String NOT_FOUND_MESSAGE = "No annotations of type " + ANNOTATION_NAME + "is found in %s: %s";
    public static final String FOUND_MESSAGE = "Found %s: %s, annotated with: " + ANNOTATION_NAME + ", reviewer: %s, in class: %s";
    private static final String PACKAGE = "com.kunitskaya";

    private static ThisCodeSmells[] getClassAnnotations(Class<?> clazz) {
        if (clazz.isAnnotationPresent(ThisCodeSmells.class)) {
            return clazz.getAnnotationsByType(ThisCodeSmells.class);
        } else {
            LOGGER.info(String.format(NOT_FOUND_MESSAGE, clazz.getTypeName(), clazz.getSimpleName()));
            return new ThisCodeSmells[0];
        }
    }

    private static ThisCodeSmells[] getMethodAnnotations(Class<?> clazz) {
        Method[] methods = clazz.getMethods();
        List<ThisCodeSmells> methodAnnotationsList = new LinkedList<>();

        for (Method method : methods) {
            if (method.isAnnotationPresent(ThisCodeSmells.class)) {
                ThisCodeSmells[] annotations = method.getAnnotationsByType(ThisCodeSmells.class);
                if (annotations.length != 0) {
                    methodAnnotationsList.addAll(Arrays.asList(annotations));
                }
            }
        }
        ThisCodeSmells[] methodAnnotations = methodAnnotationsList.toArray(new ThisCodeSmells[]{});
        return (ThisCodeSmells[]) ArrayUtils.toPrimitive(methodAnnotations);
    }

    public static Set<Class<?>> getAnnotatedClasses() {
        Reflections ref = new Reflections(PACKAGE);
        return ref.getTypesAnnotatedWith(ThisCodeSmells.class);
    }

    public static Set<Method> getAnnotatedMethods() {
        Reflections ref = new Reflections(PACKAGE, new MethodAnnotationsScanner());
        return ref.getMethodsAnnotatedWith(ThisCodeSmells.class);
    }

    public static Set<Field> getAnnotatedFields() {
        Reflections ref = new Reflections(PACKAGE, new FieldAnnotationsScanner());
        return ref.getFieldsAnnotatedWith(ThisCodeSmells.class);
    }

    public static List<Set> sortSetsOfAnnotatedElements(Set... annotatedElementSets) {
        int numberOfSets = annotatedElementSets.length;
        LOGGER.info("Total number of sets of annotated elements: " + numberOfSets);

        for (int i = 0; i < numberOfSets; i++) {
            LOGGER.info("Number of elements in set #" + i + ": " + annotatedElementSets[i].size());
            if (!annotatedElementSets[i].isEmpty()) {
                String elementName = annotatedElementSets[i].toArray()[0].getClass().getSimpleName();
                LOGGER.info("Elements type: " + elementName);
            }
        }

        LOGGER.info("Sorting...");

        List<Set> sets = Arrays.asList(annotatedElementSets);
        sets.sort(Comparator.comparingInt(Set::size));

        for (Set set : sets) {
            LOGGER.info("Number of elements in set #" + sets.indexOf(set) + ": " + set.size());
            if (!set.isEmpty()) {
                String elementName = set.toArray()[0].getClass().getSimpleName();
                LOGGER.info("Elements type: " + elementName);
            }
        }
        return sets;
    }
}
