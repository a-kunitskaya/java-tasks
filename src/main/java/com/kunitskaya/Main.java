package com.kunitskaya;

import com.kunitskaya.domain.Fridge;
import com.kunitskaya.domain.HouseholdAppliance;
import com.kunitskaya.domain.Kettle;
import com.kunitskaya.domain.TvSet;
import com.kunitskaya.domain.data.HomeLocation;
import com.kunitskaya.exceptions.ApplianceNotFoundException;
import com.kunitskaya.reflection.ClassInstantiator;
import com.kunitskaya.reflection.ReflectionExecutor;
import com.kunitskaya.service.HouseholdApplianceFinder;
import com.kunitskaya.service.HouseholdAppliancesSorter;
import com.kunitskaya.service.PowerConsumptionCounter;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Fridge fridge = new Fridge(250, "white", HomeLocation.KITCHEN, 3);
        fridge.plugIn();

        TvSet tvSet = new TvSet(200, "black", HomeLocation.LIVING_ROOM, 55);
        tvSet.plugIn();

        Kettle kettle = new Kettle(100, "White", HomeLocation.KITCHEN, 1.5);
        kettle.plugIn();

        List<HouseholdAppliance> appliances = Arrays.asList(fridge, tvSet, kettle);

        //Count power consumption for all plugged-in appliances
        PowerConsumptionCounter.countPowerConsumption(appliances);

        fridge.unplug();

        //Since fridge is unplugged it does not consume power anymore
        PowerConsumptionCounter.countPowerConsumption(appliances);

        HouseholdApplianceFinder finder = new HouseholdApplianceFinder();

        try {
            finder.findApplianceByColor(appliances, "WHITE");
            finder.findApplianceByLocation(appliances, HomeLocation.LIVING_ROOM);
        } catch (ApplianceNotFoundException e) {
            e.printStackTrace();
        }

        new HouseholdAppliancesSorter().sortByPowerConsumption(appliances);

        //M2 - Task 2 - Reflection API usage
        ReflectionExecutor.executeReflectionMethods();





    }
}
