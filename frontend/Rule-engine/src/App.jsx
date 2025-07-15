import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';

import Home from './components/Home-page/home-page';
import InputForm from './components/Input-form/input-form';
import Dashboard from './components/Dashboard/dashboard-page';
import Chatbot from './components/Chatbot-page/chatbot-page';
import Output from './components/Output-page/output-page';
import LoginSignup from './components/Login-page/login-page';  
import Navbar from './components/Navbar/navbar';
import ProtectedRoute from './components/Route/protected-route';  

function App() {
  return (
    <Router>
      <Navbar />

      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/login" element={<LoginSignup />} />
        <Route path="/input" element={<InputForm />} />
        <Route path="/chatbot" element={<Chatbot />} />
        <Route path="/output" element={<Output />} />

        <Route
          path="/dashboard"
          element={
            <ProtectedRoute>
              <Dashboard />
            </ProtectedRoute>
          }
        />
      </Routes>
    </Router>
  );
}

export default App;
