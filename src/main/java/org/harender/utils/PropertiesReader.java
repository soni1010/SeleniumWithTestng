package org.harender.utils;

import java.io.*;
import java.util.Properties;
public class PropertiesReader {

    private Properties properties;

    public PropertiesReader(String filePath) {
        properties = loadProperties(filePath);
    }

    private Properties loadProperties(String filePath) {
        Properties props = new Properties();
        try (InputStream input = new FileInputStream(filePath)) {
            // Load properties from the file
            props.load(input);
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception based on exception in try
        }
        return props;
    }

    public String getProperty(String key) {
        // Get property value by key
        return properties.getProperty(key);
    }

}
