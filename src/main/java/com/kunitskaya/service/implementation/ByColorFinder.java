package com.kunitskaya.service.implementation;

import com.kunitskaya.domain.appliances.HouseholdAppliance;
import com.kunitskaya.exceptions.ApplianceNotFoundException;
import com.kunitskaya.service.Findable;

import java.util.List;
import java.util.stream.Collectors;

import static com.kunitskaya.logging.ProjectLogger.LOGGER;

public class ByColorFinder implements Findable {
    private String color;

    public ByColorFinder(String color) {
        this.color = color;
    }

    @Override
    public List find(List<HouseholdAppliance> appliances) throws ApplianceNotFoundException {
        List<HouseholdAppliance> filteredAppliances = appliances.stream()
                                                                .filter(a -> a.getColor().equalsIgnoreCase(color))
                                                                .collect(Collectors.toList());
        if (filteredAppliances.size() != 0) {
            LOGGER.info("Found " + filteredAppliances.size() + " appliances in total by color " + color + ":");
            filteredAppliances.forEach(appliance -> LOGGER.info("Found " + appliance.getClass().getSimpleName() + " by color " + color));
            return filteredAppliances;
        } else {
            throw new ApplianceNotFoundException("No appliance is found by color: " + color);
        }
    }
}
