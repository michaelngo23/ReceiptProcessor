package com.fetch.rule.impl;

import com.fetch.model.request.ReceiptRequest;
import com.fetch.rule.RuleResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.*;

class OddDayPointsRuleTest {

    private OddDayPointsRule rule;

    @BeforeEach
    void setUp() {
        rule = new OddDayPointsRule();
    }

    @Test
    void testOddDayPoints() {
        // Setup ReceiptRequest with an odd day (e.g., 13th)
        ReceiptRequest receipt = new ReceiptRequest();
        receipt.setPurchaseDate("2025-02-13");  // 13th is an odd day

        RuleResult result = rule.calculate(receipt);

        // Points should be 6 for an odd day
        assertEquals(6L, result.getPoints());
        assertEquals("Odd Day Points", result.getRuleName());
    }

    @Test
    void testEvenDayPoints(){
        // Setup ReceiptRequest with an even day (e.g., 12th)
        ReceiptRequest receipt = new ReceiptRequest();
        receipt.setPurchaseDate("2025-02-12");  // 12th is an even day

        RuleResult result = rule.calculate(receipt);

        // Points should be 0 for an even day
        assertEquals(0L, result.getPoints());
        assertEquals("Odd Day Points", result.getRuleName());
    }

    @Test
    void testInvalidDateFormat() {
        // Setup ReceiptRequest with an invalid date format
        ReceiptRequest receipt = new ReceiptRequest();
        receipt.setPurchaseDate("invalid-date");

        RuleResult result = rule.calculate(receipt);

        // Points should be 0 as the date format is invalid, and the exception should be handled
        assertEquals(0L, result.getPoints());
        assertEquals("Odd Day Points", result.getRuleName());
    }
}
