package com.thefool.ratelimiter.factories;

import com.thefool.ratelimiter.config.RatelimiterConfig;
import com.thefool.ratelimiter.rule.parser.IRuleConfigParser;
import com.thefool.ratelimiter.rule.source.IRuleConfigSource;
import com.thefool.ratelimiter.spi.ExtensionServiceLoader;
import lombok.Data;

@Data
public class RateLimiterBeansFactory {

    public static final RateLimiterBeansFactory context = new RateLimiterBeansFactory();
    private RatelimiterConfig ratelimiterConfig = new RatelimiterConfig();

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
}