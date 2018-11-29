package com.kunitskaya.module8;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static com.kunitskaya.logging.ProjectLogger.LOGGER;

public class ConfigProvider {
    private static final String PROPERTIES_PATH = "src/main/resources/module8/jdbc.properties";

    private Properties properties = new Properties();

    public ConfigProvider() {
        loadProperties();
    }

    private void loadProperties() {
        try (InputStream inputStream = new FileInputStream(PROPERTIES_PATH)) {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        LOGGER.info("Successfully loaded properties from: " + PROPERTIES_PATH);
    }

    public String getDBUsername() {
        return properties.getProperty("jdbc.username");
    }

    public String getDBPassword() {
        return properties.getProperty("jdbc.password");
    }

    public String getDBUrl() {
        return properties.getProperty("jdbc.url");
    }
}
