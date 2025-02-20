package com.fetch.rule.impl;

import com.fetch.model.request.ReceiptRequest;
import com.fetch.rule.DateFormat;
import com.fetch.rule.PointsRule;
import com.fetch.rule.PointsRuleType;
import com.fetch.rule.RuleResult;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class PurchaseTimePointsRule implements PointsRule {
    @Override
    public RuleResult calculate(ReceiptRequest receipt) {
        long points = 0L;
        try {
            // Parse the purchase time
            SimpleDateFormat sdf = new SimpleDateFormat(DateFormat.HHmm);
            Date purchaseTime = sdf.parse(receipt.getPurchaseTime());
            int timeInMinutes = purchaseTime.getHours() * 60 + purchaseTime.getMinutes();

            int startRange = 14 * 60;  // 2:00pm in minutes
            int endRange = 16 * 60;    // 4:00pm in minutes
            // Check if the time falls in the specified range
            if (timeInMinutes >= startRange && timeInMinutes < endRange) {
                points = PointsRuleType.PURCHASE_TIME_POINTS.getBasePoints();
            }
        } catch (ParseException e) {
            // Handle the ParseException here, e.g., log it or return a default value
            return new RuleResult(PointsRuleType.PURCHASE_TIME_POINTS.getRuleName(), points);
            // Default return if parsing fails
        }
        return new RuleResult(PointsRuleType.PURCHASE_TIME_POINTS.getRuleName(), points);
    }
}
