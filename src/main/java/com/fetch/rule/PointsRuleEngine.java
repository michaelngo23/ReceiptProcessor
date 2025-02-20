package com.fetch.rule;

import com.fetch.model.request.ReceiptRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PointsRuleEngine {

    private final List<PointsRule> pointsRules;

    @Autowired
    public PointsRuleEngine(List<PointsRule> pointsRules) {
        this.pointsRules = pointsRules;
    }

    public Long calculatePoints(ReceiptRequest receipt) {
        // Calculate total points by applying each rule
        List<RuleResult> ruleResults = pointsRules.stream()
                .map(rule -> rule.calculate(receipt))  // Apply each rule
                .toList();

        //for( RuleResult r : ruleResults){
        //    System.out.println(r.getRuleName() + " | " + r.getPoints());
        //}
        return ruleResults.stream()
                .mapToLong(RuleResult::getPoints)  // Sum up the points
                .sum();
    }
}
