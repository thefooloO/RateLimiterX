package com.thefool.ratelimiter;

import com.thefool.ratelimiter.rule.source.IRuleConfigSource;
import lombok.Data;
import com.thefool.ratelimiter.rule.struct.UniformRuleConfigMapping;
import com.thefool.ratelimiter.algorithm.IRatelimiter;

import java.util.Iterator;
import java.util.ServiceLoader;

@Data
public class RateLimiter {

    private UniformRuleConfigMapping uniformRuleConfigMapping;
    private IRatelimiter ratelimiter;


    public RateLimiter() {
        ServiceLoader<IRuleConfigSource> ruleConfigSources = ServiceLoader.load(IRuleConfigSource.class);
        Iterator<IRuleConfigSource> iRuleConfigSourceIterator = ruleConfigSources.iterator();
        while(iRuleConfigSourceIterator.hasNext()) {
            uniformRuleConfigMapping = iRuleConfigSourceIterator.next().load();
        }
    }

    public static void main(String[] args) {
        new RateLimiter();
    }
}