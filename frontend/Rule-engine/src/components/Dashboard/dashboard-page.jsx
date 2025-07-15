import React, { useEffect, useState } from 'react';
import './dashboard.css';
import axios from 'axios';

const Dashboard = () => {
  const [stats, setStats] = useState({
    total: 0,
    criticalBugs: 0,
    highPriority: 0,
    humanAssist: 0
  });

  const [healthStatus, setHealthStatus] = useState('Checking...');

  useEffect(() => {
    const token = localStorage.getItem("token");

    axios.get('http://localhost:8080/api/evaluations/stats', {
      headers: {
        Authorization: `Bearer ${token}`
      }
    })
      .then((res) => {
        setStats(res.data);
      })
      .catch(() => {
        console.error("Failed to fetch stats");
      });

    axios.get('http://localhost:8080/api/evaluations/health', {
      headers: {
        Authorization: `Bearer ${token}`
      }
    })
      .then((res) => {
        setHealthStatus(res.data === 'UP' ? 'UP' : 'DOWN');
      })
      .catch(() => {
        setHealthStatus('DOWN');
      });
  }, []);

  return (
    <div className="dashboard-container">
      <h2 className="dashboard-title">Executive Dashboard</h2>

      <div className="stats-container">
        <div className="stat-card">
          <h3>Total Requests</h3>
          <p>{stats.total}</p>
        </div>
        <div className="stat-card">
          <h3>Critical Bugs</h3>
          <p>{stats.criticalBugs}</p>
        </div>
        <div className="stat-card">
          <h3>High Priority</h3>
          <p>{stats.highPriority}</p>
        </div>
        <div className="stat-card">
          <h3>Human Assist</h3>
          <p>{stats.humanAssist}</p>
        </div>
        <div className="stat-card">
          <h3>System Health</h3>
          <p className={`health-status ${healthStatus === 'DOWN' ? 'down' : ''}`}>{healthStatus}</p>
        </div>
      </div>

      <div className="dashboard-note">
        <p>This dashboard shows a live summary of rule engine activity.</p>
      </div>
    </div>
  );
};

export default Dashboard;
