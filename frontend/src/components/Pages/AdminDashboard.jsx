// AdminDashboard.jsx
import React, { useState } from 'react';

const AdminDashboard = () => {
  const [activePage, setActivePage] = useState('home');

  const renderContent = () => {
    switch (activePage) {
      case 'home':
        return (
          <div className="p-6">
            <h2 className="text-2xl font-bold mb-4">Welcome to the Admin Dashboard</h2>
            <p>Use the navigation bar to access different sections of the dashboard.</p>
          </div>
        );
      case 'overview':
        return (
          <div className="p-6">
            <h2 className="text-2xl font-bold mb-4">Dashboard Overview</h2>
            <div className="grid grid-cols-1 md:grid-cols-3 gap-4">
              <div className="bg-white shadow-md rounded-lg p-4">
                <h3 className="text-xl font-semibold">Total Registered Users</h3>
                <p className="text-3xl font-bold">1,234</p>
              </div>
              <div className="bg-white shadow-md rounded-lg p-4">
                <h3 className="text-xl font-semibold">Fuel Requests Processed</h3>
                <p className="text-3xl font-bold">5,678</p>
              </div>
              <div className="bg-white shadow-md rounded-lg p-4">
                <h3 className="text-xl font-semibold">Pending Fuel Requests</h3>
                <p className="text-3xl font-bold">34</p>
              </div>
            </div>
          </div>
        );
      case 'users':
        return (
          <div className="p-6">
            <h2 className="text-2xl font-bold mb-4">User Management</h2>
            <p>Search and manage user profiles here.</p>
          </div>
        );
      case 'inventory':
        return (
          <div className="p-6">
            <h2 className="text-2xl font-bold mb-4">Inventory Monitoring</h2>
            <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
              <div className="bg-white shadow-md rounded-lg p-4">
                <h3 className="text-xl font-semibold">Fuel Stock Levels</h3>
                <p className="text-3xl font-bold">5,000 liters</p>
              </div>
              <div className="bg-white shadow-md rounded-lg p-4">
                <h3 className="text-xl font-semibold">Low Stock Alerts</h3>
                <p className="text-3xl font-bold text-red-600">Critical!</p>
              </div>
            </div>
          </div>
        );
      case 'reports':
        return (
          <div className="p-6">
            <h2 className="text-2xl font-bold mb-4">Reporting and Analytics</h2>
            <p>Generate and view detailed reports here.</p>
          </div>
        );
      case 'settings':
        return (
          <div className="p-6">
            <h2 className="text-2xl font-bold mb-4">Settings and Customization</h2>
            <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
              <div className="bg-white shadow-md rounded-lg p-4">
                <h3 className="text-xl font-semibold">Set Fuel Quota Limits</h3>
                <label className="block text-sm font-medium mb-2" htmlFor="quotaLimit">Fuel Quota (liters):</label>
                <input
                  type="number"
                  id="quotaLimit"
                  className="w-full border-gray-300 rounded-lg p-2"
                  placeholder="Enter quota limit"
                />
                <button className="mt-4 bg-red-600 text-white px-4 py-2 rounded-lg">Save</button>
              </div>
              <div className="bg-white shadow-md rounded-lg p-4">
                <h3 className="text-xl font-semibold">Set Quota Time Duration</h3>
                <label className="block text-sm font-medium mb-2" htmlFor="quotaDuration">Time Duration (hours):</label>
                <input
                  type="number"
                  id="quotaDuration"
                  className="w-full border-gray-300 rounded-lg p-2"
                  placeholder="Enter time duration"
                />
                <button className="mt-4 bg-red-600 text-white px-4 py-2 rounded-lg">Save</button>
              </div>
            </div>
          </div>
        );
      default:
        return null;
    }
  };

  return (
    <div className="flex min-h-screen">
      {/* Sidebar */}
      <aside className="bg-gray-800 text-white w-64 p-4 flex flex-col">
        <h1 className="text-2xl font-bold mb-6">Admin Panel</h1>
        <nav className="space-y-4">
          <button 
            className={`w-full text-left px-4 py-2 rounded-lg ${activePage === 'home' ? 'bg-red-600' : 'hover:bg-gray-700'}`} 
            onClick={() => setActivePage('home')}
          >
            Home
          </button>
          <button 
            className={`w-full text-left px-4 py-2 rounded-lg ${activePage === 'overview' ? 'bg-red-600' : 'hover:bg-gray-700'}`} 
            onClick={() => setActivePage('overview')}
          >
            Dashboard Overview
          </button>
          <button 
            className={`w-full text-left px-4 py-2 rounded-lg ${activePage === 'users' ? 'bg-red-600' : 'hover:bg-gray-700'}`} 
            onClick={() => setActivePage('users')}
          >
            User Management
          </button>
          <button 
            className={`w-full text-left px-4 py-2 rounded-lg ${activePage === 'inventory' ? 'bg-red-600' : 'hover:bg-gray-700'}`} 
            onClick={() => setActivePage('inventory')}
          >
            Inventory Monitoring
          </button>
          <button 
            className={`w-full text-left px-4 py-2 rounded-lg ${activePage === 'reports' ? 'bg-red-600' : 'hover:bg-gray-700'}`} 
            onClick={() => setActivePage('reports')}
          >
            Reporting & Analytics
          </button>
          <button 
            className={`w-full text-left px-4 py-2 rounded-lg ${activePage === 'settings' ? 'bg-red-600' : 'hover:bg-gray-700'}`} 
            onClick={() => setActivePage('settings')}
          >
            Settings & Customization
          </button>
        </nav>
      </aside>

      {/* Main Content */}
      <main className="flex-1 bg-gray-100">
        {renderContent()}
      </main>
    </div>
  );
};

export default AdminDashboard;