package com.fetch.rule.impl;

import com.fetch.model.request.ReceiptRequest;
import com.fetch.rule.PointsRule;
import com.fetch.rule.RuleResult;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@Component
public class OddDayPointsRule implements PointsRule {
    @Override
    public RuleResult calculate(ReceiptRequest receipt) {
        long points = 0L;
        try {
            // Parse the purchase date
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            int day = sdf.parse(receipt.getPurchaseDate()).getDate();
            // Check if the day is odd
            points = day % 2 == 1 ? 6L : 0L;
            return new RuleResult("Odd Day Points", points);

        } catch (ParseException e) {
            // Handle the ParseException here, e.g., log it or return a default value
            return new RuleResult("Odd Day Points", points);
            // Default return if parsing fails
        }

    }
}
