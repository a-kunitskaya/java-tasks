package com.kunitskaya.exceptions;

public class NotSupportedApplianceTypeException extends Exception {
    private static final String NOT_SUPPORTED_APPLIANCE_MESSAGE = "Appliance of type %s is not supported. Expected type: %s";

    public NotSupportedApplianceTypeException(String message) {
        super(message);
    }

    public static String getErrorMessage(String invalidType, String validType) {
        return String.format(NOT_SUPPORTED_APPLIANCE_MESSAGE, invalidType, validType);
    }
}
