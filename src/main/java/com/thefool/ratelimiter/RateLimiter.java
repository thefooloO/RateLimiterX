package com.thefool.ratelimiter;

import com.thefool.ratelimiter.annotation.Ratelimit;
import com.thefool.ratelimiter.annotation.RatelimitProcessor;
import com.thefool.ratelimiter.factories.RateLimiterBeansFactory;
import com.thefool.ratelimiter.properties.PropertyConstants;
import com.thefool.ratelimiter.rule.struct.UniformRuleConfigMapping;
import lombok.Data;

@Data
public class RateLimiter {

    private UniformRuleConfigMapping uniformRuleConfigMapping;
    private RatelimitProcessor ratelimitProcessor;

    public RateLimiter() {
        uniformRuleConfigMapping = RateLimiterBeansFactory.context.obtainRuleConfigSource().load();
        ratelimitProcessor = new RatelimitProcessor();
        ratelimitProcessor.process();
    }


    @Ratelimit("test")
    public void test() {}

    public static void main(String[] args) {
        System.setProperty(PropertyConstants.BASE_PACKAGES, "com.thefool.ratelimiter");
        new RateLimiter().ratelimitProcessor.print();

    }
}