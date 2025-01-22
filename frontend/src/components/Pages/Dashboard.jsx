import React, {useEffect, useState } from "react";

const Dashboard = () => {
  const [qrCode, setQrCode] = useState("Sample QR Code");
  const [showProfile, setShowProfile] = useState(false);
  const [activeSection, setActiveSection] = useState("QR Code");
  const [userDetails, setUserDetails] = useState(null);

  const generateQrCode = () => {
    setQrCode(`QR-${Date.now()}`);
  };

  useEffect(() => {
    const jwtToken = localStorage.getItem('token');
    const mobileNumber = localStorage.getItem('mobileNumber');
    console.log(jwtToken);
    

    if (jwtToken && typeof jwtToken === 'string') {
        
      fetchUserDetails(jwtToken)
    } else {
        setError('JWT Token not found or invalid!');
    }
} , []);




const fetchUserDetails = async (jwtToken,mobileNumber) => {
  try {
      const response = await fetch(`http://localhost:8080/api/user/details?token=${jwtToken}`, {
          method: 'GET',
          headers: {
              'Authorization': `Bearer ${jwtToken}`, 
              'Content-Type': 'application/json',
          },
      });

      const data = await response.json(); 
      if (response.ok) {
          setUserDetails(data);
          setShowProfile(true); 
      } else {
          setError('Failed to fetch user details');
      }
  } catch (error) {
      console.error('Error during request:', error);
      setError('Error occurred during request');
  }
};



  const downloadQrCode = () => {
    const canvas = document.getElementById("qrCode");
    const pngUrl = canvas.toDataURL("image/png");
    const downloadLink = document.createElement("a");
    downloadLink.href = pngUrl;
    downloadLink.download = "qr-code.png";
    downloadLink.click();
  };

  const handleLogout = () => {
    
    const isConfirmed = window.confirm("Do you really want to log out?");

    
    if (isConfirmed) {
        
        localStorage.removeItem('token');

       
        alert("Logged out successfully!");

        
        window.location.href = '/login'; 
    } else {
       
        alert("Logout cancelled.");
    }
};


  const deleteAccount = () => {
    if (window.confirm("Are you sure you want to delete your account?")) {
      alert("Account deleted.");
      
    }
  };

  return (
    <div className="min-h-screen bg-red-50 text-gray-800 flex">
      {/* Navigation Menu */}

      <aside className="w-1/5 bg-red-500 text-white py-6 px-4">
        <div className="mb-8 flex justify-center">

          {/* Profile Icon */}
          <div
            className="w-16 h-16 bg-red-200 rounded-full flex items-center justify-center cursor-pointer hover:bg-red-300"
            onClick={() => setShowProfile(!showProfile)}
          >
            <span className="text-xl font-bold text-red-600">P</span>
          </div>
        </div>

        {/* Profile Details */}
        {showProfile && (
          <div className="bg-red-100 text-red-800 rounded-lg p-4 mb-4">
            <p>Name: {userDetails.firstName}</p>
            <p>Email: {userDetails.mobileNumber}</p>
          </div>
        )}

        <nav className="flex flex-col space-y-4">
          <button
            onClick={() => setActiveSection("QR Code")}
            className={`px-4 py-2 rounded-md text-left ${
              activeSection === "QR Code" ? "bg-red-700" : "hover:bg-red-700"
            }`}
          >
            QR Code
          </button>
          <button
            onClick={() => setActiveSection("Reports")}
            className={`px-4 py-2 rounded-md text-left ${
              activeSection === "Reports" ? "bg-red-700" : "hover:bg-red-700"
            }`}
          >
            Reports
          </button>
          
          <button
            onClick={deleteAccount}
            className="px-4 py-2 rounded-md text-left hover:bg-red-700"
          >
            Delete Account
          </button>
          <button
            onClick={handleLogout}
            className="px-4 py-2 rounded-md text-left hover:bg-red-700"
          >
            Logout
          </button>
        </nav>
      </aside>

      {/* Main Content */}
      <main className="flex-1 p-6">
        
        {/* QR Code Section */}
        {activeSection === "QR Code" && (
          <div className="bg-white text-red-800 rounded-lg shadow-lg p-8 text-center">
            <h2 className="text-2xl font-bold mb-4">QR Code</h2>
            <div
              id="qrCode"
              className="bg-gray-100 w-40 h-40 flex justify-center items-center text-red-500 border-2 border-red-300 rounded-lg mx-auto mb-6"
            >
              {qrCode}
            </div>
            <div className="flex justify-center space-x-4">
              <button
                onClick={generateQrCode}
                className="bg-red-500 text-white px-4 py-2 rounded-md shadow-md hover:bg-red-700"
              >
                Update QR Code
              </button>
              <button
                onClick={downloadQrCode}
                className="bg-red-500 text-white px-4 py-2 rounded-md shadow-md hover:bg-red-700"
              >
                Download QR Code
              </button>
            </div>
          </div>
        )}

        {/* Reports Section */}
        {activeSection === "Reports" && (
          <div className="grid grid-cols-1 md:grid-cols-2 gap-6">
            <div className="bg-white p-6 rounded-lg shadow">
              <h3 className="text-lg font-semibold mb-2">Transaction Summary</h3>
              <p>Total Transactions: 50</p>
              <p>Total Amount: $5,000</p>
            </div>
            <div className="bg-white p-6 rounded-lg shadow">
              <h3 className="text-lg font-semibold mb-2">Fuel Usage Analysis</h3>
              <p>Diesel: 1,200L</p>
              <p>Petrol: 800L</p>
            </div>
            <div className="bg-white p-6 rounded-lg shadow">
              <h3 className="text-lg font-semibold mb-2">Cost Analysis</h3>
              <p>Average Cost per Liter: $1.25</p>
              <p>Total Cost: $2,500</p>
            </div>
            <div className="bg-white p-6 rounded-lg shadow">
              <h3 className="text-lg font-semibold mb-2">Refueling Details</h3>
              <p>Last Refueling: 2025-01-21</p>
              <p>Station: ABC Fuel</p>
            </div>
            <div className="bg-white p-6 rounded-lg shadow">
              <h3 className="text-lg font-semibold mb-2">Vehicle Reports</h3>
              <p>Vehicle 1: 500L used</p>
              <p>Vehicle 2: 700L used</p>
            </div>
            <div className="bg-white p-6 rounded-lg shadow">
              <h3 className="text-lg font-semibold mb-2">Custom Date Range</h3>
              <input
                type="date"
                className="border rounded p-2 w-full mt-2"
                placeholder="Start Date"
              />
              <input
                type="date"
                className="border rounded p-2 w-full mt-2"
                placeholder="End Date"
              />
            </div>
            
          </div>
        )}

        {/* Transactions Section */}
        {activeSection === "Transactions" && (
          <div className="bg-white text-red-800 rounded-lg shadow-lg p-8">
            <h2 className="text-2xl font-bold mb-4">Transactions</h2>
            <ul className="text-gray-600">
              <li>- Refueling Station 1: $45</li>
              <li>- Refueling Station 2: $60</li>
            </ul>
          </div>
        )}
      </main>
    </div>
  );
};

export default Dashboard;
