package com.kunitskaya.module2.reflection;

import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static com.kunitskaya.logging.ProjectLogger.LOGGER;

@SuppressWarnings(value = "unchecked")
public class ClassInstantiator {
    private static final String SUCCESS_MESSAGE = "Successfully initialized instance of %s, parameters: %s";
    private static final String ERROR_MESSAGE = "Could not instantiate object for class %s";

    /**
     * Instantiates an instance of class without parameters
     *
     * @param clazz target class
     * @return instance of target class
     */
    public static <T> T instantiate(Class<T> clazz) {
        T instance = null;
        try {
            Constructor<?> constructor = clazz.getConstructor();
            instance = (T) constructor.newInstance();
            LOGGER.info(String.format(SUCCESS_MESSAGE, clazz.getSimpleName(), StringUtils.EMPTY));

        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            LOGGER.error(String.format(ERROR_MESSAGE, clazz.getSimpleName()));
            e.printStackTrace();
        }
        return instance;
    }

    /**
     * Instantiates an instance of class with a parameter
     *
     * @param clazz target class
     * @param param parameter to pass to constructor
     * @return instance of target class
     */
    public static <T> T instantiate(Class<T> clazz, Object... param) {
        T instance;

        try {
            Class[] paramTypes = new Class[param.length];
            Object[] args = new Object[param.length];

            for (int i = 0; i < param.length; i++) {
                paramTypes[i] = param[i].getClass();
                args[i] = param[i];
            }

            Constructor<?> constructor = clazz.getConstructor(paramTypes);

            instance = (T) constructor.newInstance(args);
            LOGGER.info(String.format(SUCCESS_MESSAGE, clazz.getSimpleName(), param.length));
            return instance;

        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            LOGGER.error(String.format(ERROR_MESSAGE, clazz.getSimpleName()));
            e.printStackTrace();
        }
        return null;
    }
}
