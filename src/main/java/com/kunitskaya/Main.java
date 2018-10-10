package com.kunitskaya;

import com.kunitskaya.entities.Fridge;
import com.kunitskaya.entities.Kettle;
import com.kunitskaya.entities.TvSet;
import com.kunitskaya.entities.data.HomeLocation;
import com.kunitskaya.service.PowerConsumptionCounter;

public class Main {
    public static void main(String[] args) {
        Fridge fridge = new Fridge(250, "white", HomeLocation.KITCHEN, 3);
        fridge.plugIn();

        Kettle kettle = new Kettle(100, "White", HomeLocation.KITCHEN, 1.5);
        kettle.plugIn();

        TvSet tvSet = new TvSet(200, "black", HomeLocation.LIVING_ROOM, 55);
        tvSet.plugIn();

        //Count power consumption for all plugged-in appliances
        PowerConsumptionCounter.countPowerConsumption(fridge, kettle, tvSet);

        fridge.unplug();

        //Since fridge is unplugged it does not consume power anymore
        PowerConsumptionCounter.countPowerConsumption(fridge, kettle, tvSet);
    }
}
