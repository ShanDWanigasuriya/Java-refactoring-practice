package com.common;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.service.AbstractService;

import java.io.IOException;
import java.util.Properties;

/**
 * The CommonUtil class provides utility methods and common resources
 * used across the application, such as logging and property management.
 */
public class CommonUtil {

    // Logger for logging application events and errors
    public static final Logger log = Logger.getLogger(AbstractService.class.getName());
    
    // Properties object to hold configuration values from a properties file
    public static final Properties properties = new Properties();

    // Static block to initialize properties from the configuration file
    static {
        try {
            // Load properties from the specified file in the classpath
            properties.load(QueryUtil.class.getResourceAsStream(CommonConstants.PROPERTY_FILE));
        } catch (IOException e) {
            // Log any IO exceptions that occur during properties file loading
            log.log(Level.SEVERE, "Failed to load properties file: " + e.getMessage(), e);
        }
    }
}
