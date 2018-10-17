package com.kunitskaya.service;

import com.kunitskaya.domain.appliances.HouseholdAppliance;
import com.kunitskaya.exceptions.ApplianceNotFoundException;

import java.util.List;

public interface Findable {
    List find(List<HouseholdAppliance> appliances) throws ApplianceNotFoundException;

   // List findApplianceByLocation(List<HouseholdAppliance> appliances, HomeLocation location) throws ApplianceNotFoundException;
}
