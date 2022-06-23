package com.thefool.ratelimiter.rule.struct.impl;

import com.thefool.ratelimiter.rule.struct.IRule;

public class FixedTimeWindowRatelimiterRule implements IRule {

    int limit;
    long interval;

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public long getInterval() {
        return interval;
    }

    public void setInterval(long interval) {
        this.interval = interval;
    }
}