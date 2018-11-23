package com.kunitskaya.module2.reflection;


import com.kunitskaya.module1.domain.HouseholdAppliance;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.stream.Stream;

import static com.kunitskaya.logging.ProjectLogger.LOGGER;

/**
 * Manipulates class fields with reflection
 */
public class FieldsManipulator {

    public static void fillInFieldsWithReflection(HouseholdAppliance from, HouseholdAppliance to) {
        Class<? extends HouseholdAppliance> modelClazz = from.getClass();
        Class<? extends HouseholdAppliance> clazz = to.getClass();
        String name = clazz.getSimpleName();

        if (modelClazz.getSimpleName().equals(name)) {
            Field[] superFields = clazz.getSuperclass().getDeclaredFields();
            Field[] childFields = clazz.getDeclaredFields();
            Field[] fields = Stream.concat(Arrays.stream(superFields), Arrays.stream(childFields)).toArray(Field[]::new);

            for (Field field : fields) {
                if (!Modifier.isFinal(field.getModifiers())) {
                    field.setAccessible(true);
                    try {
                        field.set(to, field.get(from));
                        LOGGER.info("Assigned value: " + field.get(from) + " to field: " + field.getName());
                    } catch (IllegalAccessException e) {
                        LOGGER.error("Could not get access to field " + field.getName());
                        e.printStackTrace();
                    }
                } else {
                    LOGGER.info("Field " + field.getName() + " is final, skipping...");
                    continue;
                }
            }
        } else {
            String message = "Could not fill in fields of %s. \n Instance type to get values from (%s) is different from instance type to fill in (%s)";
            throw new IllegalArgumentException(String.format(message, name, modelClazz, clazz));
        }
    }
}
