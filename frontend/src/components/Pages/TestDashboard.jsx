// components/Dashboard/Dashboard.jsx

import React, { useEffect, useState } from 'react';

const Dashboard = () => {
    const [message, setMessage] = useState('');
    const [error, setError] = useState('');

    useEffect(() => {
        const jwtToken = localStorage.getItem('jwtToken');  // Get the JWT token from localStorage

        if (jwtToken) {
            // Call the endpoint with JWT token
            fetchMessage(jwtToken);
        } else {
            setError('JWT Token not found!');
        }
    }, []);

    const fetchMessage = async (jwtToken) => {
        try {
            const response = await fetch('http://localhost:8080/api/vehicles/test', {
                method: 'GET',
                headers: {
                    'Authorization': `Bearer ${jwtToken}`,  // Add JWT token in Authorization header
                    'Content-Type': 'application/json',
                },
            });

            const data = await response.json();
            if (response.ok) {
                setMessage(data); // Assuming the response body contains a string message
            } else {
                setError('Failed to fetch message');
            }
        } catch (error) {
            console.error('Error during request:', error);
            setError('Error occurred during request');
        }
    };

    return (
        <div>
            <h1>Dashboard</h1>
            {message && <p>{message}</p>}
            {error && <p style={{ color: 'red' }}>{error}</p>}
        </div>
    );
};

export default Dashboard;
