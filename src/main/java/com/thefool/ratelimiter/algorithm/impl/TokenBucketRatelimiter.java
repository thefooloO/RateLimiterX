package com.thefool.ratelimiter.algorithm.impl;

import com.thefool.ratelimiter.algorithm.IBucketRatelimiter;
import com.thefool.ratelimiter.rule.struct.IRule;
import com.thefool.ratelimiter.rule.struct.impl.TokenBucketRatelimiterRule;

import java.util.HashMap;
import java.util.Map;

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
        synchronized (tokenBucket) {
            addTokens(tokenBucket);
            tokenBucket.lastRequestTime = System.currentTimeMillis();
            if(tokenBucket.currentPermits >= permits) {
                tokenBucket.currentPermits -= permits;
                return true;
            }
            return false;
        }
    }

    private void addTokens(TokenBucket tokenBucket) {
        int newTokens = (int) (((System.currentTimeMillis() - tokenBucket.lastRequestTime) / 1000.0) * tokenBucket.tokenBucketRule.getRate());
        tokenBucket.currentPermits = Math.min(tokenBucket.tokenBucketRule.getMaxPermits(), tokenBucket.currentPermits + newTokens);
    }

    private class TokenBucket {
        TokenBucketRatelimiterRule tokenBucketRule;
        int  currentPermits;
        long lastRequestTime = System.currentTimeMillis();

        public TokenBucket(int rate, int maxPermits) {
            this.tokenBucketRule = new TokenBucketRatelimiterRule(rate, maxPermits);
            this.currentPermits  = maxPermits;
        }

        public TokenBucket(TokenBucketRatelimiterRule tokenBucketRule) {
            this.tokenBucketRule = tokenBucketRule;
            this.currentPermits  = tokenBucketRule.getMaxPermits();
        }
    }
}