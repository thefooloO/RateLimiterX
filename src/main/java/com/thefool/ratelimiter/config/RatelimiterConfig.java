package com.thefool.ratelimiter.config;

import com.thefool.ratelimiter.order.OrderComparator;
import com.thefool.ratelimiter.properties.PropertyConstants;
import com.thefool.ratelimiter.properties.PropertySource;
import com.thefool.ratelimiter.properties.loader.PropertySourceLoader;
import com.thefool.ratelimiter.properties.loader.impl.JvmPropertySourceLoader;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RatelimiterConfig {

    private List<PropertySourceLoader> sourceLoaders;
    private String ruleConfigParserType = "yaml";
    private String ruleConfigSourceType = "file";
    private String appId;

    public RatelimiterConfig() {
        sourceLoaders = new ArrayList<>();
        sourceLoaders.add(new JvmPropertySourceLoader());
    }

    public void load() {
        PropertySource propertySource = new PropertySource();
        // 属性来源排序, 相同的属性高优先级会覆盖低优先级的
        Collections.sort(sourceLoaders, OrderComparator.instance);
        for(int i = sourceLoaders.size() - 1; i >= 0; --i) {
            propertySource.combinePropertySource(sourceLoaders.get(i).load());
        }
        mapPropertiesToConfigs(propertySource);
    }

    private void mapPropertiesToConfigs(PropertySource propertySource) {
        String parserType = propertySource.getPropertyStringValue(PropertyConstants.PROPERTY_RULE_CONFIG_PARSER);
        if(parserType != null) {
            this.ruleConfigParserType = parserType;
        }

        String sourceType = propertySource.getPropertyStringValue(PropertyConstants.PROPERTY_RULE_CONFIG_SOURCE);
        if(sourceType != null) {
            this.ruleConfigSourceType = sourceType;
        }
        this.appId = propertySource.getPropertyStringValue(PropertyConstants.APP_ID);
    }

    public List<PropertySourceLoader> getSourceLoaders() {
        return sourceLoaders;
    }

    public void setSourceLoaders(List<PropertySourceLoader> sourceLoaders) {
        this.sourceLoaders = sourceLoaders;
    }

    public String getRuleConfigParserType() {
        return ruleConfigParserType;
    }

    public void setRuleConfigParserType(String ruleConfigParserType) {
        this.ruleConfigParserType = ruleConfigParserType;
    }

    public String getRuleConfigSourceType() {
        return ruleConfigSourceType;
    }

    public void setRuleConfigSourceType(String ruleConfigSourceType) {
        this.ruleConfigSourceType = ruleConfigSourceType;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }
}