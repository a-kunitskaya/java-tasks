package com.kunitskaya.service;

import com.kunitskaya.domain.appliances.HouseholdAppliance;
import com.kunitskaya.exceptions.ByColorApplianceNotFoundException;
import com.kunitskaya.exceptions.ByLocationNotFoundException;

import java.util.List;

public interface Findable {
    List find(List<HouseholdAppliance> appliances) throws ByColorApplianceNotFoundException, ByLocationNotFoundException;

    // List findApplianceByLocation(List<HouseholdAppliance> appliances, HomeLocation location) throws ByColorApplianceNotFoundException;
}
