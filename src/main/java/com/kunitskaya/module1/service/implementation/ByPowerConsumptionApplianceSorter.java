package com.kunitskaya.module1.service.implementation;

import com.kunitskaya.module1.domain.HouseholdAppliance;
import com.kunitskaya.module1.service.Sorter;
import com.kunitskaya.module2.annotations.UseArrayList;

import java.util.Comparator;
import java.util.List;

import static com.kunitskaya.logging.ProjectLogger.LOGGER;

public class ByPowerConsumptionApplianceSorter implements Sorter<HouseholdAppliance> {

    @UseArrayList
    @Override
    public void sort(List<HouseholdAppliance> appliances) {
        LOGGER.info("Order of items in unsorted list:");
        appliances.forEach(a -> LOGGER.info(a.getClass().getSimpleName() + ", power consumption: " + a.getPowerConsumption()));

        appliances.sort(Comparator.comparingDouble(HouseholdAppliance::getPowerConsumption));

        LOGGER.info("Order of items in a sorted list:");
        appliances.forEach(a -> LOGGER.info(a.getClass().getSimpleName() + ", power consumption: " + a.getPowerConsumption()));
    }
}
