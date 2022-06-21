package com.thefool.ratelimiter.rule.config;


public class UniformRuleConfig {
    String name;
    String alg;
    String info;
    String format;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlg() {
        return alg;
    }

    public void setAlg(String alg) {
        this.alg = alg;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    @Override
    public String toString() {
        return "UniformRuleConfig{" +
                "name='" + name + '\'' +
                ", alg='" + alg + '\'' +
                ", info='" + info + '\'' +
                ", format='" + format + '\'' +
                '}';
    }
}