package com.fetch.rule.impl;

import com.fetch.model.request.ReceiptRequest;
import com.fetch.rule.PointsRule;
import com.fetch.rule.PointsRuleType;
import com.fetch.rule.RuleResult;
import org.springframework.stereotype.Component;


@Component
public class RetailerPointsRule implements PointsRule {

    @Override
    public RuleResult calculate(ReceiptRequest receipt) {
        long points = receipt.getRetailer().chars().filter(Character::isLetterOrDigit).count();
        return new RuleResult(PointsRuleType.RETAILER_POINTS.getRuleName(), points);
    }
}
