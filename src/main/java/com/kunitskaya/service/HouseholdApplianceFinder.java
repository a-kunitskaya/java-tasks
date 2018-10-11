package com.kunitskaya.service;

import com.kunitskaya.entities.HouseholdAppliance;
import com.kunitskaya.entities.data.HomeLocation;

import java.util.List;
import java.util.stream.Collectors;

import static com.kunitskaya.logging.ProjectLogger.LOGGER;

public class HouseholdApplianceFinder implements Findable {

    @Override
    public List<HouseholdAppliance> findApplianceByColor(List<HouseholdAppliance> appliances, String color) {
        List<HouseholdAppliance> filteredAppliances = appliances.stream().filter(a -> a.getColor().equalsIgnoreCase(color))
                .collect(Collectors.toList());

        if (filteredAppliances.size() != 0) {
            LOGGER.info("Found " + filteredAppliances.size() + " appliances in total by color " + color + ":");

            for(HouseholdAppliance appliance : filteredAppliances){
                LOGGER.info("Found " + appliance.getClass().getSimpleName() + " by color " + color);
            }
        } else {
//            throw new ApplianceNotFoundException();
        }
        return filteredAppliances;
    }

    @Override
    public List<HouseholdAppliance> findApplianceByLocation(List<HouseholdAppliance> appliances, HomeLocation location) {
        List<HouseholdAppliance> filteredAppliances = appliances.stream().filter(a -> a.getLocation().equals(location))
                .collect(Collectors.toList());

        if (filteredAppliances.size() != 0) {
            LOGGER.info("Found " + filteredAppliances.size() + " appliances in total by location " + location + ":");

            for(HouseholdAppliance appliance : filteredAppliances){
                LOGGER.info("Found " + appliance.getClass().getSimpleName() + " by location " + location.name());
            }
        } else {
//            throw new ApplianceNotFoundException();
        }
        return filteredAppliances;
    }
}
