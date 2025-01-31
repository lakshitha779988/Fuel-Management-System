import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';

function AdminLoginForm() {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState('');
  const navigate = useNavigate();

  function handleLogin(e) {
    e.preventDefault();

    const requestBody = {
      userName: username,
      password: password,
    };

    
    fetch('http://localhost:8080/api/admin/login', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(requestBody),
    })
      .then((response) => {
        if (!response.ok) {
          throw new Error('Invalid username or password');
        }
        return response.json(); // Call .json() and return the promise
      })
      .then((data) => {
        console.log("Response JSON:", data); // Check what is actually being received
        if (data.token) {
          localStorage.setItem('adminToken', data.token);
          console.log("Token stored:", data.token);
          window.location.href = "./admin";
        } else {
          throw new Error('Token not found in response');
        }
      })
      .catch((error) => {
        setError(error.message);
        console.error("Login error:", error);
      });
    
  }

  return (
    <div className="flex items-center justify-center min-h-screen bg-gradient-to-br from-red-200 via-pink-100 to-white">
      <div className="bg-white shadow-lg rounded-lg p-8 w-full max-w-sm">
        <form onSubmit={handleLogin} className="space-y-6">
          <h2 className="text-2xl font-bold text-center text-red-500">Admin Login</h2>
          {error && <p className="text-red-500 text-sm text-center">{error}</p>}
          <div>
            <label
              htmlFor="username"
              className="block text-sm font-medium text-gray-700"
            >
              Username
            </label>
            <input
              type="text"
              id="username"
              placeholder="Enter Username"
              value={username}
              onChange={(e) => setUsername(e.target.value)}
              className="w-full mt-1 p-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-red-400 focus:outline-none"
            />
          </div>
          <div>
            <label
              htmlFor="password"
              className="block text-sm font-medium text-gray-700"
            >
              Password
            </label>
            <input
              type="password"
              id="password"
              placeholder="Enter Password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              className="w-full mt-1 p-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-red-400 focus:outline-none"
            />
          </div>
          <button
            type="submit"
            className="w-full bg-red-500 text-white py-2 px-4 rounded-lg hover:bg-red-600 focus:outline-none focus:ring-2 focus:ring-red-400"
          >
            Log in
          </button>
        </form>
      </div>
    </div>
  );
}

export default AdminLoginForm;
