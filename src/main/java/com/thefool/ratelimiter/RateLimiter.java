package com.thefool.ratelimiter;

import com.thefool.ratelimiter.factories.RateLimiterBeansFactory;
import com.thefool.ratelimiter.rule.struct.UniformRuleConfigMapping;
import com.thefool.ratelimiter.spi.ExtensionServiceLoader;
import lombok.Data;
import com.thefool.ratelimiter.algorithm.IRatelimiter;

@Data
public class RateLimiter {

    UniformRuleConfigMapping uniformRuleConfigMapping;
    IRatelimiter ratelimiter;

    public RateLimiter() {
        uniformRuleConfigMapping = RateLimiterBeansFactory.context.obtainRuleConfigSource().load();
        ratelimiter = ExtensionServiceLoader.getExtension(IRatelimiter.class);
    }
}