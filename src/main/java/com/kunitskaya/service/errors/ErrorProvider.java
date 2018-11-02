package com.kunitskaya.service.errors;

import com.kunitskaya.domain.errordomain.StackOverFlowObject;
import sun.misc.Unsafe;

import java.lang.reflect.Field;

import static com.kunitskaya.logging.ProjectLogger.LOGGER;

public class ErrorProvider {
    private static final String MESSAGE = "Throwing: %s, caused by: %s";

    public static void getOutOfMemoryErrorHeapSpace() {
        LOGGER.info(String.format(MESSAGE, "OutOfMemoryError", "big array size"));
        int[] array = new int[Integer.MAX_VALUE - 3];
    }

    public static void getOutOfMemoryErrorHeapSpaceObjects() throws Exception {
        LOGGER.info(String.format(MESSAGE, "OutOfMemoryError", "excessive memory allocation for object"));

        Class unsafeClass = Class.forName("sun.misc.Unsafe");
        Field f = unsafeClass.getDeclaredField("theUnsafe");
        f.setAccessible(true);
        Unsafe unsafe = (Unsafe) f.get(null);
        while (true) {
            unsafe.allocateMemory(1024 * 1024);
        }
    }

    //-XX:MaxMetaspaceSize=512m
    public static void getOutOfMemoryErrorMetaspace() throws ClassNotFoundException {
        LOGGER.info(String.format(MESSAGE, "OutOfMemoryError", ""));


    }

    public static void getStackOverflowErrorRecursion() {
        LOGGER.info(String.format(MESSAGE, "StackOverflowError", "recursion"));
        recursiveMethod();
    }

    public static void getStackOverFlowErrorNoRecursion() {
        LOGGER.info(String.format(MESSAGE, "StackOverflowError","instantiating object whose constructor instantiates the object \n " +
                "ak: though I guess it's also recursion, but not a typical one"));
       new StackOverFlowObject();
    }

    private static void recursiveMethod() {

    }

}
