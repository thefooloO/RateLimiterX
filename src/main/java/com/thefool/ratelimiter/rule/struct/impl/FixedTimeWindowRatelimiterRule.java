package com.thefool.ratelimiter.rule.struct.impl;

import com.thefool.ratelimiter.rule.struct.IRule;

/**
 * 固定时间窗口限流规则
 */
public class FixedTimeWindowRatelimiterRule implements IRule {
    int limit;
    int unit = 1;

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getUnit() {
        return unit;
    }

    public void setUnit(int unit) {
        this.unit = unit;
    }
}