package com.quantity.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ApplicationConfig {

    private static final Properties properties =
            new Properties();

    static {

        try (InputStream input =
                     ApplicationConfig.class
                             .getClassLoader()
                             .getResourceAsStream(
                                     "application.properties")) {

            properties.load(input);

        } catch (IOException e) {

            throw new RuntimeException(e);
        }
    }

    public static String getProperty(String key) {

        return properties.getProperty(key);
    }
}