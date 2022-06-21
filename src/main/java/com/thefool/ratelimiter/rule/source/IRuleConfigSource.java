package com.thefool.ratelimiter.rule.source;

import com.thefool.ratelimiter.rule.config.UniformRuleConfigMapping;

public interface IRuleConfigSource {
    UniformRuleConfigMapping load();
}
