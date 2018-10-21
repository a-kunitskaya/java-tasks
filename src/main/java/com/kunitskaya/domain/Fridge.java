package com.kunitskaya.domain;

import com.kunitskaya.domain.data.HomeLocation;
import com.kunitskaya.service.annotations.ThisCodeSmells;

import java.util.Objects;

@ThisCodeSmells(reviewer = "John")
public class Fridge extends HouseholdAppliance {

    @ThisCodeSmells()
    private int freezingTemperature;

    public Fridge(int powerConsumption, String color, HomeLocation location, int freezingTemperature) {
        super(powerConsumption, color, location);
        this.freezingTemperature = freezingTemperature;
    }

    public Fridge(int powerConsumption) {
        super(powerConsumption);
    }

    public Fridge() {
        super();
    }

    public int getFreezingTemperature() {
        return freezingTemperature;
    }

    public void setFreezingTemperature(int freezingTemperature) {
        this.freezingTemperature = freezingTemperature;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        if (!super.equals(o))
            return false;
        Fridge fridge = (Fridge) o;
        return freezingTemperature == fridge.freezingTemperature;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), freezingTemperature);
    }

    @Override
    public String toString() {
        return "Fridge{" +
                "freezingTemperature=" + freezingTemperature +
                "} " + super.toString();
    }
}
