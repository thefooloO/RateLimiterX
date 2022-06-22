package com.thefool.ratelimiter.rule.struct.impl;

import com.thefool.ratelimiter.rule.struct.IRule;

public class TokenBucketRatelimiterRule implements IRule {

    int rate;
    int maxPermits;

    public TokenBucketRatelimiterRule(){}
    public TokenBucketRatelimiterRule(int rate, int maxPermits) {
        this.rate = rate;
        this.maxPermits = maxPermits;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public int getMaxPermits() {
        return maxPermits;
    }

    public void setMaxPermits(int maxPermits) {
        this.maxPermits = maxPermits;
    }
}