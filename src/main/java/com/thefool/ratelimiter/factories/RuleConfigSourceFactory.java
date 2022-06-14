package com.thefool.ratelimiter.factories;

import com.thefool.ratelimiter.rule.source.IRuleConfigSource;
import com.thefool.ratelimiter.rule.source.impl.FileRuleConfigSource;

import java.util.HashMap;
import java.util.Map;

public class RuleConfigSourceFactory {

    private static Map<String, IRuleConfigSource> sourceMap = new HashMap<>();
    static {
        sourceMap.put("file", new FileRuleConfigSource());
    }

    public static IRuleConfigSource obtainRuleConfigSource(String sourceType) {
        return sourceMap.get(sourceType);
    }
}