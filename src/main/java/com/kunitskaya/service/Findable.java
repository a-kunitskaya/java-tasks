package com.kunitskaya.service;

import com.kunitskaya.domain.appliances.HouseholdAppliance;
import com.kunitskaya.domain.HomeLocation;
import com.kunitskaya.exceptions.ApplianceNotFoundException;

import java.util.List;

public interface Findable {
    List findApplianceByColor(List<HouseholdAppliance> appliances, String color) throws ApplianceNotFoundException;

    List findApplianceByLocation(List<HouseholdAppliance> appliances, HomeLocation location) throws ApplianceNotFoundException;
}
