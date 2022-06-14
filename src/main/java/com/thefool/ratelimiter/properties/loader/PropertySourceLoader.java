package com.thefool.ratelimiter.properties.loader;

import com.thefool.ratelimiter.properties.PropertySource;

public interface PropertySourceLoader {
    PropertySource load();
}