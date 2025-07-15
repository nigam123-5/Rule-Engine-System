package com.example.ruleengine.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "evaluations")
public class Evaluation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String stockSymbol;
    private String action;
    private double price;
    private int quantity;
    private String userType;
    private String marketCondition;
    private double salary;

    private String issueCategory;
    private String priority;
    private String description;
    private int confidence;
    private String jobEligibility;
    private boolean humanAssistRequired;

    private LocalDateTime timestamp = LocalDateTime.now();

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getStockSymbol() { return stockSymbol; }
    public void setStockSymbol(String stockSymbol) { this.stockSymbol = stockSymbol; }

    public String getAction() { return action; }
    public void setAction(String action) { this.action = action; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public String getUserType() { return userType; }
    public void setUserType(String userType) { this.userType = userType; }

    public String getMarketCondition() { return marketCondition; }
    public void setMarketCondition(String marketCondition) { this.marketCondition = marketCondition; }

    public double getSalary() { return salary; }
    public void setSalary(double salary) { this.salary = salary; }

    public String getIssueCategory() { return issueCategory; }
    public void setIssueCategory(String issueCategory) { this.issueCategory = issueCategory; }

    public String getPriority() { return priority; }
    public void setPriority(String priority) { this.priority = priority; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public int getConfidence() { return confidence; }
    public void setConfidence(int confidence) { this.confidence = confidence; }

    public String getJobEligibility() { return jobEligibility; }
    public void setJobEligibility(String jobEligibility) { this.jobEligibility = jobEligibility; }

    public boolean isHumanAssistRequired() { return humanAssistRequired; }
    public void setHumanAssistRequired(boolean humanAssistRequired) { this.humanAssistRequired = humanAssistRequired; }

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
}
