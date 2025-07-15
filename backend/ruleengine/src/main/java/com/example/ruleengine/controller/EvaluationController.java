package com.example.ruleengine.controller;

import com.example.ruleengine.model.Evaluation;
import com.example.ruleengine.repository.EvaluationRepository;
import com.example.ruleengine.service.RuleEngineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/evaluations")
@CrossOrigin(origins = "*")
public class EvaluationController {

    @Autowired
    private EvaluationRepository evaluationRepository;

    @Autowired
    private RuleEngineService ruleEngineService;

    @PostMapping("/evaluate-trade")
    public ResponseEntity<Map<String, Object>> evaluateTrade(@RequestBody Evaluation evaluation) {
        Evaluation updatedEvaluation = ruleEngineService.applyRules(evaluation);

        Evaluation saved = evaluationRepository.save(updatedEvaluation);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Evaluation saved successfully");
        response.put("priority", saved.getPriority());
        response.put("description", saved.getDescription());
        response.put("humanAssist", saved.isHumanAssistRequired());
        response.put("issueCategory", saved.getIssueCategory());
        response.put("confidence", saved.getConfidence());
        response.put("jobEligibility", saved.getJobEligibility());
        response.put("finalScore", 80);
        response.put("experienceLevel", saved.getUserType().equalsIgnoreCase("BEGINNER") ? "Fresher" : "Experienced");
        response.put("taxBracket", saved.getPrice() > 100000 ? "30%" : "10%");

        return ResponseEntity.ok(response);
    }

    @GetMapping("/stats")
    public Map<String, Object> getDashboardStats() {
        List<Evaluation> evaluations = evaluationRepository.findAll();

        long total = evaluations.size();
        long potentialRisk = evaluations.stream()
                .filter(e -> "Potential Risk".equalsIgnoreCase(e.getIssueCategory()))
                .count();
        long highPriority = evaluations.stream()
                .filter(e -> "High".equalsIgnoreCase(e.getPriority()))
                .count();
        long humanAssist = evaluations.stream()
                .filter(Evaluation::isHumanAssistRequired)
                .count();

        Map<String, Object> stats = new HashMap<>();
        stats.put("total", total);
        stats.put("criticalBugs", potentialRisk);
        stats.put("highPriority", highPriority);
        stats.put("humanAssist", humanAssist);

        return stats;
    }

    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("UP");
    }
}
