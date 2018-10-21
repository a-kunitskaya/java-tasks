package com.kunitskaya.service.implementation;

import com.kunitskaya.domain.appliances.HouseholdAppliance;
import com.kunitskaya.exceptions.ByColorApplianceNotFoundException;
import com.kunitskaya.exceptions.ByLocationNotFoundException;
import com.kunitskaya.service.Findable;

import java.util.List;

public class ByDisplaySizeFinder implements Findable {
    @Override
    public List find(List<HouseholdAppliance> appliances) throws ByColorApplianceNotFoundException, ByLocationNotFoundException {
        return null;
    }
}
