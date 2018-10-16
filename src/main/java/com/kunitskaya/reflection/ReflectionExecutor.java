package com.kunitskaya.reflection;

import com.kunitskaya.domain.Fridge;
import com.kunitskaya.domain.HouseholdAppliance;
import com.kunitskaya.domain.Kettle;
import com.kunitskaya.domain.data.HomeLocation;

/**
 * Shows Reflection API usage
 * Created to avoid calling all these methods directly from main
 *
 */
public class ReflectionExecutor {

    public static void executeReflectionMethods() {

        //M2 - Task 2 - Reflection API usage
        HouseholdAppliance kettle = ClassInstantiator.instantiate(Kettle.class, 200);
        HouseholdAppliance fridge = ClassInstantiator.instantiate(Fridge.class);

        HouseholdAppliance modelFrigge = new Fridge(100, "red", HomeLocation.LIVING_ROOM, -4);

        FieldsManipulator.fillInFieldsWithReflection(modelFrigge, fridge);
    }
}
