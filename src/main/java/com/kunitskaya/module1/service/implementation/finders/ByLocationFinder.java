package com.kunitskaya.module1.service.implementation.finders;

import com.kunitskaya.module1.HomeLocation;
import com.kunitskaya.module1.domain.HouseholdAppliance;
import com.kunitskaya.module1.service.ObjectsFinder;
import com.kunitskaya.module1.service.exceptions.ByLocationNotFoundException;

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
            LOGGER.info("Found " + filteredAppliances.size() + " domain in total by location " + location + ":");
            filteredAppliances.forEach(appliance -> LOGGER.info("Found " + appliance.getClass().getSimpleName() + " by location " + location.name()));
            return filteredAppliances;
        } else {
            throw new ByLocationNotFoundException(ByLocationNotFoundException.getErrorMessage(location.name()));
        }
    }
}
