package com.thefool.ratelimiter.algorithm;

import com.thefool.ratelimiter.rule.struct.IRule;

public interface IRatelimiter {
    boolean tryAcquire(String key, IRule rule);
}