package com.thefool.ratelimiter.aspect;

import com.alibaba.fastjson.JSONObject;
import com.thefool.ratelimiter.algorithm.IRatelimiter;
import com.thefool.ratelimiter.annotation.Ratelimit;
import com.thefool.ratelimiter.factories.RateLimiterBeansFactory;
import com.thefool.ratelimiter.rule.config.UniformRuleConfigMapping;
import com.thefool.ratelimiter.rule.struct.IRule;
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
    private static Map<String, IRatelimiter> ratelimiterMap = new HashMap<>();
    private static Map<String, IRule> ruleMap = new HashMap<>();

    static {
        uniformRuleConfigMapping.getConfigs().forEach(config -> {
            try {
                ratelimiterMap.put(config.getName(), (IRatelimiter) (Class.forName(config.getRatelimiter()).newInstance()));
                ruleMap.put(config.getName(), (IRule) JSONObject.parseObject(config.getInfo(), Class.forName(config.getRule())));
            } catch (Exception e) {}
        });
    }

    @Pointcut("@annotation(com.thefool.ratelimiter.annotation.Ratelimit)")
    public void jointPoint() {}

    @Before("jointPoint()")
    public void before(JoinPoint joinPoint) {
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        String ratelimitKey = method.getDeclaringClass().getName() + ":" + method.getName();
        String name = method.getAnnotation(Ratelimit.class).value();
        IRule rule = ruleMap.get(name);
        IRatelimiter ratelimiter = ratelimiterMap.get(name);
        while(!ratelimiter.tryAcquire(rule, ratelimitKey)) {
            System.out.println(Thread.currentThread().getName() + "：限流...");
        }
    }
}