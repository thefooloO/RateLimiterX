package com.thefool.ratelimiter;

import com.thefool.ratelimiter.annotation.Ratelimit;
import com.thefool.ratelimiter.factories.RateLimiterBeansFactory;
import com.thefool.ratelimiter.rule.struct.UniformRuleConfigMapping;
import lombok.Data;

@Data
public class RateLimiter {

    private UniformRuleConfigMapping uniformRuleConfigMapping;

    public RateLimiter() {
        uniformRuleConfigMapping = RateLimiterBeansFactory.context.obtainRuleConfigSource().load();
    }

    @Ratelimit("test")
    public void test() {}


    public static void main(String[] args) {
        new RateLimiter().test();
    }
}