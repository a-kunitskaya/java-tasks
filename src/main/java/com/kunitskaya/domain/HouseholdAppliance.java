package com.kunitskaya.domain;

import com.kunitskaya.domain.data.HomeLocation;
import com.kunitskaya.domain.data.Pluginable;
import com.kunitskaya.service.annotations.ThisCodeSmells;

import java.util.Objects;

import static com.kunitskaya.logging.ProjectLogger.LOGGER;

public abstract class HouseholdAppliance implements Pluginable {

    @ThisCodeSmells(reviewer = "David")
    private int powerConsumption;

    @ThisCodeSmells(reviewer = "Robin")
    private boolean isPluggedIn;

    private String color;
    private HomeLocation location;

    public HouseholdAppliance(int powerConsumption, String color, HomeLocation location) {
        this.powerConsumption = powerConsumption;
        this.color = color;
        this.location = location;
    }

    public HouseholdAppliance(int powerConsumption) {
        this.powerConsumption = powerConsumption;
    }

    public HouseholdAppliance() {
    }

    public int getPowerConsumption() {
        return powerConsumption;
    }

    public void setPowerConsumption(int powerConsumption) {
        this.powerConsumption = powerConsumption;
    }

    public boolean isPluggedIn() {
        return isPluggedIn;
    }

    public void setPluggedIn(boolean pluggedIn) {
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
        LOGGER.info("Plugging in " + applianceName);

        setPluggedIn(true);

        LOGGER.info(applianceName + " is plugged in");
        return isPluggedIn();
    }

    @ThisCodeSmells(reviewer = "Jess")
    @Override
    public final boolean unplug() {
        String applianceName = this.getClass().getSimpleName();
        LOGGER.info("Unplugging " + applianceName);

        setPluggedIn(false);
        setPowerConsumption(0);

        LOGGER.info(applianceName + " is unplugged");
        return isPluggedIn();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        HouseholdAppliance that = (HouseholdAppliance) o;
        return Double.compare(that.powerConsumption, powerConsumption) == 0 &&
                isPluggedIn == that.isPluggedIn &&
                Objects.equals(color, that.color) &&
                location == that.location;
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

