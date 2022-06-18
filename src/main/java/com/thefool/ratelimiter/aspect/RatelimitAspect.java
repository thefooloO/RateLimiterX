package com.thefool.ratelimiter.aspect;

import com.thefool.ratelimiter.annotation.Ratelimit;
import com.thefool.ratelimiter.factories.RateLimiterBeansFactory;
import com.thefool.ratelimiter.rule.struct.UniformRuleConfig;
import com.thefool.ratelimiter.rule.struct.UniformRuleConfigMapping;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@Aspect
public class RatelimitAspect {

    private static UniformRuleConfigMapping uniformRuleConfigMapping = RateLimiterBeansFactory.context.obtainRuleConfigSource().load();
    private static Map<String, UniformRuleConfig> configMap = new HashMap<>();

    static {
        uniformRuleConfigMapping.getConfigs().forEach(config -> {
            configMap.put(config.getName(), config);
        });
    }

    @Pointcut("@annotation(com.thefool.ratelimiter.annotation.Ratelimit)")
    public void jointPoint() {}

    @Before("jointPoint()")
    public void before(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        System.out.println(configMap.get(method.getAnnotation(Ratelimit.class).value()));
    }
}
