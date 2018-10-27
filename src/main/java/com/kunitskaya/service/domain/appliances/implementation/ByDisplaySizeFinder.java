package com.kunitskaya.service.domain.appliances.implementation;

import com.kunitskaya.domain.appliances.HouseholdAppliance;
import com.kunitskaya.exceptions.ByColorApplianceNotFoundException;
import com.kunitskaya.exceptions.ByLocationNotFoundException;
import com.kunitskaya.service.domain.ObjectsFinder;

import java.util.List;

public class ByDisplaySizeFinder implements ObjectsFinder<HouseholdAppliance> {
    @Override
    public List find(List<HouseholdAppliance> appliances) throws ByColorApplianceNotFoundException, ByLocationNotFoundException {
        return null;
    }
}
