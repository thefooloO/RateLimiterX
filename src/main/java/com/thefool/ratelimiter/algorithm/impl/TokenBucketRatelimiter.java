package com.thefool.ratelimiter.algorithm.impl;

import com.thefool.ratelimiter.algorithm.IBucketRatelimiter;
import com.thefool.ratelimiter.rule.struct.IRule;
import com.thefool.ratelimiter.rule.struct.impl.TokenBucketRatelimiterRule;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 单机版-令牌桶
 */
public class TokenBucketRatelimiter implements IBucketRatelimiter {

    static Map<String, TokenBucket> tokenBucketMap = new HashMap<>();

    private String key;
    public TokenBucketRatelimiter() {}
    public TokenBucketRatelimiter(String key, int rate, int maxPermits) {
        this.key = key;
        tokenBucketMap.put(key, new TokenBucket(rate, maxPermits));
    }

    @Override
    public boolean tryAcquire(int permits) {
        return tryAcquire(key, permits);
    }

    @Override
    public boolean tryAcquire(String key, IRule rule) {
        TokenBucketRatelimiterRule tokenBucketRule = (TokenBucketRatelimiterRule) rule;
        if(!tokenBucketMap.containsKey(key)) {
            synchronized (TokenBucketRatelimiter.class) {
                if(!tokenBucketMap.containsKey(key)) {
                    tokenBucketMap.put(key, new TokenBucket(tokenBucketRule));
                }
            }
        }
        return tryAcquire(key, 1);
    }

    private boolean tryAcquire(String key, int permits) {
        TokenBucket tokenBucket = tokenBucketMap.get(key);

        return false;
    }

    private class TokenBucket {
        TokenBucketRatelimiterRule tokenBucketRule;
        AtomicInteger currentPermits = new AtomicInteger(0);
        long lastRequestTime = System.currentTimeMillis();

        public TokenBucket(int rate, int maxPermits) {
            tokenBucketRule = new TokenBucketRatelimiterRule(rate, maxPermits);
        }

        public TokenBucket(TokenBucketRatelimiterRule tokenBucketRule) {
            this.tokenBucketRule = tokenBucketRule;
        }
    }
}