package com.kunitskaya.domain.appliances;

import com.kunitskaya.domain.HomeLocation;

import java.util.Objects;

public class Kettle extends HouseholdAppliance {
    private Double volume;

    public Kettle(Integer powerConsumption, String color, HomeLocation location, Double volume) {
        super(powerConsumption, color, location);
        this.volume = volume;
    }

    public Kettle(Integer powerConsumption) {
        super(powerConsumption);
    }

    public Kettle() {
        super();
    }

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Kettle kettle = (Kettle) o;
        return Objects.equals(volume, kettle.volume);
    }

    @Override
    public int hashCode() {

        return Objects.hash(volume);
    }

    @Override
    public String toString() {
        return "Kettle{" +
                "volume=" + volume +
                "} " + super.toString();
    }
}
