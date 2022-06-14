package rule.source;

import rule.struct.UniformRuleConfigMapping;

public interface IRuleConfigSource {
    UniformRuleConfigMapping load();
}
