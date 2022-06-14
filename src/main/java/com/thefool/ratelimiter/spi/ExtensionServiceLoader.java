package com.thefool.ratelimiter.spi;

import com.thefool.ratelimiter.order.OrderComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ServiceLoader;

public class ExtensionServiceLoader {

    public static <T> T getExtension(Class<T> clazz) {
        List<T> extensionList = getExtensionList(clazz);
        if(extensionList == null || extensionList.isEmpty()) {
            return null;
        }
        return extensionList.get(0);
    }

    public static <T> List<T> getExtensionList(Class<T> clazz) {
        return load(clazz);
    }

    private static <T> List<T> load(Class<T> clazz) {
        List<T> services = new ArrayList<>();
        ServiceLoader<T> serviceLoader = ServiceLoader.load(clazz);
        for(T service : serviceLoader) services.add(service);
        Collections.sort(services, OrderComparator.instance);
        return services;
    }
}