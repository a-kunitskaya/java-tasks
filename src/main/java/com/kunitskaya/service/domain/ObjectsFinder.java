package com.kunitskaya.service.domain;

import com.kunitskaya.exceptions.ByColorApplianceNotFoundException;
import com.kunitskaya.exceptions.ByLocationNotFoundException;
import com.kunitskaya.exceptions.ByTemperatureApplianceNotFoundException;
import com.kunitskaya.exceptions.NotSupportedApplianceTypeException;

import java.util.List;

@FunctionalInterface
public interface ObjectsFinder<T> {
    List<T> find(List<T> appliances) throws ByColorApplianceNotFoundException, ByLocationNotFoundException, NotSupportedApplianceTypeException, ByTemperatureApplianceNotFoundException;
}
