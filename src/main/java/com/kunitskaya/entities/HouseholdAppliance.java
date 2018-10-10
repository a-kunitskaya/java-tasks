package com.kunitskaya.entities;

import com.kunitskaya.entities.data.HomeLocation;
import com.kunitskaya.entities.data.PluggedInable;

import java.util.Objects;

import static com.kunitskaya.logging.ProjectLogger.LOGGER;

public abstract class HouseholdAppliance implements PluggedInable
{
	private double size;
	private double powerConsumption;
	private boolean isPluggedIn;
	private String color;
	private HomeLocation location;

	public double getSize()
	{
		return size;
	}

	public void setSize(double size)
	{
		this.size = size;
	}

	public double getPowerConsumption()
	{
		return powerConsumption;
	}

	public void setPowerConsumption(double powerConsumption)
	{
		this.powerConsumption = powerConsumption;
	}

	public boolean isPluggedIn()
	{
		return isPluggedIn;
	}

	public void setPluggedIn(boolean pluggedIn)
	{
		isPluggedIn = pluggedIn;
	}

	public String getColor()
	{
		return color;
	}

	public void setColor(String color)
	{
		this.color = color;
	}

	public HomeLocation getLocation()
	{
		return location;
	}

	public void setLocation(HomeLocation location)
	{
		this.location = location;
	}

	@Override
	public boolean plugIn()
	{
		String applianceName = this.getClass().getSimpleName();
		LOGGER.info("Plugging in " + applianceName);
		setPluggedIn(true);
		LOGGER.info(applianceName + " is plugged in");
		return isPluggedIn();
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		HouseholdAppliance that = (HouseholdAppliance) o;
		return Double.compare(that.size, size) == 0 &&
				Double.compare(that.powerConsumption, powerConsumption) == 0 &&
				isPluggedIn == that.isPluggedIn &&
				Objects.equals(color, that.color) &&
				location == that.location;
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(size, powerConsumption, isPluggedIn, color, location);
	}

	@Override
	public String toString()
	{
		return "HouseholdAppliance{" +
				"size=" + size +
				", powerConsumption=" + powerConsumption +
				", isPluggedIn=" + isPluggedIn +
				", color='" + color + '\'' +
				", location=" + location +
				'}';
	}
}
