package com.fetch.rule.impl;

import com.fetch.model.request.Item;
import com.fetch.model.request.ReceiptRequest;
import com.fetch.rule.PointsRule;
import com.fetch.rule.PointsRuleType;
import com.fetch.rule.RuleResult;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
public class ItemDescriptionPointsRule implements PointsRule {

    @Override
    public RuleResult calculate(ReceiptRequest receipt) {
        long points = 0L;
        for (Item item : receipt.getItems()) {
            String trimmedDescription = item.getShortDescription().trim();
            if (trimmedDescription.length() % PointsRuleType.ITEM_DESCRIPTION_DIVISOR_POINTS.getMultiplier() == 0) {
                BigDecimal price = new BigDecimal(item.getPrice());
                points += price.multiply(BigDecimal.valueOf(PointsRuleType.ITEM_DESCRIPTION_POINTS.getMultiplier()))
                        .setScale(0, RoundingMode.UP)
                        .longValue();
            }
        }
        return new RuleResult(PointsRuleType.ITEM_DESCRIPTION_POINTS.getRuleName(), points);
    }
}

