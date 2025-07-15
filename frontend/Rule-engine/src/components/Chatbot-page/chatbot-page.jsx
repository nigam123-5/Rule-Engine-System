import React, { useState } from "react";
import axios from "axios";
import "./chatbot.css";

const Chatbot = () => {
  const [messages, setMessages] = useState([
    { from: "bot", text: "Hi! Let's begin. What is the Stock Symbol?" }
  ]);

  const [step, setStep] = useState(1);
  const [inputData, setInputData] = useState({
    stockSymbol: "",
    action: "",
    price: "",
    quantity: "",
    userType: "",
    marketCondition: ""
  });

  const [currentInput, setCurrentInput] = useState("");

  const handleSend = async () => {
    const userText = currentInput.trim();
    if (!userText) return;

    let updatedMessages = [...messages, { from: "user", text: userText }];
    let updatedInput = { ...inputData };

    switch (step) {
      case 1:
        updatedInput.stockSymbol = userText.toUpperCase();
        updatedMessages.push({ from: "bot", text: "Action? (BUY/SELL)" });
        setStep(2);
        break;

      case 2:
        if (userText.toUpperCase() !== "BUY" && userText.toUpperCase() !== "SELL") {
          updatedMessages.push({ from: "bot", text: "Please enter 'BUY' or 'SELL'" });
        } else {
          updatedInput.action = userText.toUpperCase();
          updatedMessages.push({ from: "bot", text: "Enter Price" });
          setStep(3);
        }
        break;

      case 3:
        const price = parseFloat(userText);
        if (isNaN(price) || price <= 0) {
          updatedMessages.push({ from: "bot", text: "Please enter a valid price" });
        } else {
          updatedInput.price = price;
          updatedMessages.push({ from: "bot", text: "Enter Quantity" });
          setStep(4);
        }
        break;

      case 4:
        const quantity = parseInt(userText);
        if (isNaN(quantity) || quantity <= 0) {
          updatedMessages.push({ from: "bot", text: "Please enter a valid quantity" });
        } else {
          updatedInput.quantity = quantity;
          updatedMessages.push({ from: "bot", text: "User Type? (BEGINNER/EXPERT)" });
          setStep(5);
        }
        break;

      case 5:
        if (userText.toUpperCase() !== "BEGINNER" && userText.toUpperCase() !== "EXPERT") {
          updatedMessages.push({ from: "bot", text: "Type must be 'BEGINNER' or 'EXPERT'" });
        } else {
          updatedInput.userType = userText.toUpperCase();
          updatedMessages.push({ from: "bot", text: "Market Condition? (STABLE/VOLATILE)" });
          setStep(6);
        }
        break;

      case 6:
        if (userText.toUpperCase() !== "STABLE" && userText.toUpperCase() !== "VOLATILE") {
          updatedMessages.push({ from: "bot", text: "Condition must be 'STABLE' or 'VOLATILE'" });
        } else {
          updatedInput.marketCondition = userText.toUpperCase();
          updatedMessages.push({ from: "bot", text: "Evaluating your trade input..." });

          try {
            const token = localStorage.getItem("token");

            const res = await axios.post(
              "http://localhost:8080/api/evaluations/evaluate-trade",
              updatedInput,
              {
                headers: {
                  Authorization: `Bearer ${token}`,
                  "Content-Type": "application/json"
                }
              }
            );

            const result = res.data;

           if (result) {
  if (result.humanAssist) {
    updatedMessages.push({
      from: "bot",
      text: " Couldn't match confidently.\nTransferring to human assistant..."
    });
  } else {
    updatedMessages.push({
  from: "bot",
  text:
    "Rule Matched: " + result.issueCategory + "\n" +
    " Description: " + result.description + "\n" +
    "Priority: " + result.priority + "\n" +
    "Final Score: " + result.finalScore + "\n" +
    "Experience Level: " + result.experienceLevel + "\n" +
    "Tax Bracket: " + result.taxBracket + "\n" +
    "Job Eligibility: " + result.jobEligibility
});

  }
} else {
  updatedMessages.push({ from: "bot", text: " No rule matched. Try again." });
}

          } catch (err) {
            updatedMessages.push({ from: "bot", text: " Server error. Try again later." });
          }

          setStep(1);
          updatedMessages.push({ from: "bot", text: "Let's try again. What is the Stock Symbol?" });
          updatedInput = {
            stockSymbol: "",
            action: "",
            price: "",
            quantity: "",
            userType: "",
            marketCondition: ""
          };
        }
        break;

      default:
        break;
    }

    setMessages(updatedMessages);
    setInputData(updatedInput);
    setCurrentInput("");
  };

  const handleKeyPress = (e) => {
    if (e.key === "Enter") handleSend();
  };

  return (
    <div className="chatbox-wrapper">
      <h2 className="chatbot-title">Trading Chatbot Assistant</h2>

      <div className="chat-window">
        {messages.map((msg, i) => (
          <div key={i} className={`chat-bubble ${msg.from}`}>
            {msg.text.split("\n").map((line, idx) => (
              <div key={idx}>{line}</div>
            ))}
          </div>
        ))}
      </div>

      <div className="chat-input-area">
        <input
          className="chat-input"
          type="text"
          placeholder="Type here..."
          value={currentInput}
          onChange={(e) => setCurrentInput(e.target.value)}
          onKeyDown={handleKeyPress}
        />
        <button className="chat-send-button" onClick={handleSend}>Send</button>
      </div>
    </div>
  );
};

export default Chatbot;
