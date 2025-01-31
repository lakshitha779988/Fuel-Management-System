import React, { useState, useEffect } from 'react';

const AdminDashboard = () => {
  const [activePage, setActivePage] = useState('home');
  const [fuelStations, setFuelStations] = useState([]);
  const [vehicleTypes, setVehicleTypes] = useState([]);
  const [newVehicleType, setNewVehicleType] = useState('');
  const [availableQuota, setAvailableQuota] = useState('');

  useEffect(() => {

    const token = localStorage.getItem('adminToken');
    if (token && typeof token === 'string') {
      fetchFuelStation(token)
      fetchVehicleTypes(token)
    } else {
      window.location.href = "/adminlogin"; // Redirect if token is not found

    }
  }, [activePage]);

  const fetchFuelStation = async (token) => {
    try {
      console.log(token);
      const response = await fetch(`http://localhost:8080/api/admin/fuelStation`, {
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
    console.log(`Changing status for station ID: ${id}`);
  
    fetch(`http://localhost:8080/api/admin/toggle-status/${id}`, {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json',
        Authorization: `Bearer ${localStorage.getItem('adminToken')}`, // Ensure the token is included if authentication is required
      },
    })
      .then((response) => {
        if (!response.ok) {
          throw new Error('Failed to toggle fuel station status');
        }
        return response.text(); // Since backend returns a simple success message
      })
      .then((message) => {
        console.log(message);
        alert('Fuel station status updated successfully!');
        // Optionally, refresh data or update UI
      })
      .catch((error) => {
        console.error('Error:', error);
        alert('Failed to update fuel station status');
      });
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
                    <td className="border border-gray-400 p-2">{station.name}</td>
                    <td className="border border-gray-400 p-2">{station.stock}</td>
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

        case 'vehicletype':
          return (
            <div className="p-6">
              <h2 className="text-2xl font-bold mb-4">Vehicle Type Management</h2>
              <table className="w-full border border-gray-400 mb-4">
                <thead>
                  <tr className="bg-gray-200 border border-gray-400">
                    <th className="border border-gray-400 p-2">Vehicle Type</th>
                    <th className="border border-gray-400 p-2">Available Quota</th>
                  </tr>
                </thead>
                <tbody>
                  {vehicleTypes.map((vehicle) => (
                    <tr key={vehicle.id} className="border border-gray-400">
                      <td className="border border-gray-400 p-2">{vehicle.typeName}</td>
                      <td className="border border-gray-400 p-2">{vehicle.availableQuota}</td>
                    </tr>
                  ))}
                </tbody>
              </table>
              <div className="mt-4">
                <input 
                  type="text" 
                  className="border p-2 mr-2" 
                  placeholder="Vehicle Type" 
                  value={newVehicleType} 
                  onChange={(e) => setNewVehicleType(e.target.value)} 
                />
                <input 
                  type="number" 
                  className="border p-2 mr-2" 
                  placeholder="Available Quota" 
                  value={availableQuota} 
                  onChange={(e) => setAvailableQuota(e.target.value)} 
                />
                <button 
                  className="px-3 py-2 bg-red-500 text-white rounded" 
                  onClick={addVehicleType}
                >
                  Add Vehicle Type
                </button>
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
            className={`w-full text-left px-4 py-2 rounded-lg ${activePage === 'fuelstation' ? 'bg-red-600' : 'hover:bg-gray-700'}`} 
            onClick={() => setActivePage('fuelstation')}
          >
            Fuel Station Management
          </button>
          <button 
            className={`w-full text-left px-4 py-2 rounded-lg ${activePage === 'vehicletype' ? 'bg-red-600' : 'hover:bg-gray-700'}`} 
            onClick={() => setActivePage('vehicletype')}
          >
            Vehicle Type Management
          </button>


        </nav>
      </aside>

      {/* Main Content  */}
<main class name="flex-1 bg-gray-100">
        {renderContent()}
      </main>

      
    </div>
  );
};

export default AdminDashboard;
