package com.kunitskaya.service;

import com.kunitskaya.domain.HouseholdAppliance;

import java.util.Arrays;
import java.util.List;

import static com.kunitskaya.logging.ProjectLogger.LOGGER;

public class PowerConsumptionCounter {
    public static int countPowerConsumption(List<HouseholdAppliance> appliances) {
        int powerConsumption = 0;

        for (HouseholdAppliance appliance : appliances) {
            int appliancePowerConsumption = appliance.getPowerConsumption();

            LOGGER.info("Power consumption for " + appliance.getClass().getSimpleName() + " = " + appliancePowerConsumption);
            powerConsumption += appliancePowerConsumption;
        }

        LOGGER.info("Total power consumption = " + powerConsumption);
        return powerConsumption;
    }

    public static int countPowerConsumption(HouseholdAppliance... appliances) {
        return countPowerConsumption(Arrays.asList(appliances));
    }
}
