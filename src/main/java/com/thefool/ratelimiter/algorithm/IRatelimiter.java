package com.thefool.ratelimiter.algorithm;

public interface IRatelimiter {
    boolean tryAcquire();
}