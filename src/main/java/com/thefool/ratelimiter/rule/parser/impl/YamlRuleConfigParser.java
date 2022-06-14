package com.thefool.ratelimiter.rule.parser.impl;

import com.thefool.ratelimiter.rule.parser.IRuleConfigParser;
import com.thefool.ratelimiter.rule.struct.UniformRuleConfigMapping;
import com.thefool.ratelimiter.utils.YamlUtils;

import java.io.InputStream;

public class YamlRuleConfigParser implements IRuleConfigParser {

    @Override
    public UniformRuleConfigMapping parse(InputStream in) {
        return YamlUtils.parse(in, UniformRuleConfigMapping.class);
    }
}