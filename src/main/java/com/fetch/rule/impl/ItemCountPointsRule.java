package com.fetch.rule.impl;

import com.fetch.model.request.ReceiptRequest;
import com.fetch.rule.PointsRule;
import com.fetch.rule.RuleResult;
import org.springframework.stereotype.Component;

@Component
public class ItemCountPointsRule implements PointsRule {

    @Override
    public RuleResult calculate(ReceiptRequest receipt) {
        long points = (receipt.getItems().size() / 2) * 5L;
        return new RuleResult("Item Count Points", points);
    }
}

