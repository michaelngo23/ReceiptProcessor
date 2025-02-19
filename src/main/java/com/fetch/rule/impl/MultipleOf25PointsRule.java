package com.fetch.rule.impl;

import com.fetch.model.request.ReceiptRequest;
import com.fetch.rule.PointsRule;
import com.fetch.rule.RuleResult;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;

@Component
public class MultipleOf25PointsRule implements PointsRule {

    @Override
    public RuleResult calculate(ReceiptRequest receipt) {
        BigDecimal total = new BigDecimal(receipt.getTotal().toString());
        // Calculate the remainder and check if it is a multiple of 0.25
        long points = total.remainder(new BigDecimal("0.25")).compareTo(BigDecimal.ZERO) == 0 ? 25L : 0L;
        return new RuleResult("Multiple of 25 Points", points);
    }
}
