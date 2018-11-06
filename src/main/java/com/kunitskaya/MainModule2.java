package com.kunitskaya;

import com.kunitskaya.domain.module1.HomeLocation;
import com.kunitskaya.domain.module1.appliances.Fridge;
import com.kunitskaya.domain.module1.appliances.HouseholdAppliance;
import com.kunitskaya.domain.module1.appliances.Kettle;
import com.kunitskaya.domain.module1.appliances.TvSet;
import com.kunitskaya.service.module1.implementation.ByPowerConsumptionApplianceSorter;
import com.kunitskaya.service.module1.implementation.PowerConsumptionCounter;
import com.kunitskaya.service.module2.handlers.ThisCodeSmellsHandler;
import com.kunitskaya.service.module2.reflection.ClassInstantiator;
import com.kunitskaya.service.module2.reflection.FieldsManipulator;
import com.kunitskaya.service.module2.reflection.MetaDataPrinter;
import com.kunitskaya.service.module2.reflection.MethodsExecutor;
import com.kunitskaya.service.module2.runners.ProdCodeRunner;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Set;


public class MainModule2 {

    public static void main(String[] args) {
        HouseholdAppliance kettle = ClassInstantiator.instantiate(Kettle.class, 200, "black", HomeLocation.KITCHEN, 3.0);
        HouseholdAppliance fridge = ClassInstantiator.instantiate(Fridge.class);
        HouseholdAppliance tvSet = ClassInstantiator.instantiate(TvSet.class);

        HouseholdAppliance modelFridge = ClassInstantiator.instantiate(Fridge.class, 300);

        if (modelFridge != null) {
            FieldsManipulator.fillInFieldsWithReflection(modelFridge, fridge);
        }

        MetaDataPrinter.printMetaData(HouseholdAppliance.class);

        MethodsExecutor.executeMethod("plugIn", fridge);
        MethodsExecutor.executeMethod("unplug", fridge);

        List instances = Arrays.asList(kettle, fridge);
        Class[] paramTypes = new Class[]{List.class};
        Object[] values = new Object[]{instances};

        ByPowerConsumptionApplianceSorter sorter = ClassInstantiator.instantiate(ByPowerConsumptionApplianceSorter.class);
        MethodsExecutor.executeMethod("sort", sorter, paramTypes, values);
        MethodsExecutor.executeMethod("countPowerConsumption", PowerConsumptionCounter.class, paramTypes, values);

        Set<Class<?>> annotatedClasses = ThisCodeSmellsHandler.getAnnotatedClasses();
        MetaDataPrinter.printThisCodeSmellsClasses(annotatedClasses);

        Set<Method> annotatedMethods = ThisCodeSmellsHandler.getAnnotatedMethods();
        MetaDataPrinter.printThisCodeSmellsMethods(annotatedMethods);

        Set<Field> annotatedFields = ThisCodeSmellsHandler.getAnnotatedFields();
        MetaDataPrinter.printThisCodeSmellsFields(annotatedFields);

        ThisCodeSmellsHandler.sortSetsOfAnnotatedElements(annotatedClasses, annotatedFields, annotatedMethods);
        ProdCodeRunner.runProdCodeMethods();
    }
}
