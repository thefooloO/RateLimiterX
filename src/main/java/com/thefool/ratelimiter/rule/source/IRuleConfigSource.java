package com.thefool.ratelimiter.rule.source;

import com.thefool.ratelimiter.rule.struct.UniformRuleConfigMapping;

public interface IRuleConfigSource {
    UniformRuleConfigMapping load();
}
