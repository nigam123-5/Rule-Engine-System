package com.example.ruleengine.model;

import jakarta.persistence.*;

@Entity
@Table(name = "rules")
public class Rule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String conditionText;

    private String issueCategory;
    private String priority;
    private String statusColor;


    private String description;


    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getConditionText() { return conditionText; }

    public void setConditionText(String conditionText) { this.conditionText = conditionText; }

    public String getIssueCategory() { return issueCategory; }

    public void setIssueCategory(String issueCategory) { this.issueCategory = issueCategory; }

    public String getPriority() { return priority; }

    public void setPriority(String priority) { this.priority = priority; }

    public String getStatusColor() { return statusColor; }

    public void setStatusColor(String statusColor) { this.statusColor = statusColor; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }
}
