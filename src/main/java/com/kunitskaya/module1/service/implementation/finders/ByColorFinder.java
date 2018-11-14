package com.kunitskaya.module1.service.implementation.finders;

import com.kunitskaya.module1.domain.HouseholdAppliance;
import com.kunitskaya.module1.service.ObjectsFinder;
import com.kunitskaya.module1.service.exceptions.ByColorApplianceNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

import static com.kunitskaya.logging.ProjectLogger.LOGGER;

public class ByColorFinder implements ObjectsFinder<HouseholdAppliance> {
    private String color;

    public ByColorFinder(String color) {
        this.color = color;
    }

    @Override
    public List find(List<HouseholdAppliance> appliances) throws ByColorApplianceNotFoundException {
        List<HouseholdAppliance> filteredAppliances = appliances.stream()
                                                                .filter(a -> a.getColor().equalsIgnoreCase(color))
                                                                .collect(Collectors.toList());
        if (filteredAppliances.size() != 0) {
            LOGGER.info("Found " + filteredAppliances.size() + " domain in total by color " + color + ":");
            filteredAppliances.forEach(appliance -> LOGGER.info("Found " + appliance.getClass().getSimpleName() + " by color " + color));
            return filteredAppliances;
        } else {
            throw new ByColorApplianceNotFoundException(ByColorApplianceNotFoundException.getErrorMessage(color));
        }
    }
}
