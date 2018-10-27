package com.kunitskaya;

import com.kunitskaya.domain.HomeLocation;
import com.kunitskaya.domain.appliances.Fridge;
import com.kunitskaya.domain.appliances.HouseholdAppliance;
import com.kunitskaya.domain.appliances.Kettle;
import com.kunitskaya.domain.appliances.TvSet;
import com.kunitskaya.reflection.ClassInstantiator;
import com.kunitskaya.reflection.FieldsManipulator;
import com.kunitskaya.reflection.MetaDataPrinter;
import com.kunitskaya.reflection.MethodsExecutor;
import com.kunitskaya.service.annotations.handlers.ThisCodeSmellsHandler;
import com.kunitskaya.service.annotations.runners.ProdCodeRunner;
import com.kunitskaya.service.domain.implementation.appliances.PowerConsumptionCounter;
import com.kunitskaya.service.domain.implementation.appliances.SorterByPowerConsumption;

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

        SorterByPowerConsumption sorter = ClassInstantiator.instantiate(SorterByPowerConsumption.class);
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
