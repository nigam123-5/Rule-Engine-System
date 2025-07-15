import React from "react";
import { useLocation, useNavigate } from "react-router-dom";
import "./output.css";

const Output = () => {
  const location = useLocation();
  const navigate = useNavigate();

  const input = location.state?.input;
  const result = location.state?.result;

  const handleRetry = () => {
    navigate("/input");  
  };

  return (
    <div className="output-container">
      <h2 className="output-title">Trade Evaluation Output</h2>

      {result ? (
        <div
          className="output-box"
          style={{ borderLeft: `6px solid ${result.statusColor || "gray"}` }}
        >
          <h3 className="issue-category">{result.issueCategory}</h3>
          <p className="description">{result.description}</p>
          <p className="priority">
            Priority: <strong>{result.priority}</strong>
          </p>
          <p className="action">
            Action: <strong>{result.action || "N/A"}</strong>
          </p>

          {input && (
            <div className="trading-input-summary">
              <h4>Trade Input Details:</h4>
              <p>Stock Symbol: <strong>{input.stockSymbol}</strong></p>
              <p>Action: <strong>{input.action}</strong></p>
              <p>Price: <strong>{input.price}</strong></p>
              <p>Quantity: <strong>{input.quantity}</strong></p>
              <p>User Type: <strong>{input.userType}</strong></p>
              <p>Market Condition: <strong>{input.marketCondition}</strong></p>
            </div>
          )}

          {result.derived && (
            <div className="derived-output">
              <h4>Derived Insights:</h4>
              <p>Final Score: <strong>{result.derived.finalScore}</strong></p>
              <p>Experience Level: <strong>{result.derived.experienceLevel}</strong></p>
              <p>Tax Bracket: <strong>{result.derived.taxBracket}</strong></p>
              <p>Job Eligibility: <strong>{result.derived.jobEligibility}</strong></p>
            </div>
          )}
        </div>
      ) : (
        <div className="output-box no-match">
          <h3>No rule matched</h3>
          <p>Please try again with different input.</p>
        </div>
      )}

      <button className="retry-button" onClick={handleRetry}>Retry</button>
    </div>
  );
};

export default Output;
