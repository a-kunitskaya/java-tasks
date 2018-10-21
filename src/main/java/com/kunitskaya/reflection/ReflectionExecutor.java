package com.kunitskaya.reflection;

import com.kunitskaya.domain.Fridge;
import com.kunitskaya.domain.HouseholdAppliance;
import com.kunitskaya.domain.Kettle;
import com.kunitskaya.service.annotations.ThisCodeSmells;
import com.kunitskaya.service.annotations.handlers.ThisCodeSmellsHandler;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Set;

/**
 * Shows Reflection API usage
 * Created to avoid calling all these methods directly from main
 *
 */
public class ReflectionExecutor {

    public static void executeReflectionMethods() {

        //M2 - Task 2 - Reflection API usage
        HouseholdAppliance kettle = ClassInstantiator.instantiate(Kettle.class, 200);
        HouseholdAppliance fridge = ClassInstantiator.instantiate(Fridge.class);

        Set<Class<?>> annotatedClasses = ThisCodeSmellsHandler.getAnnotatedClasses();
        MetaDataPrinter.printThisCodeSmellsClasses(annotatedClasses);

        Set<Method> annotatedMethods = ThisCodeSmellsHandler.getAnnotatedMethods();
        MetaDataPrinter.printThisCodeSmellsMethods(annotatedMethods);

        Set<Field> annotatedFields = ThisCodeSmellsHandler.getAnnotatedFields();
        MetaDataPrinter.printThisCodeSmellsFields(annotatedFields);

        ThisCodeSmellsHandler.sortSetsOfAnnotatedElements(annotatedClasses, annotatedFields, annotatedMethods);

    }
}
