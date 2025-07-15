import React from "react";
import { useNavigate } from "react-router-dom";
import "../Home-Page/home.css"; 

const Home = () => {
  const navigate = useNavigate();

return (
    <div className="home-container">
        <h1> Welcome to the Rule Engine System</h1>
        <p>
            This system allows you to input data like salary and age, 
            apply transformation rules using a smart Rule Engine, and view
            results via chatbot and dashboard.
        </p>

        <div className="btn-group">
            <button onClick={() => navigate("/input")}>Input Form</button>
            <button onClick={() => navigate("/chatbot")}> Chatbot</button>
            <button onClick={() => navigate("/dashboard")}>Dashboard</button>
        </div>
    </div>
);
};
export default Home;
