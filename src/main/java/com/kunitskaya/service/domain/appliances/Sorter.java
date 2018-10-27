package com.kunitskaya.service.domain.appliances;

import com.kunitskaya.domain.appliances.HouseholdAppliance;

import java.util.List;

public interface Sorter<T> {
    void sort(List<T> appliances);
}
