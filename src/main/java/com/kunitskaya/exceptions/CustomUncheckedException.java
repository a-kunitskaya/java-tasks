package com.kunitskaya.exceptions;

import com.kunitskaya.logging.ProjectLogger;

public class CustomUncheckedException extends RuntimeException {

    public CustomUncheckedException(String errorMessage){
        super(errorMessage);
        ProjectLogger.LOGGER.error("Custom exception will be thrown... ");
    }
}
