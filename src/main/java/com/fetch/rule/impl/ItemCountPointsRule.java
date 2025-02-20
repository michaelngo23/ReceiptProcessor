package com.fetch.rule.impl;

import com.fetch.model.request.ReceiptRequest;
import com.fetch.rule.PointsRule;
import com.fetch.rule.PointsRuleType;
import com.fetch.rule.RuleResult;
import org.springframework.stereotype.Component;

@Component
public class ItemCountPointsRule implements PointsRule {

    @Override
    public RuleResult calculate(ReceiptRequest receipt) {
        double calculatedPoints = Math.floor(receipt.getItems().size() / PointsRuleType.ITEM_COUNT_POINTS.getMultiplier())
                * PointsRuleType.ITEM_COUNT_POINTS.getBasePoints();
        long points = (long) calculatedPoints;
        return new RuleResult(PointsRuleType.ITEM_COUNT_POINTS.getRuleName(), points);
    }
}

