package com.thefool.ratelimiter.algorithm.impl;

import com.thefool.ratelimiter.algorithm.IRatelimiter;
import com.thefool.ratelimiter.rule.struct.IRule;

/**
 * 单机版-滑动时间窗口限流
 */
public class SlidingTimeWindowRatelimiter implements IRatelimiter {
    @Override
    public boolean tryAcquire(String key, IRule rule) {
        return false;
    }
}