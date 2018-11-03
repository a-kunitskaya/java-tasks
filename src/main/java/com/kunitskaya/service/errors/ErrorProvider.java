package com.kunitskaya.service.errors;

import com.kunitskaya.domain.errordomain.StackOverFlowObject;
import javassist.CannotCompileException;
import javassist.ClassPool;
import org.apache.commons.lang3.RandomStringUtils;
import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static com.kunitskaya.logging.ProjectLogger.LOGGER;

public class ErrorProvider {
    private static final String MESSAGE = "Throwing: %s, caused by: %s";
    private static ClassPool classPool = ClassPool.getDefault(); //makes loaded objects stay in memory

    public static void getOutOfMemoryErrorHeapSpace() {
        LOGGER.info(String.format(MESSAGE, "OutOfMemoryError", "big array size"));
        int[] array = new int[Integer.MAX_VALUE - 3];
    }

    public static void getOutOfMemoryErrorHeapSpaceObjects() throws Exception {
        LOGGER.info(String.format(MESSAGE, "OutOfMemoryError", "excessive memory allocation for object"));

        Class clazz = Class.forName("sun.misc.Unsafe");
        Field field = clazz.getDeclaredField("theUnsafe");
        field.setAccessible(true);
        Unsafe unsafe = (Unsafe) field.get(null);
        while (true) {
            unsafe.allocateMemory(1024 * 1024);
        }
    }

    //-XX:MaxMetaspaceSize=25m - vm option to limit metaspace size
    public static void getOutOfMemoryErrorMetaspace() {
        LOGGER.info(String.format(MESSAGE, "OutOfMemoryError", "to many loaded classes"));

        for (int i = 0; i < 1700; i++) {
            try {
                Class clazz = classPool.makeClass(i + "class").toClass();
            } catch (CannotCompileException e) {
                e.printStackTrace();
            }
        }
    }

    public static void getStackOverflowErrorRecursion() {
        LOGGER.info(String.format(MESSAGE, "StackOverflowError", "recursion"));
        recursiveMethod();
    }

    //AK: I guess it's also recursion, but not a typical one
    public static void getStackOverFlowErrorNoRecursion() {
        LOGGER.info(String.format(MESSAGE, "StackOverflowError", "instantiating object whose constructor instantiates the object"));
        new StackOverFlowObject();
    }

    public static void catchOutOfMemoryErrorInInfiniteLoop() {
        List<String> strings = new ArrayList<>();

        while (true) {
            try {
                LOGGER.info("Adding string to list to increase heap usage...");

                String s = RandomStringUtils.randomAlphanumeric(1, Integer.MAX_VALUE - 10000);
                strings.add(s);
            } catch (OutOfMemoryError e) {
                LOGGER.error(String.format(MESSAGE, "OutOfMemoryError", "bit array list"));
            }
        }
    }

    private static void recursiveMethod() {
    }
}
