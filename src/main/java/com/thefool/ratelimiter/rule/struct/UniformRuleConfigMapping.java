package com.thefool.ratelimiter.rule.struct;

import lombok.Data;

import java.util.List;

@Data
public class UniformRuleConfigMapping {
    List<UniformRuleConfig> configs;
}