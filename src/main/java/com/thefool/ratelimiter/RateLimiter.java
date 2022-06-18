package com.thefool.ratelimiter;

import com.thefool.ratelimiter.annotation.Ratelimit;

public class RateLimiter {

    @Ratelimit("test")
    public void test() {}


    public static void main(String[] args) {
        new RateLimiter().test();
    }
}