package com.kunitskaya.module1.domain;

import com.kunitskaya.module1.HomeLocation;
import com.kunitskaya.module2.annotations.ThisCodeSmells;

import java.util.Objects;

@ThisCodeSmells(reviewer = "John")
public class Fridge extends HouseholdAppliance {

    @ThisCodeSmells()
    private transient Integer freezingTemperature;

    public Fridge(Integer powerConsumption, String color, HomeLocation location, int freezingTemperature) {
        super(powerConsumption, color, location);
        this.freezingTemperature = freezingTemperature;
    }


    public Fridge(Integer powerConsumption, Integer freezingTemperature) {
        super(powerConsumption);
        this.freezingTemperature = freezingTemperature;
    }

    public Fridge(Integer powerConsumption, Boolean isPluggedIn, String color, HomeLocation location, Integer freezingTemperature) {
        super(powerConsumption, isPluggedIn, color, location);
        this.freezingTemperature = freezingTemperature;
    }

    public Fridge(Integer powerConsumption) {
        super(powerConsumption);
    }

    public Fridge(Integer powerConsumption, int freezingTemperature) {
        super(powerConsumption);
        if (freezingTemperature > 0) {
            throw new IllegalArgumentException("Freezing temperature can't be > 0");
        } else {
            this.freezingTemperature = freezingTemperature;
        }
    }

    public Fridge() {
        super();
    }

    public Integer getFreezingTemperature() {
        return freezingTemperature;
    }

    public void setFreezingTemperature(Integer freezingTemperature) {
        this.freezingTemperature = freezingTemperature;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fridge fridge = (Fridge) o;
        return Objects.equals(freezingTemperature, fridge.freezingTemperature);
    }

    @Override
    public int hashCode() {

        return Objects.hash(freezingTemperature);
    }

    @Override
    public String toString() {
        return "Fridge{" +
                "freezingTemperature=" + freezingTemperature +
                "} " + super.toString();
    }
}
