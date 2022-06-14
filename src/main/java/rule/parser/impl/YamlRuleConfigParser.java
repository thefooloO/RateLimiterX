package rule.parser.impl;

import rule.parser.IRuleConfigParser;
import rule.struct.UniformRuleConfigMapping;
import utils.YamlUtils;

import java.io.InputStream;

public class YamlRuleConfigParser implements IRuleConfigParser {

    @Override
    public UniformRuleConfigMapping parse(InputStream in) {
        return YamlUtils.parse(in, UniformRuleConfigMapping.class);
    }
}