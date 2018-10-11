package com.kunitskaya.exceptions;

import com.kunitskaya.logging.ProjectLogger;

public class CustomException extends Exception {

    public CustomException(String errorMessage) {
        super(errorMessage);
        ProjectLogger.LOGGER.error("Custom exception will be thrown... ");
    }
}
