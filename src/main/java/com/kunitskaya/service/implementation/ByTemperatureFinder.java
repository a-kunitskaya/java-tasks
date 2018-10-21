package com.kunitskaya.service.implementation;

import com.kunitskaya.domain.appliances.Fridge;
import com.kunitskaya.domain.appliances.HouseholdAppliance;
import com.kunitskaya.exceptions.ByTemperatureApplianceNotFoundException;
import com.kunitskaya.exceptions.NotSupportedApplianceTypeException;
import com.kunitskaya.service.Findable;

import java.util.List;
import java.util.stream.Collectors;

import static com.kunitskaya.exceptions.ByTemperatureApplianceNotFoundException.getErrorMessage;
import static com.kunitskaya.logging.ProjectLogger.LOGGER;

public class ByTemperatureFinder implements Findable {
    private int freezingTemperature;

    public ByTemperatureFinder(int freezingTemperature) {
        this.freezingTemperature = freezingTemperature;
    }

    @Override
    public List find(List<HouseholdAppliance> appliances) throws NotSupportedApplianceTypeException, ByTemperatureApplianceNotFoundException {

        for (HouseholdAppliance appliance : appliances) {
            if (!(appliance instanceof Fridge)) {
                String invalidType = appliance.getClass().getSimpleName();
                String validType = Fridge.class.getSimpleName();
                throw new NotSupportedApplianceTypeException(NotSupportedApplianceTypeException.getErrorMessage(invalidType, validType));
            }
        }

        List<Fridge> filteredAppliances = appliances.stream()
                                                    .map(e -> (Fridge) e)
                                                    .filter(a -> a.getFreezingTemperature() == freezingTemperature)
                                                    .collect(Collectors.toList());

        if (filteredAppliances.size() != 0) {
            LOGGER.info("Found " + filteredAppliances.size() + " appliances in total by freezing temperature " + freezingTemperature + ":");
            filteredAppliances.forEach(appliance -> LOGGER.info("Found " + appliance.getClass().getSimpleName() + " by freezing temperature " + freezingTemperature));
            return filteredAppliances;
        } else {
            throw new ByTemperatureApplianceNotFoundException(getErrorMessage(String.valueOf(freezingTemperature)));
        }
    }
}
