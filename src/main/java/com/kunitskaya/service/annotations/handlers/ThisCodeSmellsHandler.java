package com.kunitskaya.service.annotations.handlers;

import com.kunitskaya.service.annotations.ProdCode;
import com.kunitskaya.service.annotations.ThisCodeSmells;
import org.reflections.Reflections;
import org.reflections.scanners.FieldAnnotationsScanner;
import org.reflections.scanners.MethodAnnotationsScanner;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import static com.kunitskaya.logging.ProjectLogger.LOGGER;

public class ThisCodeSmellsHandler {

    public static final String NOT_FOUND_MESSAGE = "No annotations of type @%s is found in %s: %s";
    public static final String FOUND_MESSAGE = "Found %s: %s, annotated with: @%s, value: %s, in class: %s";
    public static final String THIS_CODE_SMELLS_PACKAGE = "com.kunitskaya";
    public static final String THIS_CODE_SMELLS_ANNOTATION = ThisCodeSmells.class.getSimpleName();


    /**
     * @deprecated Replaced by {@link #getAnnotatedClasses()}
     */
    @Deprecated
    private static ThisCodeSmells[] getClassAnnotations(Class<?> clazz) {
        if (clazz.isAnnotationPresent(ThisCodeSmells.class)) {
            return clazz.getAnnotationsByType(ThisCodeSmells.class);
        } else {
            LOGGER.info(String.format(NOT_FOUND_MESSAGE, THIS_CODE_SMELLS_ANNOTATION, clazz.getTypeName(), clazz.getSimpleName()));
            return new ThisCodeSmells[0];
        }
    }

    @ProdCode(name = "production code")
    public static Set<Class<?>> getAnnotatedClasses() {
        Reflections ref = new Reflections(THIS_CODE_SMELLS_PACKAGE);
        return ref.getTypesAnnotatedWith(ThisCodeSmells.class);
    }

    @ProdCode(name = "another value")
    public static Set<Method> getAnnotatedMethods() {
        Reflections ref = new Reflections(THIS_CODE_SMELLS_PACKAGE, new MethodAnnotationsScanner());
        return ref.getMethodsAnnotatedWith(ThisCodeSmells.class);
    }

    @ProdCode
    public static Set<Field> getAnnotatedFields() {
        Reflections ref = new Reflections(THIS_CODE_SMELLS_PACKAGE, new FieldAnnotationsScanner());
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
