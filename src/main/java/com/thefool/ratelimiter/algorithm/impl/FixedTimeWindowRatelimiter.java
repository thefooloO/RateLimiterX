package com.thefool.ratelimiter.algorithm.impl;

import com.thefool.ratelimiter.algorithm.IRatelimiter;
import com.thefool.ratelimiter.rule.struct.IRule;
import com.thefool.ratelimiter.rule.struct.impl.FixedTimeWindowRatelimiterRule;

/**
 * 单机版-固定时间窗口限流
 */
public class FixedTimeWindowRatelimiter implements IRatelimiter {

    @Override
    public boolean tryAcquire(IRule rule, String key) {
        FixedTimeWindowRatelimiterRule fixedTimeWindowRatelimiterRule = (FixedTimeWindowRatelimiterRule) rule;
        System.out.println(fixedTimeWindowRatelimiterRule.getLimit() + ":" + fixedTimeWindowRatelimiterRule.getUnit());
        return false;
    }
}