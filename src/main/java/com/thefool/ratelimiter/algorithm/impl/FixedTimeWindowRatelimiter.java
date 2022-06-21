package com.thefool.ratelimiter.algorithm.impl;

import com.thefool.ratelimiter.algorithm.IRatelimiter;
import com.thefool.ratelimiter.rule.struct.IRule;
import com.thefool.ratelimiter.rule.struct.impl.FixedTimeWindowRatelimiterRule;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 单机版-固定时间窗口限流
 */
public class FixedTimeWindowRatelimiter implements IRatelimiter {

    static Map<String, FixedTimeWindowCounter> fixedTimeWindowCounterMap = new HashMap<>();

    @Override
    public boolean tryAcquire(IRule rule, String key) {
        FixedTimeWindowRatelimiterRule fixedTimeWindowRatelimiterRule = (FixedTimeWindowRatelimiterRule) rule;
        if(!fixedTimeWindowCounterMap.containsKey(key)) {
            synchronized (FixedTimeWindowRatelimiter.class) {
                if(!fixedTimeWindowCounterMap.containsKey(key)) {
                    fixedTimeWindowCounterMap.put(key, new FixedTimeWindowCounter());
                }
            }
        }

        FixedTimeWindowCounter fixedTimeWindowCounter = fixedTimeWindowCounterMap.get(key);
        int updatedCount = fixedTimeWindowCounter.counter.incrementAndGet();
        long now = System.currentTimeMillis();

        if(now < fixedTimeWindowCounter.time + fixedTimeWindowRatelimiterRule.getInterval()) {
            return fixedTimeWindowRatelimiterRule.getLimit() > updatedCount;
        }
        else {
            fixedTimeWindowCounter.time = now;
            return fixedTimeWindowCounter.counter.compareAndSet(updatedCount, 1);
        }
    }

    private class FixedTimeWindowCounter {
        public AtomicInteger counter = new AtomicInteger(0);
        public long time = System.currentTimeMillis();
    }
}