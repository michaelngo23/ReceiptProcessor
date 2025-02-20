package com.fetch.rule.impl;


import com.fetch.model.request.ReceiptRequest;
import com.fetch.model.request.Item;
import com.fetch.rule.RuleResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ItemDescriptionPointsRuleTest {

    private ItemDescriptionPointsRule rule;

    @BeforeEach
    void setUp() {
        rule = new ItemDescriptionPointsRule();
    }

    @Test
    void testItemDescriptionPointsWithValidDescription() {
        // Prepare ReceiptRequest with items having descriptions whose length is divisible by 3
        Item item1 = new Item("Gatorades", "2.25");
        Item item2 = new Item("Juice", "3.50");

        ReceiptRequest receipt = new ReceiptRequest();
        receipt.setItems(Arrays.asList(item1, item2));  // Item descriptions: "Gatorade" (length = 8) and "Juice" (length = 5)

        RuleResult result = rule.calculate(receipt);

        // The description "Gatorades" length % 3 == 0, so it should give points.
        // Price = 2.25, points = 2.25 * 0.2 = 0.45 -> rounded up = 1
        assertEquals(1L, result.getPoints());
        assertEquals("Item Description Points", result.getRuleName());
    }

    @Test
    void testItemDescriptionPointsWithInvalidDescription() {
        // Prepare ReceiptRequest with items having descriptions whose length is NOT divisible by 3
        Item item1 = new Item("Coke", "2.00");
        Item item2 = new Item("Pepsi", "3.00");

        ReceiptRequest receipt = new ReceiptRequest();
        receipt.setItems(Arrays.asList(item1, item2));  // Item descriptions: "Coke" (length = 4) and "Pepsi" (length = 5)

        RuleResult result = rule.calculate(receipt);

        // None of the item descriptions have a length divisible by 3
        assertEquals(0L, result.getPoints());
        assertEquals("Item Description Points", result.getRuleName());
    }

    @Test
    void testItemDescriptionPointsWithTrimmedDescription() {
        // Test where description contains leading/trailing spaces
        Item item1 = new Item("  Milk  ", "1.50");
        Item item2 = new Item("  Bread  ", "2.00");

        ReceiptRequest receipt = new ReceiptRequest();
        receipt.setItems(Arrays.asList(item1, item2));

        RuleResult result = rule.calculate(receipt);

        // Trimmed descriptions: "Milk" (length = 4) and "Bread" (length = 5)
        // None of the item descriptions are divisible by 3
        assertEquals(0L, result.getPoints());
        assertEquals("Item Description Points", result.getRuleName());
    }

    @Test
    void testItemDescriptionPointsWithMultipleItems() {
        // Multiple items, description length is divisible by 3
        Item item1 = new Item("123", "1.25");  // "123" length = 3, divisible by 3
        Item item2 = new Item("456", "4.00");  // "456" length = 3, divisible by 3

        ReceiptRequest receipt = new ReceiptRequest();
        receipt.setItems(Arrays.asList(item1, item2));

        RuleResult result = rule.calculate(receipt);

        // Points = 1 for each item as their descriptions length % 3 == 0
        // Price = 1.25 * 0.2 = 0.25 -> rounded = 1
        assertEquals(2L, result.getPoints());
        assertEquals("Item Description Points", result.getRuleName());
    }

    @Test
    void testEmptyReceipt() {
        // Test empty receipt
        ReceiptRequest receipt = new ReceiptRequest();
        receipt.setItems(List.of());

        RuleResult result = rule.calculate(receipt);

        assertEquals(0L, result.getPoints());
        assertEquals("Item Description Points", result.getRuleName());
    }
}
