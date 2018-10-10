package com.kunitskaya.entities;

import com.kunitskaya.entities.data.HomeLocation;

import java.util.Objects;

public class TvSet extends HouseholdAppliance {
    private double displaySize;

    public TvSet(int powerConsumption, String color, HomeLocation location, double displaySize) {
        super(powerConsumption, color, location);
        this.displaySize = displaySize;
    }

    public TvSet(int powerConsumption) {
        super(powerConsumption);
    }

    public double getDisplaySize() {
        return displaySize;
    }

    public void setDisplaySize(double displaySize) {
        this.displaySize = displaySize;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        if (!super.equals(o))
            return false;
        TvSet tvSet = (TvSet) o;
        return Double.compare(tvSet.displaySize, displaySize) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), displaySize);
    }

    @Override
    public String toString() {
        return "TvSet{" +
                "displaySize=" + displaySize +
                '}';
    }
}
