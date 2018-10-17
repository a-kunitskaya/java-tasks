package com.kunitskaya;

import com.kunitskaya.domain.HomeLocation;
import com.kunitskaya.domain.appliances.Fridge;
import com.kunitskaya.domain.appliances.HouseholdAppliance;
import com.kunitskaya.domain.appliances.Kettle;
import com.kunitskaya.domain.appliances.TvSet;
import com.kunitskaya.exceptions.ApplianceNotFoundException;
import com.kunitskaya.service.implementation.ByColorFinder;
import com.kunitskaya.service.implementation.ByLocationFinder;
import com.kunitskaya.service.implementation.PowerConsumptionCounter;
import com.kunitskaya.service.implementation.SorterByPowerConsumption;

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

        TvSet tvSet = new TvSet(200, "black", HomeLocation.KITCHEN, 55);
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

        String color = "WHITE";
        try {
            List appliancesWithColor = new ByColorFinder(color).find(appliances);
            List appliancesWithLocation = new ByLocationFinder(HomeLocation.LIVING_ROOM).find(appliances);
        } catch (ApplianceNotFoundException e) {
            e.printStackTrace();
        }

        LOGGER.info("Order of items in unsorted list:");
        appliances.forEach(a -> LOGGER.info(a.getClass().getSimpleName() + ", power consumption: " + a.getPowerConsumption()));

        new SorterByPowerConsumption().sort(appliances);

        LOGGER.info("Order of items in a sorted list:");
        appliances.forEach(a -> LOGGER.info(a.getClass().getSimpleName() + ", power consumption: " + a.getPowerConsumption()));

    }
}
