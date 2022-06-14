package com.thefool.ratelimiter.properties;

import java.util.LinkedHashMap;
import java.util.Map;

public class PropertySource {
    private final Map<String, Object> properties = new LinkedHashMap<String, Object>();

    public void addProperties(Map<String, Object> properties) {
        this.properties.putAll(properties);
    }

    public void combinePropertySource(PropertySource propertySource) {
        if(propertySource == null || propertySource.getProperties().isEmpty()) {
            return;
        }
        addProperties(propertySource.getProperties());
    }

    public Map<String, Object> getProperties() {
        return properties;
    }

    public String getPropertyStringValue(String name) {
        Object val = properties.get(name);
        if(val == null)
            return null;
        return String.valueOf(val);
    }
}