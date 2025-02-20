package com.fetch.rule;

import lombok.Getter;

@Getter
public enum PointsRuleType {
    ROUND_DOLLAR_POINTS("Round Dollar Points", 50L, 0),
    MULTIPLE_OF_25_POINTS("Multiple of 25 Points", 25L, .25),
    ITEM_COUNT_POINTS("Item Count Points", 5L, 2),
    ODD_DAY_POINTS("Odd Day Points", 6L, 0),
    PURCHASE_TIME_POINTS("Purchase Time Points", 10L, 0),
    ITEM_DESCRIPTION_POINTS("Item Description Points", 0L, 0.25),  // Variable points based on logic
    ITEM_DESCRIPTION_DIVISOR_POINTS("Item Description Points", 0L, 3),  // Variable points based on logic
    RETAILER_POINTS("Retailer Points", 0L, 0); // Calculated dynamically

    private final String ruleName;
    private final long basePoints;
    private final double multiplier;

    PointsRuleType(String ruleName, long basePoints, double multiplier) {
        this.ruleName = ruleName;
        this.basePoints = basePoints;
        this.multiplier = multiplier;
    }

}
