package com.fetch.rule.impl;

import com.fetch.model.request.ReceiptRequest;
import com.fetch.rule.PointsRule;
import com.fetch.rule.RuleResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class LargeLanguageModelPointsRule implements PointsRule {

    @Value("${disable.large.language.model.points.rule}")
    boolean disableLargeLanguageModelPointsRule;

    @Override
    public RuleResult calculate(ReceiptRequest receipt) {

        if(disableLargeLanguageModelPointsRule){
            return new RuleResult("Large Language Model Points", 0L);
        }
        long points = 0L;
        try {
            // Convert receipt.getTotal() to BigDecimal if it is a String
            BigDecimal total = new BigDecimal(receipt.getTotal());
            // Compare if the total is greater than 10.00
            points = total.compareTo(new BigDecimal("10.00")) > 0 ? 5L : 0L;
            return new RuleResult("Large Language Model Points", points);



        } catch (NumberFormatException e) {
            // Handle case where the total cannot be parsed into BigDecimal
            return new RuleResult("Large Language Model Points", points);
        }
    }
}
