package com.kunitskaya.service;

import com.kunitskaya.domain.HouseholdAppliance;

import java.util.List;

public interface Sortable {
    void sortByPowerConsumption(List<HouseholdAppliance> appliances);
}
