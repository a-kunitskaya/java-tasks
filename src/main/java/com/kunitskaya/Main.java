package com.kunitskaya;

import com.kunitskaya.domain.HomeLocation;
import com.kunitskaya.domain.appliances.Fridge;
import com.kunitskaya.domain.appliances.HouseholdAppliance;
import com.kunitskaya.domain.appliances.Kettle;
import com.kunitskaya.domain.appliances.TvSet;
import com.kunitskaya.exceptions.ApplianceNotFoundException;
import com.kunitskaya.exceptions.ByTemperatureApplianceNotFoundException;
import com.kunitskaya.exceptions.NotSupportedApplianceTypeException;
import com.kunitskaya.service.implementation.*;
import com.kunitskaya.reflection.ReflectionExecutor;
import com.kunitskaya.service.HouseholdApplianceFinder;
import com.kunitskaya.service.HouseholdAppliancesSorter;
import com.kunitskaya.service.PowerConsumptionCounter;

import java.util.Arrays;
import java.util.List;

import static com.kunitskaya.domain.appliances.HouseholdAppliance.PLUGIN_MESSAGE;
import static com.kunitskaya.domain.appliances.HouseholdAppliance.UNPLUG_MESSAGE;
import static com.kunitskaya.logging.ProjectLogger.LOGGER;

public class Main {
    public static void main(String[] args) {
        Fridge fridge = new Fridge(250, "Fridge", HomeLocation.KITCHEN, 3);

        LOGGER.info(String.format(PLUGIN_MESSAGE, Fridge.class.getSimpleName()));
        fridge.plugIn();

        TvSet tvSet = new TvSet(200, "black", HomeLocation.LIVING_ROOM, 55);
        LOGGER.info(String.format(PLUGIN_MESSAGE, TvSet.class.getSimpleName()));
        tvSet.plugIn();

        Kettle kettle = new Kettle(100, "White", HomeLocation.KITCHEN, 1.5);
        LOGGER.info(String.format(PLUGIN_MESSAGE, Kettle.class.getSimpleName()));
        kettle.plugIn();

        List<HouseholdAppliance> appliances = Arrays.asList(fridge, tvSet, kettle);

        //Count power consumption for all plugged-in appliances
        PowerConsumptionCounter.countPowerConsumption(appliances);

        LOGGER.info(String.format(UNPLUG_MESSAGE, Fridge.class.getSimpleName()));
        fridge.unplug();

        //Since fridge is unplugged it does not consume power anymore
        PowerConsumptionCounter.countPowerConsumption(appliances);

        try {
            String color = "WHITE";
            List appliancesWithColor = new ByColorFinder(color).find(appliances);
            List appliancesWithLocation = new ByLocationFinder(HomeLocation.LIVING_ROOM).find(appliances);
            if (appliancesWithColor.size() != 0 && appliancesWithLocation.size() != 0) {
                LOGGER.info("Some appliances were found by color and location!");
            }
        } catch (ApplianceNotFoundException e) {
            e.printStackTrace();
        }

        LOGGER.info("Order of items in unsorted list:");
        appliances.forEach(a -> LOGGER.info(a.getClass().getSimpleName() + ", power consumption: " + a.getPowerConsumption()));

        new SorterByPowerConsumption().sort(appliances);

        LOGGER.info("Order of items in a sorted list:");
        appliances.forEach(a -> LOGGER.info(a.getClass().getSimpleName() + ", power consumption: " + a.getPowerConsumption()));

        try {
            int freezingTemperature = -3;
            List appliancesWithTemperature = new ByTemperatureFinder(freezingTemperature).find(appliances);
        } catch (NotSupportedApplianceTypeException | ByTemperatureApplianceNotFoundException e) {
            e.printStackTrace();
        } finally {
            LOGGER.info("Expected exception - NotSupportedApplianceTypeException");
        }
        new HouseholdAppliancesSorter().sortByPowerConsumption(appliances);

        //M2 - Task 2 - Reflection API usage
        ReflectionExecutor.executeReflectionMethods();



    }
}
