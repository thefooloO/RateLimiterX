package com.thefool.ratelimiter.aspect;

import com.thefool.ratelimiter.annotation.Ratelimit;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

@Aspect
public class RatelimitAspect {

    @Pointcut("@annotation(com.thefool.ratelimiter.annotation.Ratelimit)")
    public void jointPoint() {}

    @Before("jointPoint()")
    public void before(JoinPoint joinPoint) {
        System.out.println(((MethodSignature)joinPoint.getSignature()).getMethod().getAnnotation(Ratelimit.class).value());
    }
}
