package com.thefool.ratelimiter.algorithm.impl;

import com.thefool.ratelimiter.algorithm.IRatelimiter;
import com.thefool.ratelimiter.rule.struct.IRule;
import com.thefool.ratelimiter.rule.struct.impl.SlidingTimeWindowRatelimiterRule;

import java.util.HashMap;
import java.util.Map;

/**
 * 单机版-滑动时间窗口限流
 */
public class SlidingTimeWindowRatelimiter implements IRatelimiter {

    static Map<String, SlidingTimeWindow> slidingTimeWindowMap = new HashMap<>();

    @Override
    public boolean tryAcquire(String key, IRule rule) {

        SlidingTimeWindowRatelimiterRule slidingTimeWindowRatelimiterRule = (SlidingTimeWindowRatelimiterRule) rule;
        if(!slidingTimeWindowMap.containsKey(key)) {
            synchronized (SlidingTimeWindowRatelimiter.class) {
                if(!slidingTimeWindowMap.containsKey(key)) {
                    slidingTimeWindowMap.put(key, new SlidingTimeWindow(slidingTimeWindowRatelimiterRule));
                }
            }
        }

        SlidingTimeWindow slidingTimeWindow = slidingTimeWindowMap.get(key);
        synchronized (slidingTimeWindow) {
            slideWindow(slidingTimeWindow);

        }

        return false;
    }


    private void slideWindow(SlidingTimeWindow slidingTimeWindow) {

    }

    private class SlidingTimeWindow {

        long  startTime = System.currentTimeMillis();
        int   index = 0;
        int   count = 0;
        int[] counters;
        SlidingTimeWindowRatelimiterRule slidingTimeWindowRatelimiterRule;

        public SlidingTimeWindow(SlidingTimeWindowRatelimiterRule slidingTimeWindowRatelimiterRule) {
            counters = new int[slidingTimeWindowRatelimiterRule.getSplitNum()];
            this.slidingTimeWindowRatelimiterRule = slidingTimeWindowRatelimiterRule;
        }
    }
}