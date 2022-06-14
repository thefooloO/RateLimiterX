package com.thefool.ratelimiter.rule.struct;

import lombok.Data;

@Data
public class ApiLimit {
    String api;
    int limit;
    int unit = 1;
}