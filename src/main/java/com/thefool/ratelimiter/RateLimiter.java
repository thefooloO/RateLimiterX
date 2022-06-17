package com.thefool.ratelimiter;

import com.thefool.ratelimiter.annotation.Ratelimit;
import com.thefool.ratelimiter.factories.RateLimiterBeansFactory;
import com.thefool.ratelimiter.rule.struct.UniformRuleConfigMapping;
import lombok.Data;
import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.reflections.util.ConfigurationBuilder;

import java.lang.reflect.Method;
import java.util.Set;

@Data
public class RateLimiter {

    UniformRuleConfigMapping uniformRuleConfigMapping;

    public RateLimiter() {
        uniformRuleConfigMapping = RateLimiterBeansFactory.context.obtainRuleConfigSource().load();
    }



    @Ratelimit("test-static")
    public static void testStatic() {}

    @Ratelimit("test")
    public void test(){}

    public static void main(String[] args) {
        Reflections reflections = new Reflections(new ConfigurationBuilder().forPackages("com.thefool.ratelimiter").addScanners(new MethodAnnotationsScanner()));
        Set<Method> methods = reflections.getMethodsAnnotatedWith(Ratelimit.class);
        for(Method method : methods) {
            System.out.println(method.getAnnotation(Ratelimit.class).value());
        }
    }
}