package com.kunitskaya.service.errors;

import com.kunitskaya.logging.ProjectLogger;

import static com.kunitskaya.logging.ProjectLogger.LOGGER;

public class OutOfMemoryErrorProvider {
    private static final String MESSAGE = "Throwing OutOfMemoryError: %s, caused by: %s";

    public static void getOutOfMemotyErrorHeapSpace() {
        LOGGER.info(String.format(MESSAGE, "Java heap space", "big array size"));
        int[] array = new int[Integer.MAX_VALUE - 3];
    }

    public static void getOutOfMemotyErrorHeapSize() {
        LOGGER.info(String.format(MESSAGE, "", ""));
    }

    public static void getOutOfMemotyErrorHeapSizeObjects() {
        LOGGER.info(String.format(MESSAGE, "", ""));
    }

    //-XX:MaxMetaspaceSize=512m
    public static void getOutOfMemotyErrorMetaspace() {
        LOGGER.info(String.format(MESSAGE, "", ""));
    }

    public static void getOutOfMemotyErrorRecursion() {
        LOGGER.info(String.format(MESSAGE, "", ""));
    }

    public static void getOutOfMemotyErrorNoRecursion() {
        LOGGER.info(String.format(MESSAGE, "", ""));
    }
}
