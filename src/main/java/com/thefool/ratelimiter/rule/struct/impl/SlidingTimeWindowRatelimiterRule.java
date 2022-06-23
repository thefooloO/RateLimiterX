package com.thefool.ratelimiter.rule.struct.impl;

import com.thefool.ratelimiter.rule.struct.IRule;

public class SlidingTimeWindowRatelimiterRule implements IRule {

    int windowSize;     // ms
    int limit;
    int num;

    public int getWindowSize() {
        return windowSize;
    }

    public void setWindowSize(int windowSize) {
        this.windowSize = windowSize;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}