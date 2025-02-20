package com.fetch.rule.impl;

import com.fetch.model.request.ReceiptRequest;
import com.fetch.rule.RuleResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RetailerPointsRuleTest {

    private RetailerPointsRule rule;

    @BeforeEach
    void setUp() {
        rule = new RetailerPointsRule();
    }

    @Test
    void testRetailerPointsWithAlphanumericCharacters() {
        // Setup ReceiptRequest with a retailer name
        ReceiptRequest receipt = new ReceiptRequest();
        receipt.setRetailer("M&M Corner Market");

        // Points should be equal to the number of alphanumeric characters in "M&M Corner Market"
        // "M&M Corner Market" has 14 alphanumeric characters: "MMCornerMarket"
        RuleResult result = rule.calculate(receipt);

        assertEquals(14L, result.getPoints());
        assertEquals("Retailer Points", result.getRuleName());
    }

    @Test
    void testRetailerPointsWithSpecialCharacters() {
        // Setup ReceiptRequest with a retailer name containing special characters
        ReceiptRequest receipt = new ReceiptRequest();
        receipt.setRetailer("!@#$%&*()_+Retailer");

        // Points should be equal to the number of alphanumeric characters, excluding special characters
        // "!@#$%&*()_+Retailer" has 8 alphanumeric characters: "Retailer"
        RuleResult result = rule.calculate(receipt);

        assertEquals(8L, result.getPoints());
        assertEquals("Retailer Points", result.getRuleName());
    }

    @Test
    void testRetailerPointsWithEmptyRetailerName() {
        // Setup ReceiptRequest with an empty retailer name
        ReceiptRequest receipt = new ReceiptRequest();
        receipt.setRetailer("");

        // Points should be 0 as there are no alphanumeric characters in the empty string
        RuleResult result = rule.calculate(receipt);

        assertEquals(0L, result.getPoints());
        assertEquals("Retailer Points", result.getRuleName());
    }
}
