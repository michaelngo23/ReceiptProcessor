package com.fetch.rule.impl;

import com.fetch.model.request.Item;
import com.fetch.model.request.ReceiptRequest;
import com.fetch.rule.RuleResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ItemCountPointsRuleTest {

    private ItemCountPointsRule rule;
    private ReceiptRequest receiptRequest;

    @BeforeEach
    void setUp() {
        rule = new ItemCountPointsRule();
        receiptRequest = new ReceiptRequest();
    }

    @Test
    void calculate_ShouldReturnCorrectPoints_WhenEvenItems() {
        // Given: 4 items
        receiptRequest.setItems(Arrays.asList(new Item(), new Item(), new Item(), new Item()));

        // When: Rule is applied
        RuleResult result = rule.calculate(receiptRequest);

        // Then: (4 / 2) * 5 = 10 points
        assertEquals(10L, result.getPoints());
        assertEquals("Item Count Points", result.getRuleName());
    }

    @Test
    void calculate_ShouldReturnCorrectPoints_WhenOddItems() {
        // Given: 5 items
        receiptRequest.setItems(Arrays.asList(new Item(), new Item(), new Item(), new Item(), new Item()));

        // When: Rule is applied
        RuleResult result = rule.calculate(receiptRequest);

        // Then: (5 / 2) * 5 = 10 points
        assertEquals(10L, result.getPoints());
        assertEquals("Item Count Points", result.getRuleName());
    }

    @Test
    void calculate_ShouldReturnZeroPoints_WhenNoItems() {
        // Given: No items
        receiptRequest.setItems(Collections.emptyList());

        // When: Rule is applied
        RuleResult result = rule.calculate(receiptRequest);

        // Then: 0 points expected
        assertEquals(0L, result.getPoints());
        assertEquals("Item Count Points", result.getRuleName());
    }
}
