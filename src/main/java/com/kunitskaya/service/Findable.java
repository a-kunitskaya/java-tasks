package com.kunitskaya.service;

import com.kunitskaya.domain.HouseholdAppliance;
import com.kunitskaya.domain.data.HomeLocation;
import com.kunitskaya.exceptions.ApplianceNotFoundException;

import java.util.List;

public interface Findable {
    List findApplianceByColor(List<HouseholdAppliance> appliances, String color) throws ApplianceNotFoundException;

    List findApplianceByLocation(List<HouseholdAppliance> appliances, HomeLocation location) throws ApplianceNotFoundException;
}
