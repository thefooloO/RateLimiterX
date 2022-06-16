package com.thefool.ratelimiter.rule.struct;

/**
 * 固定时间窗口限流规则
 */
public class FixedTimeWindowRatelimiterRule {
    int limit;
    int unit = 1;
}