import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import './input-Form.css';

const InputForm = () => {
  const navigate = useNavigate();

  const [formData, setFormData] = useState({
    stockSymbol: '',
    action: 'BUY',
    price: '',
    quantity: '',
    userType: 'BEGINNER',
    marketCondition: 'STABLE'
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData(prev => ({ ...prev, [name]: value }));
  };

  const isFormValid =
    formData.stockSymbol.trim() !== '' &&
    formData.price.trim() !== '' &&
    formData.quantity.trim() !== '';

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      const token = localStorage.getItem("token");

      if (!token) {
        alert("Token not found. Please login again.");
        return;
      }

      const response = await axios.post(
       "http://localhost:8080/api/evaluations/evaluate-trade"
, 
        formData,
        {
          headers: {
            "Content-Type": "application/json",
            "Authorization": `Bearer ${token}`
          }
        }
      );

      navigate('/output', {
        state: {
          input: formData,
          result: response.data
        }
      });

    } catch (error) {
      console.error("Error while connecting to backend:", error);

      if (error.response?.status === 403) {
        alert("Unauthorized! Please login again. Token may be expired.");
      } else if (error.response?.status === 500) {
        alert("Internal Server Error! Please check backend logs.");
      } else {
        alert("Backend connection failed or unauthorized. Please check server or token.");
      }
    }
  };

  return (
    <div className="page">
      <div className="form-container">
        <h2 className="form-title">Trading Rule Engine Input Form</h2>

        <form onSubmit={handleSubmit} className="form-box">

          <input
            type="text"
            name="stockSymbol"
            placeholder="Enter Stock Symbol (e.g. TCS)"
            onChange={handleChange}
            value={formData.stockSymbol}
            className="input-field"
            required
          />

          <select
            name="action"
            value={formData.action}
            onChange={handleChange}
            className="input-field"
            required
          >
            <option value="BUY">BUY</option>
            <option value="SELL">SELL</option>
          </select>

          <input
            type="number"
            name="price"
            placeholder="Enter Price"
            onChange={handleChange}
            value={formData.price}
            className="input-field"
            required
          />

          <input
            type="number"
            name="quantity"
            placeholder="Enter Quantity"
            onChange={handleChange}
            value={formData.quantity}
            className="input-field"
            required
          />

          <select
            name="userType"
            value={formData.userType}
            onChange={handleChange}
            className="input-field"
            required
          >
            <option value="BEGINNER">BEGINNER</option>
            <option value="EXPERT">EXPERT</option>
          </select>

          <select
            name="marketCondition"
            value={formData.marketCondition}
            onChange={handleChange}
            className="input-field"
            required
          >
            <option value="STABLE">STABLE</option>
            <option value="VOLATILE">VOLATILE</option>
          </select>

          <button type="submit" className="submit-btn" disabled={!isFormValid}>
            Submit
          </button>

        </form>
      </div>
    </div>
  );
};

export default InputForm;
