package com.kunitskaya;

import com.kunitskaya.domain.appliances.Fridge;
import com.kunitskaya.domain.appliances.HouseholdAppliance;
import com.kunitskaya.domain.appliances.Kettle;
import com.kunitskaya.domain.appliances.TvSet;
import com.kunitskaya.domain.HomeLocation;
import com.kunitskaya.exceptions.ApplianceNotFoundException;
import com.kunitskaya.service.implementation.HouseholdApplianceFinder;
import com.kunitskaya.service.implementation.HouseholdAppliancesSorter;
import com.kunitskaya.service.implementation.PowerConsumptionCounter;

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
    }
}
