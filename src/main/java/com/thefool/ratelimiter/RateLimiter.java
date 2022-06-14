package com.thefool.ratelimiter;

import lombok.Data;
import com.thefool.ratelimiter.rule.struct.UniformRuleConfigMapping;
import com.thefool.ratelimiter.algorithm.IRatelimiter;

@Data
public class RateLimiter {

    private UniformRuleConfigMapping uniformRuleConfigMapping;
    private IRatelimiter ratelimiter;


    public RateLimiter() {
    }

    public static void main(String[] args) {
        new RateLimiter();
    }
}