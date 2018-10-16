package com.kunitskaya.service.implementation;

import com.kunitskaya.domain.appliances.HouseholdAppliance;
import com.kunitskaya.service.Sortable;

import java.util.Comparator;
import java.util.List;

import static com.kunitskaya.logging.ProjectLogger.LOGGER;

public class HouseholdAppliancesSorter implements Sortable {
    @Override
    public void sortByPowerConsumption(List<HouseholdAppliance> appliances) {
        LOGGER.info("Order of items in unsorted list:");
        appliances.forEach(a -> LOGGER.info(a.getClass().getSimpleName() + ", power consumption: " + a.getPowerConsumption()));

        appliances.sort(Comparator.comparingDouble(HouseholdAppliance::getPowerConsumption));

        LOGGER.info("Order of items in a sorted list:");
        appliances.forEach(a -> LOGGER.info(a.getClass().getSimpleName() + ", power consumption: " + a.getPowerConsumption()));
    }
}
