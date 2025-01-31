import React, { useState, useEffect } from 'react';

const AdminDashboard = () => {
  const [activePage, setActivePage] = useState('home');
  const [fuelStations, setFuelStations] = useState([]);
  const [vehicleTypes, setVehicleTypes] = useState([]);
  const [newVehicleType, setNewVehicleType] = useState('');
  const [availableQuota, setAvailableQuota] = useState('');

  useEffect(() => {
    if (activePage === 'overview') {
      fetchFuelAmount('your-auth-token');
      fetchVehicleTypes('your-auth-token');
    }
  }, [activePage]);

  const fetchFuelAmount = async (token) => {
    try {
      const response = await fetch(``, {
        method: 'GET',
        headers: {
          'Authorization': `Bearer ${token}`,
          'Content-Type': 'application/json',
        },
      });
  
      const data = await response.json();
      if (response.ok) {
        setFuelStations(data);
      } else {
        alert('Failed to fetch fuel data');
      }
    } catch (error) {
      console.error('Error during request:', error);
    }
  };
  const fetchVehicleTypes = async (token) => {
    try {
      const response = await fetch(`/api/vehicletypes`, {
        method: 'GET',
        headers: {
          'Authorization': `Bearer ${token}`,
          'Content-Type': 'application/json',
        },
      });
      const data = await response.json();
      if (response.ok) {
        setVehicleTypes(data);
      } else {
        alert('Failed to fetch vehicle types');
      }
    } catch (error) {
      console.error('Error fetching vehicle types:', error);
    }
  };

  const addVehicleType = async () => {
    if (!newVehicleType || !availableQuota) {
      alert('Please enter both vehicle type and available quota');
      return;
    }
    try {
      const response = await fetch(`/api/vehicletypes`, {
        method: 'POST',
        headers: {
          'Authorization': `Bearer your-auth-token`,
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({ typeName: newVehicleType, availableQuota })
      });
      const data = await response.json();
      if (response.ok) {
        setVehicleTypes([...vehicleTypes, data]);
        setNewVehicleType('');
        setAvailableQuota('');
      } else {
        alert('Failed to add vehicle type');
      }
    } catch (error) {
      console.error('Error adding vehicle type:', error);
    }
  };






  const changeStatus = (id) => {
    // Function logic to change status will be implemented later
    console.log(`Changing status for station ID: ${id}`);
  };

  const renderContent = () => {
    switch (activePage) {
      case 'home':
        return (
          <div className="p-6">
            <h2 className="text-2xl font-bold mb-4">Welcome to the Admin Dashboard</h2>
            <p>Use the navigation bar to access different sections of the dashboard.</p>
          </div>
        );
      case 'fuelstation':
        return (
          <div className="p-6">
            <h2 className="text-2xl font-bold mb-4">Fuel Station Management</h2>
            <table className="w-full border border-gray-400">
              <thead>
                <tr className="bg-gray-200 border border-gray-400">
                  <th className="border border-gray-400 p-2">Fuel Station Name</th>
                  <th className="border border-gray-400 p-2">Available Stock</th>
                  <th className="border border-gray-400 p-2">Mobile Number</th>
                  <th className="border border-gray-400 p-2">Status</th>
                </tr>
              </thead>
              <tbody>
                {fuelStations.map((station) => (
                  <tr key={station.id} className="border border-gray-400">
                    <td className="border border-gray-400 p-2">{station.fuelStationName}</td>
                    <td className="border border-gray-400 p-2">{station.availableStock}</td>
                    <td className="border border-gray-400 p-2">{station.mobileNumber}</td>
                    <td className="border border-gray-400 p-2">
                      <button
                        className="px-3 py-1 bg-blue-500 text-white rounded"
                        onClick={() => changeStatus(station.id)}
                      >
                        {station.status}
                      </button>
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>
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
            className={`w-full text-left px-4 py-2 rounded-lg ${activePage === 'fuelstation' ? 'bg-red-600' : 'hover:bg-gray-700'}`} 
            onClick={() => setActivePage('fuelstation')}
          >
            Fuel Station Management
          </button>
        </nav>
      </aside>

      {/* Main Content */}
      <main className="flex-1 bg-gray-100 p-6">
        {activePage === 'fuelstation' && (
          <div>
            <h2 className="text-2xl font-bold mb-4">Vehicle Type Management</h2>
            <div className="mb-4">
              <input
                type="text"
                placeholder="Enter vehicle type"
                value={newVehicleType}
                onChange={(e) => setNewVehicleType(e.target.value)}
                className="p-2 border rounded mr-2"
              />
              <input
                type="text"
                placeholder="Available Quota"
                value={availableQuota}
                onChange={(e) => setAvailableQuota(e.target.value)}
                className="p-2 border rounded mr-2"
              />
              <button onClick={addVehicleType} className="px-4 py-2 bg-blue-500 text-white rounded">
                Add Vehicle Type
              </button>
            </div>
            <table className="w-full border border-gray-400">
              <thead>
                <tr className="bg-gray-200 border border-gray-400">
                  <th className="border border-gray-400 p-2">Type Name</th>
                  <th className="border border-gray-400 p-2">Available Quota</th>
                </tr>
              </thead>
              <tbody>
                {vehicleTypes.map((type) => (
                  <tr key={type.id} className="border border-gray-400">
                    <td className="border border-gray-400 p-2">{type.typeName}</td>
                    <td className="border border-gray-400 p-2">{type.availableQuota}</td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        )}
      </main>
    </div>
  );
};

export default AdminDashboard;
