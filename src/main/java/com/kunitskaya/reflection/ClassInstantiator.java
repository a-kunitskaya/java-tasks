package com.kunitskaya.reflection;

import com.kunitskaya.domain.appliances.HouseholdAppliance;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static com.kunitskaya.logging.ProjectLogger.LOGGER;

public class ClassInstantiator {
    private static final String SUCCESS_MESSAGE = "Successfully initialized instance of %s, parameters: %s";
    private static final String ERROR_MESSAGE = "Could not instantiate object for class %s";

    /**
     * Instantiates a child to HouseholdAppliance class without parameters
     *
     * @param clazz - child to HouseholdAppliance class
     * @return instance of HouseholdAppliance child class
     */
    public static HouseholdAppliance instantiate(Class<?> clazz) {
        HouseholdAppliance instance = null;
        try {
            Constructor<?> constructor = clazz.getConstructor();
            instance = (HouseholdAppliance) constructor.newInstance();
            LOGGER.info(String.format(SUCCESS_MESSAGE, clazz.getSimpleName(), StringUtils.EMPTY));

        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            LOGGER.error(String.format(ERROR_MESSAGE, clazz.getSimpleName()));
            e.printStackTrace();
        }
        return instance;
    }

    /**
     * Instantiates a child to HouseholdAppliance class with a parameter
     *
     * @param clazz            - child to HouseholdAppliance class
     * @param powerConsumption - parameter to pass to constructor
     * @return instance of HouseholdAppliance child class
     */
    public static HouseholdAppliance instantiate(Class<?> clazz, int powerConsumption) {
        HouseholdAppliance instance = null;
        try {
            Class[] paramTypes = new Class[]{int.class};
            Constructor<?> constructor = clazz.getConstructor(paramTypes);
            Object[] args = new Object[]{powerConsumption};
            instance = (HouseholdAppliance) constructor.newInstance(args);
            LOGGER.info(String.format(SUCCESS_MESSAGE, clazz.getSimpleName(), powerConsumption));
            return instance;

        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            LOGGER.error(String.format(ERROR_MESSAGE, clazz.getSimpleName()));
            e.printStackTrace();
        }
        return instance;
    }
}
