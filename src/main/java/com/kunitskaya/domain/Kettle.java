package com.kunitskaya.domain;

import com.kunitskaya.domain.data.HomeLocation;
import com.kunitskaya.service.annotations.ThisCodeSmells;

import java.util.Objects;

public class Kettle extends HouseholdAppliance {
    private double volume;

    public Kettle(int powerConsumption, String color, HomeLocation location, double volume) {
        super(powerConsumption, color, location);
        this.volume = volume;
    }

    public Kettle(int powerConsumption) {
        super(powerConsumption);
    }

    public Kettle() {
        super();
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        if (!super.equals(o))
            return false;
        Kettle kettle = (Kettle) o;
        return Double.compare(kettle.volume, volume) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), volume);
    }

    @Override
    public String toString() {
        return "Kettle{" +
                "volume=" + volume +
                "} " + super.toString();
    }
}
