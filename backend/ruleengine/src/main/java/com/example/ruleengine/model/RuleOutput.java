package com.example.ruleengine.model;

public class RuleOutput {

    private String issueCategory;
    private String priority;
    private String statusColor;
    private String description;
    private String action;

    private DerivedOutput derived;

    private boolean humanAssistRequired;

    public String getIssueCategory() {
        return issueCategory;
    }

    public void setIssueCategory(String issueCategory) {
        this.issueCategory = issueCategory;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getStatusColor() {
        return statusColor;
    }

    public void setStatusColor(String statusColor) {
        this.statusColor = statusColor;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public DerivedOutput getDerived() {
        return derived;
    }

    public void setDerived(DerivedOutput derived) {
        this.derived = derived;
    }

    public boolean isHumanAssistRequired() {
        return humanAssistRequired;
    }

    public void setHumanAssistRequired(boolean humanAssistRequired) {
        this.humanAssistRequired = humanAssistRequired;
    }
}
