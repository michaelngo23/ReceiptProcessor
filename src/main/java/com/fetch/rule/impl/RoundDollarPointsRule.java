package com.fetch.rule.impl;

import com.fetch.model.request.ReceiptRequest;
import com.fetch.rule.PointsRule;
import com.fetch.rule.RuleResult;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;


@Component
public class RoundDollarPointsRule implements PointsRule {
    @Override
    public RuleResult calculate(ReceiptRequest receipt) {
        BigDecimal total = new BigDecimal(receipt.getTotal());
        Long points = total.stripTrailingZeros().scale() == 0 ? 50L : 0L;
        // Strip trailing zeros and check if the scale is 0 (round dollar amount)
        return new RuleResult("Round Dollar Points", points);

    }
}
