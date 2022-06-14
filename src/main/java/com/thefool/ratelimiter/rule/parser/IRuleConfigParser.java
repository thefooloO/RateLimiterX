package com.thefool.ratelimiter.rule.parser;

import com.thefool.ratelimiter.rule.struct.UniformRuleConfigMapping;

import java.io.InputStream;

public interface IRuleConfigParser {
    UniformRuleConfigMapping parse(InputStream in);
}