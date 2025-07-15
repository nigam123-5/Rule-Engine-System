package com.example.ruleengine.service;

import com.example.ruleengine.model.Evaluation;
import com.example.ruleengine.repository.RuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RuleEngineService {

    @Autowired
    private RuleRepository ruleRepository;

    @Autowired
    private DAGService dagService;

    public Evaluation applyRules(Evaluation evaluation) {
        String action = evaluation.getAction();
        double price = evaluation.getPrice();
        String userType = evaluation.getUserType();
        String marketCondition = evaluation.getMarketCondition();

        // 🎯 Simple dummy logic (Replace this with actual rule matching from DB if needed)
        if ("BUY".equalsIgnoreCase(action) && price > 100000 && "BEGINNER".equalsIgnoreCase(userType)) {
            evaluation.setIssueCategory("Potential Risk");
            evaluation.setPriority("High");
            evaluation.setDescription("High value BUY trade by a beginner in stable market.");
            evaluation.setConfidence(85);
            evaluation.setJobEligibility("Not Eligible");
            evaluation.setHumanAssistRequired(true);
        } else if ("SELL".equalsIgnoreCase(action) && price < 5000) {
            evaluation.setIssueCategory("Market Fluctuation");
            evaluation.setPriority("Medium");
            evaluation.setDescription("Low value SELL detected.");
            evaluation.setConfidence(70);
            evaluation.setJobEligibility("Eligible");
            evaluation.setHumanAssistRequired(false);
        } else {
            evaluation.setIssueCategory("Safe Trade");
            evaluation.setPriority("Low");
            evaluation.setDescription("Standard trade. No issues.");
            evaluation.setConfidence(60);
            evaluation.setJobEligibility("Eligible");
            evaluation.setHumanAssistRequired(false);
        }

        return evaluation;
    }
}
