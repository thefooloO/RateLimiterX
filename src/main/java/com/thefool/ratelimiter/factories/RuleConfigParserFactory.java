package com.thefool.ratelimiter.factories;

import com.thefool.ratelimiter.rule.parser.IRuleConfigParser;
import com.thefool.ratelimiter.rule.parser.impl.YamlRuleConfigParser;

import java.util.HashMap;
import java.util.Map;

public class RuleConfigParserFactory {

    private static Map<String, IRuleConfigParser> parserMap = new HashMap<>();
    static {
        parserMap.put("yaml", new YamlRuleConfigParser());
    }

    public static IRuleConfigParser obtainRuleConfigParser(String parserType) {
        return parserMap.get(parserType);
    }
}