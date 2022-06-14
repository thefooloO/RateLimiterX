package com.thefool.ratelimiter.rule.struct;

import lombok.Data;

import java.util.List;

@Data
public class UniformRuleConfig {
    String appId;
    List<ApiLimit> limits;
}