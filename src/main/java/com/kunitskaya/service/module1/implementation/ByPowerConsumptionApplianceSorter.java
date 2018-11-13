package com.kunitskaya.service.module1.implementation;

import com.kunitskaya.domain.module1.appliances.HouseholdAppliance;
import com.kunitskaya.service.module1.Sorter;
import com.kunitskaya.service.module2.annotations.UseArrayList;

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
