package com.kunitskaya.service;

import com.kunitskaya.domain.HouseholdAppliance;
import com.kunitskaya.domain.data.HomeLocation;
import com.kunitskaya.exceptions.ApplianceNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

import static com.kunitskaya.logging.ProjectLogger.LOGGER;

public class HouseholdApplianceFinder implements Findable {

    @Override
    public List<HouseholdAppliance> findApplianceByColor(List<HouseholdAppliance> appliances, String color) throws ApplianceNotFoundException {
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

    @Override
    public List<HouseholdAppliance> findApplianceByLocation(List<HouseholdAppliance> appliances, HomeLocation location) throws ApplianceNotFoundException {
        List<HouseholdAppliance> filteredAppliances = appliances.stream()
                                                                .filter(a -> a.getLocation().equals(location))
                                                                .collect(Collectors.toList());
        if (filteredAppliances.size() != 0) {
            LOGGER.info("Found " + filteredAppliances.size() + " appliances in total by location " + location + ":");
            filteredAppliances.forEach(appliance -> LOGGER.info("Found " + appliance.getClass().getSimpleName() + " by location " + location.name()));
            return filteredAppliances;
        } else {
            throw new ApplianceNotFoundException("No appliance is found by location: " + location.name());
        }
    }
}
