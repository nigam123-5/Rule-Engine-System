export const rules = [
  {
    id: 1,
    condition: (input) =>
      input.age > 21 && input.salary > 30000 && input.direction === "right",
    output: {
      issueCategory: "Critical Bug",
      priority: "High",
      description: "User qualifies as high-risk based on age and salary.",
      action: "Escalate to senior dev team",
      derived: {
        finalScore: 89,
        experienceLevel: "Mid-Level",
        taxBracket: "20%",
        jobEligibility: "Eligible"
      }
    }
  },
  
  {
    id: 2,
    condition: (input) =>
      input.age <= 21 || input.salary <= 30000 || input.direction === "left",
    output: {
      issueCategory: "Info",
      priority: "Low",
      description: "User does not meet high-risk criteria.",
      action: "No action needed",
      derived: {
        finalScore: 50,
        experienceLevel: "Fresher",
        taxBracket: "10%",
        jobEligibility: "Not Eligible"
      }
    }
  }
];
