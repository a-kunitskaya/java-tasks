package com.kunitskaya.module1.service.exceptions;

import static com.kunitskaya.logging.ProjectLogger.LOGGER;

public class ApplianceNotFoundException extends Exception {
    public static final String APPLIANCE_NOT_FOUND_MESSAGE = "No appliance is found by %s: %s";

    public ApplianceNotFoundException(String errorMessage) {
        super(errorMessage);
        LOGGER.error("Custom ApplianceNotFoundException exception will be thrown... ");
    }

    public static String getErrorMessage(String parameter, String value) {
        return String.format(APPLIANCE_NOT_FOUND_MESSAGE, parameter, value);
    }
}
