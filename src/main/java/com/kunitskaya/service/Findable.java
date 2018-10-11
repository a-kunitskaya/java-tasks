package com.kunitskaya.service;

import com.kunitskaya.entities.HouseholdAppliance;
import com.kunitskaya.entities.data.HomeLocation;

import java.util.List;

public interface Findable {
    List findApplianceByColor(List<HouseholdAppliance> appliances, String color);

    List findApplianceByLocation(List<HouseholdAppliance> appliances, HomeLocation location);
}
