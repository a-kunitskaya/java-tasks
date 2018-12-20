package com.kunitskaya.module1.domain;

import com.kunitskaya.module1.HomeLocation;
import com.kunitskaya.module1.Pluggable;
import com.kunitskaya.module2.annotations.ThisCodeSmells;

import java.io.Serializable;
import java.util.Objects;

import static com.kunitskaya.logging.ProjectLogger.LOGGER;

public abstract class HouseholdAppliance implements Pluggable, Serializable {
    public static final String PLUGIN_MESSAGE = "Plugging in %s instance...";
    public static final String UNPLUG_MESSAGE = "Unplugging %s instance...";

    @ThisCodeSmells(reviewer = "David")
    private transient Integer powerConsumption;

    @ThisCodeSmells(reviewer = "Robin")
    private Boolean isPluggedIn;

    private String color;
    private HomeLocation location;

    public HouseholdAppliance(Integer powerConsumption, String color, HomeLocation location) {
        if (powerConsumption < 0) {
            throw new IllegalArgumentException("Power consumption can't be < 0");
        } else {
            this.powerConsumption = powerConsumption;
        }
        String nonDigitPattern = "\\D+";
        if (color.isEmpty() || !color.matches(nonDigitPattern)) {
            throw new IllegalArgumentException("Color should consist of > 0 word characters");
        } else {
            this.color = color;
        }
        this.location = location;
    }

    public HouseholdAppliance(Integer powerConsumption) {
        if (powerConsumption < 0) {
            throw new IllegalArgumentException("Power consumption can't be < 0");
        } else {
            this.powerConsumption = powerConsumption;
        }
    }

    public HouseholdAppliance(Integer powerConsumption, Boolean isPluggedIn, String color, HomeLocation location) {
        this.powerConsumption = powerConsumption;
        this.isPluggedIn = isPluggedIn;
        this.color = color;
        this.location = location;
    }

    public HouseholdAppliance() {
    }

    public Integer getPowerConsumption() {
        return powerConsumption;
    }

    public void setPowerConsumption(Integer powerConsumption) {
        if (powerConsumption < 0) {
            throw new IllegalArgumentException("Power consumption can't be < 0");
        } else {
            this.powerConsumption = powerConsumption;
        }
    }

    public Boolean isPluggedIn() {
        return isPluggedIn;
    }

    public void setPluggedIn(Boolean pluggedIn) {
        isPluggedIn = pluggedIn;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public HomeLocation getLocation() {
        return location;
    }

    public void setLocation(HomeLocation location) {
        this.location = location;
    }

    @ThisCodeSmells(reviewer = "Jack")
    @Override
    public final boolean plugIn() {
        String applianceName = this.getClass().getSimpleName();
        setPluggedIn(true);

        LOGGER.info(applianceName + " is plugged in");
        return isPluggedIn();
    }

    @ThisCodeSmells(reviewer = "Jess")
    @Override
    public final boolean unplug() {
        String applianceName = this.getClass().getSimpleName();
        setPluggedIn(false);
        setPowerConsumption(0);

        LOGGER.info(applianceName + " is unplugged");
        return isPluggedIn();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HouseholdAppliance appliance = (HouseholdAppliance) o;
        return Objects.equals(powerConsumption, appliance.powerConsumption) &&
                Objects.equals(isPluggedIn, appliance.isPluggedIn) &&
                Objects.equals(color, appliance.color) &&
                location == appliance.location;
    }

    @Override
    public int hashCode() {

        return Objects.hash(powerConsumption, isPluggedIn, color, location);
    }

    @Override
    public String toString() {
        return "HouseholdAppliance{" +
                "powerConsumption=" + powerConsumption +
                ", isPluggedIn=" + isPluggedIn +
                ", color='" + color + '\'' +
                ", location=" + location +
                '}';
    }
}
