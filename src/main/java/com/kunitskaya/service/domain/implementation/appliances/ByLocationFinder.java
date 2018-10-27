package com.kunitskaya.service.domain.implementation.appliances;

import com.kunitskaya.domain.HomeLocation;
import com.kunitskaya.domain.appliances.HouseholdAppliance;
import com.kunitskaya.exceptions.ByLocationNotFoundException;
import com.kunitskaya.service.domain.ObjectsFinder;

import java.util.List;
import java.util.stream.Collectors;

import static com.kunitskaya.logging.ProjectLogger.LOGGER;

public class ByLocationFinder implements ObjectsFinder<HouseholdAppliance> {
    private HomeLocation location;

    public ByLocationFinder(HomeLocation location) {
        this.location = location;
    }

    @Override
    public List find(List<HouseholdAppliance> appliances) throws ByLocationNotFoundException {
        List<HouseholdAppliance> filteredAppliances = appliances.stream()
                                                                .filter(a -> a.getLocation().equals(location))
                                                                .collect(Collectors.toList());
        if (filteredAppliances.size() != 0) {
            LOGGER.info("Found " + filteredAppliances.size() + " appliances in total by location " + location + ":");
            filteredAppliances.forEach(appliance -> LOGGER.info("Found " + appliance.getClass().getSimpleName() + " by location " + location.name()));
            return filteredAppliances;
        } else {
            throw new ByLocationNotFoundException(ByLocationNotFoundException.getErrorMessage(location.name()));
        }
    }
}
