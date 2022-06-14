package com.thefool.ratelimiter.algorithm.impl;

import com.thefool.ratelimiter.algorithm.IRatelimiter;

/**
 * 单机版-固定时间窗口限流
 */
public class FixedTimeWindowRatelimiter implements IRatelimiter {

    @Override
    public boolean tryAcquire() {
        return false;
    }
}