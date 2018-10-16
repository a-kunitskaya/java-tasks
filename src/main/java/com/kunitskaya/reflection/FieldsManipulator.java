package com.kunitskaya.reflection;

import com.kunitskaya.domain.HouseholdAppliance;
import com.kunitskaya.logging.ProjectLogger;

import java.lang.reflect.Field;

/**
 * Manipulates class fields with reflection
 */
public class FieldsManipulator {

    // fills in fields of the class with values ?

    //TODO: join 2 arrays!


    public static void fillInFieldsWithReflection(HouseholdAppliance instanceToGetValuesFrom, HouseholdAppliance instanceToFill) {
        Class<? extends HouseholdAppliance> modelClazz = instanceToGetValuesFrom.getClass();
        Class<? extends HouseholdAppliance> clazz = instanceToFill.getClass();

        if (modelClazz.getSimpleName().equals(clazz.getSimpleName())) {
            int arraySize = clazz.getDeclaredFields().length + clazz.getSuperclass().getDeclaredFields().length;
            Field[] fields = new Field[arraySize];
            Field[] superFields = clazz.getSuperclass().getDeclaredFields();
            Field[] childFields = clazz.getDeclaredFields();

            for(int i = 0; i < arraySize; i++) {
                fields[i] = superFields[i];
            }



            for (Field field : fields){
                field.setAccessible(true);
                try {
                    field.set(instanceToFill, field.get(instanceToGetValuesFrom));
                } catch (IllegalAccessException e) {
                    ProjectLogger.LOGGER.info("Could not get access to field " + field.getName());
                    e.printStackTrace();
                }
            }
        } else {
            String message = "Instance to get values from (%s) is different from instance to fill in (%s)";
            throw new IllegalArgumentException(String.format(message, modelClazz, clazz));
        }
    }
}
