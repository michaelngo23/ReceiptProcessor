package com.fetch.rule;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RuleResult {
    private String ruleName;
    private Long points;

    @Override
    public String toString() {
        return "Rule: " + ruleName + ", Points: " + points;
    }
}
