import { rules } from "./rules";

export const evaluateRules = (input) => {
  for (const rule of rules) {
    if (rule.condition(input)) {
      return rule.output;
    }
  }
  return null;
};
