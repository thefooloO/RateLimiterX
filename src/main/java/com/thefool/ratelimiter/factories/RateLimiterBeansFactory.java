package com.thefool.ratelimiter.factories;

import com.thefool.ratelimiter.config.RatelimiterConfig;
import com.thefool.ratelimiter.properties.PropertyConstants;
import com.thefool.ratelimiter.rule.parser.IRuleConfigParser;
import com.thefool.ratelimiter.rule.source.IRuleConfigSource;
import com.thefool.ratelimiter.spi.ExtensionServiceLoader;

public class RateLimiterBeansFactory {

    RatelimiterConfig ratelimiterConfig = new RatelimiterConfig();
    public static final RateLimiterBeansFactory context = new RateLimiterBeansFactory();

    public RateLimiterBeansFactory() {
        ratelimiterConfig.load();
    }

    public IRuleConfigParser obtainRuleConfigParser() {
        IRuleConfigParser ruleConfigParser = ExtensionServiceLoader.getExtension(IRuleConfigParser.class);

        return ruleConfigParser != null
                ? ruleConfigParser
                : RuleConfigParserFactory.obtainRuleConfigParser(ratelimiterConfig.getRuleConfigParserType());
    }

    public IRuleConfigSource obtainRuleConfigSource() {
        IRuleConfigSource ruleConfigSource = ExtensionServiceLoader.getExtension(IRuleConfigSource.class);

        return ruleConfigSource != null
                ? ruleConfigSource
                : RuleConfigSourceFactory.obtainRuleConfigSource(ratelimiterConfig.getRuleConfigSourceType());
    }


    public static void main(String[] args) {
        System.setProperty(PropertyConstants.PROPERTY_RULE_CONFIG_PARSER, "xml");
        System.setProperty(PropertyConstants.PROPERTY_RULE_CONFIG_SOURCE, "redis");
        RateLimiterBeansFactory rateLimiterBeansFactory = new RateLimiterBeansFactory();
        System.out.println(rateLimiterBeansFactory.obtainRuleConfigParser());
        System.out.println(rateLimiterBeansFactory.obtainRuleConfigSource().load());
    }
}