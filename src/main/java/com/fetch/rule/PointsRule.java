package com.fetch.rule;

import com.fetch.model.request.ReceiptRequest;

public interface PointsRule {
    RuleResult calculate(ReceiptRequest receipt);
}