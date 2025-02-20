package com.fetch.rule.impl;


import com.fetch.model.request.ReceiptRequest;
import com.fetch.rule.RuleResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PurchaseTimePointsRuleTest {

    private PurchaseTimePointsRule rule;

    @BeforeEach
    void setUp() {
        rule = new PurchaseTimePointsRule();
    }

    @Test
    void testTimeInRange() {
        // Setup ReceiptRequest with a time between 2:00pm and 4:00pm (e.g., 14:30)
        ReceiptRequest receipt = new ReceiptRequest();
        receipt.setPurchaseTime("14:30");  // 2:30pm is in the valid range

        RuleResult result = rule.calculate(receipt);

        // Points should be 10 as the time is within the 2:00pm - 4:00pm range
        assertEquals(10L, result.getPoints());
        assertEquals("Purchase Time Points", result.getRuleName());
    }

    @Test
    void testTimeBeforeRange() {
        // Setup ReceiptRequest with a time before 2:00pm (e.g., 13:30)
        ReceiptRequest receipt = new ReceiptRequest();
        receipt.setPurchaseTime("13:30");  // 1:30pm is outside the valid range

        RuleResult result = rule.calculate(receipt);

        // Points should be 0 as the time is before 2:00pm
        assertEquals(0L, result.getPoints());
        assertEquals("Purchase Time Points", result.getRuleName());
    }

    @Test
    void testTimeAfterRange() {
        // Setup ReceiptRequest with a time after 4:00pm (e.g., 16:30)
        ReceiptRequest receipt = new ReceiptRequest();
        receipt.setPurchaseTime("16:30");  // 4:30pm is outside the valid range

        RuleResult result = rule.calculate(receipt);

        // Points should be 0 as the time is after 4:00pm
        assertEquals(0L, result.getPoints());
        assertEquals("Purchase Time Points", result.getRuleName());
    }

    @Test
    void testInvalidTimeFormat() {
        // Setup ReceiptRequest with an invalid time format (e.g., "25:00")
        ReceiptRequest receipt = new ReceiptRequest();
        receipt.setPurchaseTime("25:00");  // Invalid time

        RuleResult result = rule.calculate(receipt);

        // Points should be 0, as the time format is invalid and ParseException should be caught
        assertEquals(0L, result.getPoints());
        assertEquals("Purchase Time Points", result.getRuleName());
    }
}
