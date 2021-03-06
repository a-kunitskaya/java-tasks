package com.kunitskaya.module1.service.exceptions;

public class ByTemperatureApplianceNotFoundException extends ApplianceNotFoundException {
    public ByTemperatureApplianceNotFoundException(String errorMessage) {
        super(errorMessage);
    }

    public static String getErrorMessage(String value) {
        String parameter = "temperature";
        return getErrorMessage(parameter, value);
    }
}
