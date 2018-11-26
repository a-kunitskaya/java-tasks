package com.kunitskaya.module1.domain;

import com.kunitskaya.module1.HomeLocation;
import com.kunitskaya.module2.annotations.UseStackOnly;

import java.util.Objects;

public class TvSet extends HouseholdAppliance {

    @UseStackOnly
    private transient Double displaySize;

    public TvSet(Integer powerConsumption, String color, HomeLocation location, double displaySize) {
        super(powerConsumption, color, location);
        this.displaySize = displaySize;
    }

    public TvSet(Integer powerConsumption) {
        super(powerConsumption);
    }

    public TvSet() {
        super();
    }

    public Double getDisplaySize() {
        return displaySize;
    }

    public void setDisplaySize(Double displaySize) {
        this.displaySize = displaySize;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TvSet tvSet = (TvSet) o;
        return Objects.equals(displaySize, tvSet.displaySize);
    }

    @Override
    public int hashCode() {

        return Objects.hash(displaySize);
    }

    @Override
    public String toString() {
        return "TvSet{" +
                "displaySize=" + displaySize +
                "} " + super.toString();
    }
}
