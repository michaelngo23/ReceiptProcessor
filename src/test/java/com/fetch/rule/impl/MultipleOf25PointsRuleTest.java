package com.fetch.rule.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fetch.model.request.ReceiptRequest;
import com.fetch.rule.RuleResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MultipleOf25PointsRuleTest {

    private MultipleOf25PointsRule rule;
    private ReceiptRequest mockReceiptRequest;

    @BeforeEach
    void setUp() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        rule = new MultipleOf25PointsRule();
        String json = new String(Files.readAllBytes(Paths.get("src/test/resources/receipt.json")));
        mockReceiptRequest = objectMapper.readValue(json, ReceiptRequest.class);
    }

    @Test
    public void testCalculate_WithMultipleOf25_ShouldReturn25Points() {
        // When
        mockReceiptRequest.setTotal("100");
        RuleResult result = rule.calculate(mockReceiptRequest);

        // Then
        assertEquals("Multiple of 25 Points", result.getRuleName());
        assertEquals(25L, result.getPoints());
    }

    @Test
    public void testCalculate_WithMultipleOf25Point25_ShouldReturn25Points() {
        mockReceiptRequest.setTotal(".25");
        RuleResult result = rule.calculate(mockReceiptRequest);
        // Then
        assertEquals("Multiple of 25 Points", result.getRuleName());
        assertEquals(25L, result.getPoints());
    }

    @Test
    public void testCalculate_WithNotMultipleOf25_ShouldReturn0Points() {
        mockReceiptRequest.setTotal("100.01");
        RuleResult result = rule.calculate(mockReceiptRequest);

        // Then
        assertEquals("Multiple of 25 Points", result.getRuleName());
        assertEquals(0L, result.getPoints());
    }

    @Test
    public void testCalculate_WithNegativeValue_ShouldReturn0Points() {
        mockReceiptRequest.setTotal("-100.00");
        RuleResult result = rule.calculate(mockReceiptRequest);

        // Then
        assertEquals("Multiple of 25 Points", result.getRuleName());
        assertEquals(0L, result.getPoints());
    }

    @Test
    public void testCalculate_WithZero_ShouldReturn0Points() {
        mockReceiptRequest.setTotal("0.00");
        RuleResult result = rule.calculate(mockReceiptRequest);

        // Then
        assertEquals("Multiple of 25 Points", result.getRuleName());
        assertEquals(0L, result.getPoints());
    }



    @Test
    public void testCalculate_WithVerySmallValue_ShouldReturn0Points() {
        mockReceiptRequest.setTotal("0.01");
        RuleResult result = rule.calculate(mockReceiptRequest);

        // Then
        assertEquals("Multiple of 25 Points", result.getRuleName());
        assertEquals(0L, result.getPoints());
    }
}