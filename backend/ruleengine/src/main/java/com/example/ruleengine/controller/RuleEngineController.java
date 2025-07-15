package com.example.ruleengine.controller;

import com.example.ruleengine.model.*;
import com.example.ruleengine.repository.EvaluationRepository;
import com.example.ruleengine.repository.RuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rules")
@CrossOrigin(origins = "*")
public class RuleEngineController {

    @Autowired
    private RuleRepository ruleRepository;

    @Autowired
    private EvaluationRepository evaluationRepository;

    @PostMapping("/evaluate-trade")
    public RuleOutput evaluateTrade(@RequestBody Input input) {
        RuleOutput output = new RuleOutput();
        Evaluation evaluation = new Evaluation();

        evaluation.setStockSymbol(input.getStockSymbol());
        evaluation.setAction(input.getAction());
        evaluation.setPrice(input.getPrice());
        evaluation.setQuantity(input.getQuantity());
        evaluation.setUserType(input.getUserType());
        evaluation.setMarketCondition(input.getMarketCondition());

        int score = 0;

        if ("BUY".equalsIgnoreCase(input.getAction()) && input.getPrice() > 1000) score += 30;
        if ("EXPERT".equalsIgnoreCase(input.getUserType())) score += 30;
        if ("VOLATILE".equalsIgnoreCase(input.getMarketCondition())) score += 40;

        if (score >= 60) {
            output.setIssueCategory("Potential Risk");
            output.setPriority("High");
            output.setStatusColor("red");
            output.setDescription("High-risk trading condition detected.");
            output.setAction("Manual Review Suggested");

            DerivedOutput derived = new DerivedOutput();
            derived.setFinalScore(score);
            derived.setExperienceLevel(input.getUserType().equalsIgnoreCase("EXPERT") ? "Pro Trader" : "Beginner");
            derived.setTaxBracket("30%");
            derived.setJobEligibility("Eligible");

            output.setDerived(derived);

            evaluation.setIssueCategory("Potential Risk");
            evaluation.setPriority("High");
            evaluation.setDescription("High-risk trading condition detected.");
            evaluation.setJobEligibility("Eligible");

        } else {
            output.setIssueCategory("Normal");
            output.setPriority("Low");
            output.setStatusColor("blue");
            output.setDescription("No unusual trading activity.");
            output.setAction("Proceed");

            DerivedOutput derived = new DerivedOutput();
            derived.setFinalScore(score);
            derived.setExperienceLevel(input.getUserType().equalsIgnoreCase("EXPERT") ? "Pro Trader" : "Beginner");
            derived.setTaxBracket("10%");
            derived.setJobEligibility("Allowed");

            output.setDerived(derived);

            evaluation.setIssueCategory("Normal");
            evaluation.setPriority("Low");
            evaluation.setDescription("No unusual trading activity.");
            evaluation.setJobEligibility("Allowed");
        }

        evaluation.setConfidence(score);
        evaluation.setHumanAssistRequired(score < 60);

        evaluationRepository.save(evaluation);
        return output;
    }
}
