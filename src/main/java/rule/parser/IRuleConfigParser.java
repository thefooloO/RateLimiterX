package rule.parser;

import rule.struct.UniformRuleConfigMapping;

import java.io.InputStream;

public interface IRuleConfigParser {
    UniformRuleConfigMapping parse(InputStream in);
}