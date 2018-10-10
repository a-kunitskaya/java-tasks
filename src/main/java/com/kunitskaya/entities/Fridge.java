package com.kunitskaya.entities;

import java.util.Objects;

public class Fridge extends HouseholdAppliance
{
	private int temperature;

	public int getTemperature()
	{
		return temperature;
	}

	public void setTemperature(int temperature)
	{
		this.temperature = temperature;
	}
	@Override
	public boolean equals(Object o)
	{
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		if (!super.equals(o))
			return false;
		Fridge fridge = (Fridge) o;
		return temperature == fridge.temperature;
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(super.hashCode(), temperature);
	}

	@Override
	public String toString()
	{
		return "Fridge{" +
				"temperature=" + temperature +
				'}';
	}
}
