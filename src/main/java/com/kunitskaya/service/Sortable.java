package com.kunitskaya.service;

import com.kunitskaya.entities.HouseholdAppliance;

import java.util.List;

public interface Sortable {
    void sortByPowerConsumption(List<HouseholdAppliance> appliances);
}
