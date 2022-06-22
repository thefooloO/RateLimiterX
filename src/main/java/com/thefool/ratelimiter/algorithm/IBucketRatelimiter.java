package com.thefool.ratelimiter.algorithm;

public interface IBucketRatelimiter extends IRatelimiter {
    boolean tryAcquire(int permits);
}