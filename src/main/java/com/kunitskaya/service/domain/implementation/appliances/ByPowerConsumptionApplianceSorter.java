package com.kunitskaya.service.domain.implementation.appliances;

import com.kunitskaya.domain.appliances.HouseholdAppliance;
import com.kunitskaya.service.annotations.UseArrayList;
import com.kunitskaya.service.domain.Sorter;

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
