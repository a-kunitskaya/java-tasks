package com.kunitskaya.reflection;

import com.kunitskaya.domain.HomeLocation;
import com.kunitskaya.domain.appliances.Fridge;
import com.kunitskaya.domain.appliances.HouseholdAppliance;
import com.kunitskaya.domain.appliances.Kettle;
import com.kunitskaya.service.annotations.handlers.ThisCodeSmellsHandler;
import com.kunitskaya.service.implementation.PowerConsumptionCounter;
import com.kunitskaya.service.implementation.SorterByPowerConsumption;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * Shows Reflection API usage
 * Created to avoid calling all these methods directly from main
 */
public class ReflectionExecutor {

    public static void executeReflectionMethods() {

        //M2 - Task 2 - Reflection API usage
        HouseholdAppliance kettle = ClassInstantiator.instantiate(Kettle.class, 200);
        HouseholdAppliance fridge = ClassInstantiator.instantiate(Fridge.class);

        HouseholdAppliance modelFridge = new Fridge(100, "red", HomeLocation.LIVING_ROOM, -4);
        FieldsManipulator.fillInFieldsWithReflection(modelFridge, fridge);

        MetaDataPrinter.printMetaData(HouseholdAppliance.class);

        MethodsExecutor.executeMethod("plugIn", fridge);
        MethodsExecutor.executeMethod("unplug", fridge);

        List instances = Arrays.asList(kettle, fridge);
        Class[] paramTypes = new Class[]{List.class};
        Object[] args = new Object[]{instances};

        //TODO: change to sort method from branch M1_Core

        MethodsExecutor.executeMethod("sort", new SorterByPowerConsumption(), paramTypes, args);

        //TODO: add find methods from branch M1_Core

        MethodsExecutor.executeMethod("countPowerConsumption", PowerConsumptionCounter.class, paramTypes, args);

        Set<Class<?>> annotatedClasses = ThisCodeSmellsHandler.getAnnotatedClasses();
        MetaDataPrinter.printThisCodeSmellsClasses(annotatedClasses);

        Set<Method> annotatedMethods = ThisCodeSmellsHandler.getAnnotatedMethods();
        MetaDataPrinter.printThisCodeSmellsMethods(annotatedMethods);

        Set<Field> annotatedFields = ThisCodeSmellsHandler.getAnnotatedFields();
        MetaDataPrinter.printThisCodeSmellsFields(annotatedFields);

        ThisCodeSmellsHandler.sortSetsOfAnnotatedElements(annotatedClasses, annotatedFields, annotatedMethods);

    }
}
