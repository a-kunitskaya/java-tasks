package com.kunitskaya.service.annotations.handlers;

import com.kunitskaya.service.annotations.ThisCodeSmells;
import org.apache.commons.lang3.ArrayUtils;
import org.reflections.Reflections;
import org.reflections.scanners.FieldAnnotationsScanner;
import org.reflections.scanners.MethodAnnotationsScanner;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

    public static int[] sortSetsOfAnnotatedElements(Set... annotatedElementSets) {
        int numberOfSets = annotatedElementSets.length;
        int[] sizes = new int[numberOfSets];
        LOGGER.info("Total number of sets of annotated elements: " + numberOfSets);

        for (int i = 0; i < numberOfSets; i++) {
            LOGGER.info("Number of elements in set #" + i + ": " + annotatedElementSets[i].size());
            if (!annotatedElementSets[i].isEmpty()) {
                String elementName = annotatedElementSets[i].toArray()[0].getClass().getSimpleName();
                LOGGER.info("Elements type: " + elementName);
            }
        }

        for (int i = 0; i < sizes.length; i++) {
            int size = annotatedElementSets[i].size();
            sizes[i] = size;
        }

        LOGGER.info("Sorting...");

        Set<Set> sortedSets = Arrays.stream(annotatedElementSets).sorted().collect(Collectors.toSet());


        for (int i = 0; i < numberOfSets; i++) {
            LOGGER.info("Number of elements in set #" + i + ": " + sortedSets.toArray().length);
            if (!annotatedElementSets[i].isEmpty()) {
                String elementName = sortedSets.getClass().getSimpleName();
                LOGGER.info("Elements type: " + elementName);
            }
        }

        return sizes;
    }
}
