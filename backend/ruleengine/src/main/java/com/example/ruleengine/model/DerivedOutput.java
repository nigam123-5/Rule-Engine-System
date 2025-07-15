package com.example.ruleengine.model;

public class DerivedOutput {
    private int finalScore;
    private String experienceLevel;
    private String taxBracket;
    private String jobEligibility;

    public int getFinalScore() {
        return finalScore;
    }

    public void setFinalScore(int finalScore) {
        this.finalScore = finalScore;
    }

    public String getExperienceLevel() {
        return experienceLevel;
    }

    public void setExperienceLevel(String experienceLevel) {
        this.experienceLevel = experienceLevel;
    }

    public String getTaxBracket() {
        return taxBracket;
    }

    public void setTaxBracket(String taxBracket) {
        this.taxBracket = taxBracket;
    }

    public String getJobEligibility() {
        return jobEligibility;
    }

    public void setJobEligibility(String jobEligibility) {
        this.jobEligibility = jobEligibility;
    }
}
