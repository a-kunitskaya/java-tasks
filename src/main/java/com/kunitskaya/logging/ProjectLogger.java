package com.kunitskaya.logging;

import org.apache.logging.log4j.LogManager;

public class ProjectLogger
{
	public static org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger(ProjectLogger.class);

	public static void error(String message) {
		LOGGER.error(message);
	}

	public static void info(String message) {
		LOGGER.info(message);
	}

	public static void error(String message, Throwable throwable) {
		LOGGER.error(message, throwable);
	}

	public static void debug(String message) {
		LOGGER.debug(message);
	}

	public static void warn(String message) {
		LOGGER.warn(message);
	}

	public static void trace(String message) {
		LOGGER.trace(message);
	}

	public static void log(String message) {
		LOGGER.info(message);
	}
}
