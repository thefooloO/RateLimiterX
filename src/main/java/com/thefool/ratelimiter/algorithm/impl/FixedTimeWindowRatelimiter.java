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

    static Map<String, FixedTimeWindow> fixedTimeWindowMap = new HashMap<>();

    @Override
    public boolean tryAcquire(String key, IRule rule) {
        FixedTimeWindowRatelimiterRule fixedTimeWindowRatelimiterRule = (FixedTimeWindowRatelimiterRule) rule;
        if(!fixedTimeWindowMap.containsKey(key)) {
            synchronized (FixedTimeWindowRatelimiter.class) {
                if(!fixedTimeWindowMap.containsKey(key)) {
                    fixedTimeWindowMap.put(key, new FixedTimeWindow());
                }
            }
        }

        FixedTimeWindow fixedTimeWindow = fixedTimeWindowMap.get(key);
        int updatedCount = fixedTimeWindow.counter.incrementAndGet();
        long now = System.currentTimeMillis();

        if(now < fixedTimeWindow.time + fixedTimeWindowRatelimiterRule.getInterval()) {
            return fixedTimeWindowRatelimiterRule.getLimit() > updatedCount;
        }
        else {
            fixedTimeWindow.time = now;
            return fixedTimeWindow.counter.compareAndSet(updatedCount, 1);
        }
    }

    private class FixedTimeWindow {
        public AtomicInteger counter = new AtomicInteger(0);
        public long time = System.currentTimeMillis();
    }
}