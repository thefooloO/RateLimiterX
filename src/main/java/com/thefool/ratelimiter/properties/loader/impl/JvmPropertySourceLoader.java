package com.thefool.ratelimiter.properties.loader.impl;

import com.thefool.ratelimiter.properties.PropertyConstants;
import com.thefool.ratelimiter.properties.PropertySource;
import com.thefool.ratelimiter.properties.loader.PropertySourceLoader;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class JvmPropertySourceLoader implements PropertySourceLoader {

    @Override
    public PropertySource load() {
        Map<String, Object> ratelimiterProperties = new HashMap<>();
        Properties properties = System.getProperties();
        for(String propertyName : properties.stringPropertyNames()) {
            if(propertyName.startsWith(PropertyConstants.PROPERTY_KEY_PREFIX)) {
                ratelimiterProperties.put(propertyName, properties.getProperty(propertyName));
            }
        }

        PropertySource propertySource = new PropertySource();
        propertySource.addProperties(ratelimiterProperties);
        return propertySource;
    }
}