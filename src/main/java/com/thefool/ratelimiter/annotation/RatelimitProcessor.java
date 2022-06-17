package com.thefool.ratelimiter.annotation;

import com.thefool.ratelimiter.factories.RateLimiterBeansFactory;
import lombok.Data;
import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.reflections.util.ConfigurationBuilder;

import java.lang.reflect.Method;
import java.util.*;

@Data
public class RatelimitProcessor {

    private Reflections reflections;
    private Map<Class, List<Method>> classMethodMap;

    public RatelimitProcessor() {
        ConfigurationBuilder configurationBuilder = new ConfigurationBuilder()
                .forPackages(RateLimiterBeansFactory.context.getRatelimiterConfig().getBasePackages())
                .addScanners(new MethodAnnotationsScanner());
        reflections = new Reflections(configurationBuilder);
        classMethodMap = new HashMap<>();
    }

    public void process() {
        toMap();
    }

    public void toMap() {
        Set<Method> methods = reflections.getMethodsAnnotatedWith(Ratelimit.class);
        for(Method method : methods) {
            Class clz = method.getDeclaringClass();
            if(classMethodMap.containsKey(clz)) {
                classMethodMap.get(clz).add(method);
            }
            else {
                classMethodMap.put(clz, Arrays.asList(method));
            }
        }
    }



    public void print() {
        for(Map.Entry<Class, List<Method>> entry : classMethodMap.entrySet()) {
            System.out.println("key:" + entry.getKey());
            System.out.print("value:");
            for(Method method : entry.getValue()) {
                System.out.print(method.getName() + " ");
            }
        }
    }
}