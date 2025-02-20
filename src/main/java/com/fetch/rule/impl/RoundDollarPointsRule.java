package com.fetch.rule.impl;

import com.fetch.model.request.ReceiptRequest;
import com.fetch.rule.PointsRule;
import com.fetch.rule.PointsRuleType;
import com.fetch.rule.RuleResult;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;


@Component
public class RoundDollarPointsRule implements PointsRule {
    @Override
    public RuleResult calculate(ReceiptRequest receipt) {
        BigDecimal total = new BigDecimal(receipt.getTotal()).stripTrailingZeros();
        long points = 0L;

        // Ensure total is greater than zero and is a whole number
        if (total.compareTo(BigDecimal.ZERO) > 0 && total.remainder(BigDecimal.ONE).compareTo(BigDecimal.ZERO) == 0) {
            points = PointsRuleType.ROUND_DOLLAR_POINTS.getBasePoints();
        }

        // Return the result
        return new RuleResult(PointsRuleType.ROUND_DOLLAR_POINTS.getRuleName(), points);
        }
}
