package com.thefool.ratelimiter.rule.config;

public class UniformRuleConfig {
    String name;
    String ratelimiter;
    String rule;
    String info;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRatelimiter() {
        return ratelimiter;
    }

    public void setRatelimiter(String ratelimiter) {
        this.ratelimiter = ratelimiter;
    }

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "UniformRuleConfig{" +
                "name='" + name + '\'' +
                ", ratelimiter='" + ratelimiter + '\'' +
                ", rule='" + rule + '\'' +
                ", info='" + info + '\'' +
                '}';
    }
}