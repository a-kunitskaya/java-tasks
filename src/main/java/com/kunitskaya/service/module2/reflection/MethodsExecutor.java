package com.kunitskaya.service.module2.reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static com.kunitskaya.logging.ProjectLogger.LOGGER;

public class MethodsExecutor {
    private static final String NO_METHOD_FOUND_MESSAGE = "Method with name: %s does not exist in class: %s.";
    private static final String COULD_NOT_INVOKE_MESSAGE = "Could not invoke method with name: %s";


    /**
     * Invoke a non-static method with parameters
     *
     * @param methodName     name as defined in class
     * @param instance       instance of class containing method
     * @param parameterTypes types of parameters
     * @param args           values of parameters
     */
    public static void executeMethod(String methodName, Object instance, Class<?>[] parameterTypes, Object[] args) {
        Class<?> clazz = instance.getClass();
        Method method = null;
        try {
            method = clazz.getDeclaredMethod(methodName, parameterTypes);
        } catch (NoSuchMethodException e) {
            LOGGER.error(String.format(NO_METHOD_FOUND_MESSAGE, methodName, clazz.getSimpleName()) + " Looking in parent...");
            try {
                method = clazz.getSuperclass().getDeclaredMethod(methodName, parameterTypes);
            } catch (NoSuchMethodException e1) {
                LOGGER.error(String.format(NO_METHOD_FOUND_MESSAGE, methodName, clazz.getSuperclass().getSimpleName()));
                e1.printStackTrace();
            }
        }
        try {
            if (method != null) {
                method.setAccessible(true);
                method.invoke(instance, args);
            }
        } catch (IllegalAccessException | InvocationTargetException e) {
            LOGGER.error(String.format(COULD_NOT_INVOKE_MESSAGE, methodName));
            e.printStackTrace();
        }
    }

    /**
     * Invoke non-static method without parameters
     *
     * @param methodName name as defined in class
     * @param instance   instance of class containing method
     */
    public static void executeMethod(String methodName, Object instance) {
        executeMethod(methodName, instance, null, null);
    }

    /**
     * Invoke a static method with parameters
     *
     * @param methodName     name as defined in class
     * @param clazz          class containing method
     * @param parameterTypes types of parameters
     * @param args           values of parameters
     */
    public static void executeMethod(String methodName, Class<?> clazz, Class<?>[] parameterTypes, Object[] args) {
        Method method = null;
        try {
            method = clazz.getDeclaredMethod(methodName, parameterTypes);
        } catch (NoSuchMethodException e) {
            LOGGER.error(String.format(NO_METHOD_FOUND_MESSAGE, methodName, clazz.getSuperclass().getSimpleName()));
        }
        try {
            if (method != null) {
                method.setAccessible(true);
                method.invoke(null, args);
            }
        } catch (IllegalAccessException | InvocationTargetException e) {
            LOGGER.error(String.format(COULD_NOT_INVOKE_MESSAGE, methodName));
            e.printStackTrace();
        }
    }

    /**
     * Invoke a static method without parameters
     *
     * @param methodName name as defined in class
     * @param clazz      class containing method
     */
    public static void executeMethod(String methodName, Class<?> clazz) {
        executeMethod(methodName, clazz, null, null);
    }
}
