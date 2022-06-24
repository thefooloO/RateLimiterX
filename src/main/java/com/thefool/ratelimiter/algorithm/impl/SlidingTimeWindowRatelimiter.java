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
            slidingTimeWindow.slideWindow();
            if(slidingTimeWindow.count >= slidingTimeWindow.rule.getLimit()) {
                return false;
            }
            else {
                slidingTimeWindow.count++;
                slidingTimeWindow.counters[slidingTimeWindow.index]++;
                return true;
            }
        }
    }

    private class SlidingTimeWindow {

        long  startTime = System.currentTimeMillis();
        int   index = 0;
        int   count = 0;
        int[] counters;
        SlidingTimeWindowRatelimiterRule rule;

        public SlidingTimeWindow(SlidingTimeWindowRatelimiterRule rule) {
            counters = new int[rule.getSplitNum()];
            this.rule = rule;
        }

        public void slideWindow() {
            long now = System.currentTimeMillis();
            long num = (now - startTime) / (rule.getWindowSize() / rule.getSplitNum());
            if(num == 0) return;
            long slideNum = Math.min(num, rule.getSplitNum());
            for(int i = 0; i < slideNum; i++) {
                index = (index + 1) % rule.getSplitNum();
                count -= counters[index];
                counters[index] = 0;
            }
            startTime = startTime + num * (rule.getWindowSize() / rule.getSplitNum());
        }
    }
}