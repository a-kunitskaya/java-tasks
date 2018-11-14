package com.kunitskaya;

import com.kunitskaya.module1.HomeLocation;
import com.kunitskaya.module1.domain.Fridge;
import com.kunitskaya.module1.domain.HouseholdAppliance;
import com.kunitskaya.module1.domain.Kettle;
import com.kunitskaya.module1.domain.TvSet;
import com.kunitskaya.module1.service.exceptions.ApplianceNotFoundException;
import com.kunitskaya.module1.service.exceptions.ByTemperatureApplianceNotFoundException;
import com.kunitskaya.module1.service.exceptions.NotSupportedApplianceTypeException;
import com.kunitskaya.module1.service.implementation.ByPowerConsumptionApplianceSorter;
import com.kunitskaya.module1.service.implementation.PowerConsumptionCounter;
import com.kunitskaya.module1.service.implementation.finders.ByColorFinder;
import com.kunitskaya.module1.service.implementation.finders.ByLocationFinder;
import com.kunitskaya.module1.service.implementation.finders.ByTemperatureFinder;

import java.util.Arrays;
import java.util.List;

import static com.kunitskaya.module1.domain.HouseholdAppliance.PLUGIN_MESSAGE;
import static com.kunitskaya.module1.domain.HouseholdAppliance.UNPLUG_MESSAGE;
import static com.kunitskaya.logging.ProjectLogger.LOGGER;

public class MainModule1 {
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

        //Count power consumption for all plugged-in domain
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
                LOGGER.info("Some domain were found by color and location!");
            }
        } catch (ApplianceNotFoundException e) {
            e.printStackTrace();
        }

        LOGGER.info("Order of items in unsorted list:");
        appliances.forEach(a -> LOGGER.info(a.getClass().getSimpleName() + ", power consumption: " + a.getPowerConsumption()));

        new ByPowerConsumptionApplianceSorter().sort(appliances);

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
    }
}
