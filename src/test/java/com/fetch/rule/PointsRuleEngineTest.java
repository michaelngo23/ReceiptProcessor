package com.fetch.rule;

import com.fetch.model.request.ReceiptRequest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PointsRuleEngineTest {

        @InjectMocks
        private PointsRuleEngine pointsRuleEngine;

        @Mock
        private PointsRule rule1;

        @Mock
        private PointsRule rule2;

        private ReceiptRequest mockReceiptRequest;

        @BeforeEach
        void setUp() {
            // Mock ReceiptRequest object
            mockReceiptRequest = new ReceiptRequest();
            // Inject mocked rules into PointsRuleEngine manually
            pointsRuleEngine = new PointsRuleEngine(List.of(rule1, rule2));
        }

        @Test
        void calculatePoints_ShouldReturnCorrectTotal_WhenRulesApply() {
            // Given: Mock each rule to return specific points
            when(rule1.calculate(mockReceiptRequest)).thenReturn(new RuleResult("Rule 1", 10L));
            when(rule2.calculate(mockReceiptRequest)).thenReturn(new RuleResult("Rule 2", 20L));
            // When: Points are calculated
            Long totalPoints = pointsRuleEngine.calculatePoints(mockReceiptRequest);
            // Then: The sum should be correct
            assertEquals(30L, totalPoints);
            // Verify that each rule was applied once
            verify(rule1, times(1)).calculate(mockReceiptRequest);
            verify(rule2, times(1)).calculate(mockReceiptRequest);
        }

        @Test
        void calculatePoints_ShouldReturnZero_WhenNoRulesApply() {
            // Given: All rules return 0 points
            when(rule1.calculate(mockReceiptRequest)).thenReturn(new RuleResult("Rule 1", 0L));
            when(rule2.calculate(mockReceiptRequest)).thenReturn(new RuleResult("Rule 2", 0L));
            // When: Points are calculated
            Long totalPoints = pointsRuleEngine.calculatePoints(mockReceiptRequest);
            // Then: The sum should be 0
            assertEquals(0L, totalPoints);
        }

        @Test
        void calculatePoints_ShouldReturnZero_WhenNoRulesExist() {
            // Given: Empty rules list
            pointsRuleEngine = new PointsRuleEngine(List.of());
            // When: Points are calculated
            Long totalPoints = pointsRuleEngine.calculatePoints(mockReceiptRequest);
            // Then: The sum should be 0
            assertEquals(0L, totalPoints);
        }
}
