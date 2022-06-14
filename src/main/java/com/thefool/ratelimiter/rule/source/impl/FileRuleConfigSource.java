package com.thefool.ratelimiter.rule.source.impl;

import com.thefool.ratelimiter.rule.parser.IRuleConfigParser;
import com.thefool.ratelimiter.rule.parser.impl.YamlRuleConfigParser;
import com.thefool.ratelimiter.rule.source.IRuleConfigSource;
import com.thefool.ratelimiter.rule.struct.UniformRuleConfigMapping;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class FileRuleConfigSource implements IRuleConfigSource {

    public static final String API_LIMIT_CONFIG_NAME = "rule";
    public static final String JSON_EXTENSION = "json";
    public static final String YML_EXTENSION = "yml";
    public static final String YAML_EXTENSION = "yaml";

    private static final String[] SUPPORT_EXTENSIONS =
            new String[] {YAML_EXTENSION, YML_EXTENSION, JSON_EXTENSION};

    private static final Map<String, IRuleConfigParser> PARSER_MAP = new HashMap<>();
    static {
        PARSER_MAP.put(YAML_EXTENSION, new YamlRuleConfigParser());
    }

    @Override
    public UniformRuleConfigMapping load() {
        for (String extension : SUPPORT_EXTENSIONS) {
            InputStream in = null;
            try {
                in = this.getClass().getResourceAsStream("/" + getFileNameByExt(extension));
                if(in != null) {
                    return PARSER_MAP.get(extension).parse(in);
                }
            } finally {
                if(in != null) {
                    try {
                        in.close();
                    } catch (Exception e) {}
                }
            }
        }
        return null;
    }

    private String getFileNameByExt(String extension) {
        return API_LIMIT_CONFIG_NAME + "." + extension;
    }


    public static void main(String[] args) {
        System.out.println(new FileRuleConfigSource().load());
    }
}