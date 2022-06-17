package com.thefool.ratelimiter.rule.struct;

import lombok.Data;

/**
 * 固定时间窗口限流规则
 */
@Data
public class FixedTimeWindowRatelimiterRule {
    int limit;
    int unit = 1;
}