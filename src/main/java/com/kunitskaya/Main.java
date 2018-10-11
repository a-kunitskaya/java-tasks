package com.kunitskaya;

import com.kunitskaya.entities.Fridge;
import com.kunitskaya.entities.HouseholdAppliance;
import com.kunitskaya.entities.Kettle;
import com.kunitskaya.entities.TvSet;
import com.kunitskaya.entities.data.HomeLocation;
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
        finder.findApplianceByColor(appliances, "WHITE");
        finder.findApplianceByLocation(appliances, HomeLocation.LIVING_ROOM);

        new HouseholdAppliancesSorter().sortByPowerConsumption(appliances);
    }
}
