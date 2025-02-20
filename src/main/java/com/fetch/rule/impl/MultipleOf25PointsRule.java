package com.fetch.rule.impl;

import com.fetch.model.request.ReceiptRequest;
import com.fetch.rule.PointsRule;
import com.fetch.rule.PointsRuleType;
import com.fetch.rule.RuleResult;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;

@Component
public class MultipleOf25PointsRule implements PointsRule {

    @Override
    public RuleResult calculate(ReceiptRequest receipt) {
        BigDecimal total = new BigDecimal(receipt.getTotal());

        // Ensure total is positive and non-zero before checking remainder
        if (total.compareTo(BigDecimal.ZERO) <= 0) {
            return new RuleResult(PointsRuleType.MULTIPLE_OF_25_POINTS.getRuleName(), 0L);
        }

        // Calculate the remainder and check if total is a multiple of 0.25
        long points = new BigDecimal(receipt.getTotal())
                .remainder(BigDecimal.valueOf(PointsRuleType.MULTIPLE_OF_25_POINTS.getMultiplier()))
                .compareTo(BigDecimal.ZERO) == 0
                ? PointsRuleType.MULTIPLE_OF_25_POINTS.getBasePoints()
                : 0L;

        return new RuleResult(PointsRuleType.MULTIPLE_OF_25_POINTS.getRuleName(), points);
    }
}
