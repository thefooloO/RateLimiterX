package com.thefool.ratelimiter.rule.struct;

import java.util.List;

public class UniformRuleConfigMapping {
    List<UniformRuleConfig> configs;

    public List<UniformRuleConfig> getConfigs() {
        return configs;
    }

    public void setConfigs(List<UniformRuleConfig> configs) {
        this.configs = configs;
    }
}