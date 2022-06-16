package com.thefool.ratelimiter.rule.struct;

import lombok.Data;

@Data
public class UniformRuleConfig {
    String name;
    String alg;
    String info;
    String format;
}