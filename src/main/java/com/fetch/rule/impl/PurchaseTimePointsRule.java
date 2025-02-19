package com.fetch.rule.impl;

import com.fetch.model.request.ReceiptRequest;
import com.fetch.rule.PointsRule;
import com.fetch.rule.RuleResult;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@Component
public class PurchaseTimePointsRule implements PointsRule {
    @Override
    public RuleResult calculate(ReceiptRequest receipt) {
        long points = 0L;
        try {
            // Parse the purchase time
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            int timeInMinutes = sdf.parse(receipt.getPurchaseTime()).getHours() * 60 + sdf.parse(receipt.getPurchaseTime()).getMinutes();

            int startRange = 14 * 60;  // 2:00pm in minutes
            int endRange = 16 * 60;    // 4:00pm in minutes
            points = timeInMinutes >= startRange && timeInMinutes < endRange ? 10L : 0L;
            // Check if the time is between 2:00pm and 4:00pm
            return new RuleResult("Purchase Time Points", points);
        } catch (ParseException e) {
            // Handle the ParseException here, e.g., log it or return a default value
            return new RuleResult("Purchase Time Points", points);
            // Default return if parsing fails
        }
    }
}
