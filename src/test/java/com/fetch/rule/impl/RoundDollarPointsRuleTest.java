package com.fetch.rule.impl;

import com.fetch.model.request.ReceiptRequest;
import com.fetch.rule.RuleResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoundDollarPointsRuleTest {

    private RoundDollarPointsRule rule;

    @BeforeEach
    void setUp() {
        rule = new RoundDollarPointsRule();
    }

    @Test
    void testRoundDollarPointsWithRoundAmount() {
        // Setup ReceiptRequest with a round dollar total (e.g., 100.00)
        ReceiptRequest receipt = new ReceiptRequest();
        receipt.setTotal("100.00");

        // Points should be 50 as the total is a round dollar amount
        RuleResult result = rule.calculate(receipt);

        assertEquals(50L, result.getPoints());
        assertEquals("Round Dollar Points", result.getRuleName());
    }

    @Test
    void testRoundDollarPointsWithNonRoundAmount() {
        // Setup ReceiptRequest with a non-round dollar total (e.g., 100.99)
        ReceiptRequest receipt = new ReceiptRequest();
        receipt.setTotal("100.99");

        // Points should be 0 as the total is not a round dollar amount
        RuleResult result = rule.calculate(receipt);

        assertEquals(0L, result.getPoints());
        assertEquals("Round Dollar Points", result.getRuleName());
    }


    @Test
    void testRoundDollarPointsWithAmountWithoutTrailingZeros() {
        // Setup ReceiptRequest with an amount without trailing zeros (e.g., 100.1)
        ReceiptRequest receipt = new ReceiptRequest();
        receipt.setTotal("100.1");

        // Points should be 0 because the total is not a round dollar amount
        RuleResult result = rule.calculate(receipt);

        assertEquals(0L, result.getPoints());
        assertEquals("Round Dollar Points", result.getRuleName());
    }

    @Test
    void testRoundDollarPointsWithZeroAmount() {
        // Setup ReceiptRequest with zero total (e.g., 0.00)
        ReceiptRequest receipt = new ReceiptRequest();
        receipt.setTotal("0.00");

        // Points should be 0 as the total is zero
        RuleResult result = rule.calculate(receipt);

        assertEquals(0L, result.getPoints());
        assertEquals("Round Dollar Points", result.getRuleName());
    }
}
