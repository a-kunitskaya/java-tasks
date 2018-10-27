package com.kunitskaya.service.domain.appliances.implementation;

import com.kunitskaya.domain.appliances.Fridge;
import com.kunitskaya.domain.appliances.HouseholdAppliance;
import com.kunitskaya.exceptions.ByTemperatureApplianceNotFoundException;
import com.kunitskaya.exceptions.NotSupportedApplianceTypeException;
import com.kunitskaya.service.domain.ObjectsFinder;

import java.util.List;
import java.util.stream.Collectors;

import static com.kunitskaya.exceptions.ByTemperatureApplianceNotFoundException.getErrorMessage;
import static com.kunitskaya.logging.ProjectLogger.LOGGER;


@SuppressWarnings(value = "unchecked")
public class ByTemperatureFinder implements ObjectsFinder<HouseholdAppliance> {
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

        List filteredAppliances = appliances.stream()
                                                    .map(e -> (Fridge) e)
                                                    .filter(a -> a.getFreezingTemperature() == freezingTemperature)
                                                    .collect(Collectors.toList());

        if (filteredAppliances.size() != 0) {
            LOGGER.info("Found " + filteredAppliances.size() + " appliances in total by freezing temperature " + freezingTemperature + ":");

            //will produce a warning as no type is defined for List in method return type
            //but is suppressed by @SuppressWarnings(value = "unchecked") on class level
            filteredAppliances.forEach(appliance -> LOGGER.info("Found " + appliance.getClass().getSimpleName() + " by freezing temperature " + freezingTemperature));
            return filteredAppliances;
        } else {
            throw new ByTemperatureApplianceNotFoundException(getErrorMessage(String.valueOf(freezingTemperature)));
        }
    }
}
