import React, { useEffect, useState } from "react";

const Dashboard = () => {
  const [qrCode, setQrCode] = useState("Sample QR Code");
  const [showProfile, setShowProfile] = useState(false);
  const [activeSection, setActiveSection] = useState("QR Code");
  const [userDetails, setUserDetails] = useState(null);
  const [jwtToken, setJwtToken] = useState(""); // Use state to store the JWT token

  const [totalFuelUsage,setTotalFuelUsage]=useState("0");

  const [fuelAmount, setFuelAmount] = useState("0");
  const [transactions, setTransactions] = useState([]);


  const generateQrCode = () => {
    setQrCode(`QR-${Date.now()}`);
  };

  useEffect(() => {
    const token = localStorage.getItem('token');
    if (token && typeof token === 'string') {
      setJwtToken(token); // Set JWT token in the state
      fetchUserDetails(token);
      generateQRCode(token);
      fetchFuelAmount(token);
      fetchTotalFuelUsage(token);
      fetchTransactions(token);
    } else {
      window.location.href = "/login"; // Redirect if token is not found
    }
  }, []);

  const fetchUserDetails = async (token) => {
    try {
      const response = await fetch(`http://localhost:8080/api/user/details?token=${token}`, {
        method: 'GET',
        headers: {
          'Authorization': `Bearer ${token}`,
          'Content-Type': 'application/json',
        },
      });

      const data = await response.json();
      if (response.ok) {
        setUserDetails(data);
        setShowProfile(true);
      } else {
        alert('Failed to fetch user details');
      }
    } catch (error) {
      console.error('Error during request:', error);
    }
  };

  const fetchTotalFuelUsage = async (token) => {
    try {
      const response = await fetch("http://localhost:8080/api/report/user/fuel-usage", {
        method: "GET",
        headers: {
          Authorization: `Bearer ${token}`,
          "Content-Type": "application/json",
        },
      });

      if (response.status === 401) {
        alert("Session expired. Please log in again.");
        localStorage.removeItem("token");
        window.location.href = "/login";
        return;
      }

      const data = await response.json();
      if (response.ok) {
        setTotalFuelUsage(data);
      } else {
        alert("Failed to fetch total fuel usage");
      }
    } catch (error) {
      console.error("Error fetching fuel usage:", error);
      alert("Error fetching fuel usage");
    }
  };

 





  const generateQRCode = async (token) => {
    try {
      const response = await fetch(`http://localhost:8080/api/qr/generate?token=${token}`, {
        method: 'GET',
        headers: {
          'Authorization': `Bearer ${token}`,
          'Content-Type': 'application/json',
        },
      });

      if (response.ok) {
        const blob = await response.blob();
        const imageUrl = URL.createObjectURL(blob);
        setQrCode(imageUrl);
      } else {
        alert('Failed to generate QR code');
      }
    } catch (error) {
      console.error('Error during request:', error);
      alert('Error occurred during request');
    }
  };


  const updateQrCode = async () => {
    const isConfirmed = window.confirm("Are you sure you want to update your QR code?");
  
    if (isConfirmed) {
      try {
        const response = await fetch(`http://localhost:8080/api/qr/update?token=${jwtToken}`, {
          method: 'GET',
          headers: {
            'Authorization': `Bearer ${jwtToken}`,
            'Content-Type': 'application/json',
          },
        });
  
        if (response.ok) {
          const blob = await response.blob();
          const imageUrl = URL.createObjectURL(blob);
          setQrCode(imageUrl);
          alert("QR code updated successfully!"); // Success message
        } else {
          alert("Failed to generate QR code");
        }
      } catch (error) {
        console.error("Error during request:", error);
        alert("Error occurred during request");
      }
    } else {
      alert("QR code update canceled");
    }
  };
  

  const downloadQrCode = () => {
    const qrImage = document.getElementById("qrCodeImg");
  
    if (!qrImage || !qrImage.src) {
      alert("QR code is not available for download.");
      return;
    }
  
    try {
      const downloadLink = document.createElement("a");
      downloadLink.href = qrImage.src;
      downloadLink.download = "qr-code.png";
      document.body.appendChild(downloadLink);
      downloadLink.click();
      document.body.removeChild(downloadLink);
      alert("QR code downloaded successfully!");
    } catch (error) {
      console.error("Error downloading QR code:", error);
      alert("Failed to download QR code. Please try again.");
    }
  };
  
  const fetchFuelAmount = async (token) => {
    try {
      const response = await fetch(`http://localhost:8080/api/report/user/exsistingFuel`, {
        method: 'GET',
        headers: {
          'Authorization': `Bearer ${token}`,
          'Content-Type': 'application/json',
        },
      });
  
      const data = await response.json();
      if (response.ok) {
        setFuelAmount(data);
      } else {
        alert('Failed to fetch fuel data');
      }
    } catch (error) {
      console.error('Error during request:', error);
    }
  };
  const fetchTransactions = async (token) => {
    try {
      const response = await fetch(`http://localhost:8080/api/report/user/transaction`, {
        method: 'GET',
        headers: {
          'Authorization': `Bearer ${token}`,
          'Content-Type': 'application/json',
        },
      });
  
      const data = await response.json();
      if (response.ok) {
        setTransactions(data);
      } else {
        alert('Failed to fetch transactions');
      }
    } catch (error) {
      console.error('Error fetching transactions:', error);
    }
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

 const deleteAccount = async () => {
  const isConfirmed = window.confirm("Do you really want to delete this Account?");
  
  if (isConfirmed && jwtToken) {
    try {
      const response = await fetch(`http://localhost:8080/api/account/delete?token=${jwtToken}`, {
        method: "GET", 
        headers: {
          "Authorization": `Bearer ${jwtToken}`,
          "Content-Type": "application/json",
        },
      });

      if (response.ok) {
        alert("Your account has been deleted successfully.");
        localStorage.removeItem("jwtToken"); 
        window.location.href = "/register"; 
      } else {
        const errorMessage = await response.text();
        alert(`Failed to delete account: ${errorMessage}`);
      }
    } catch (error) {
      console.error("Error deleting account:", error);
      alert("An error occurred while deleting the account. Please try again.");
    }
  } else {
    alert("Account Deletion canceled");
  }
};


  return (
    <div className="min-h-screen bg-gray-700 text-gray-800 flex">
      {/* Navigation Menu */}
      <aside className="w-1/5 bg-gradient-to-b from-black to-red-500 text-white py-6 px-4">
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
            <p>Name: {userDetails ? userDetails.firstName : "Error occur"}</p>
            <p>MobileNumber: {userDetails ? userDetails.mobileNumber : "Error occur "}</p>
          </div>
        )}

        <nav className="flex flex-col space-y-4">
          <button
            onClick={() => setActiveSection("QR Code")}
            className={`px-4 py-2 rounded-md text-left ${activeSection === "QR Code" ? "bg-red-700" : "hover:bg-red-700"}`}
          >
            QR Code
          </button>
          <button
            onClick={() => setActiveSection("Reports")}
            className={`px-4 py-2 rounded-md text-left ${activeSection === "Reports" ? "bg-red-700" : "hover:bg-red-700"}`}
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
              className="bg-gray-100 flex justify-center items-center text-red-500 border-2 border-red-800 rounded-lg mx-auto p-3 mb-6"
            >
              <img id="qrCodeImg" src={qrCode} width={300} height={300} alt="" />
            </div>
            <div className="flex justify-center space-x-4">
              <button
                onClick={updateQrCode}
                className="bg-red-700 text-white px-4 py-2 rounded-md shadow-md hover:bg-red-800"
              >
                Update QR Code
              </button>
              <button
                onClick={downloadQrCode}
                className="bg-red-700 text-white px-4 py-2 rounded-md shadow-md hover:bg-red-800"
              >
                Download QR Code
              </button>
            </div>
          </div>
        )}

        {/* Reports Section */}
        {activeSection === "Reports" && (
          <div className="grid grid-cols-1 md:grid-cols-2 gap-6">
            {/* Reports content here */}

            <div className="bg-white text-red-800 rounded-lg shadow-lg p-6">
              <h2 className="text-2xl font-bold mb-4">Total Fuel Usage</h2>
              <p className="text-lg">{totalFuelUsage !== null ? `${totalFuelUsage} Liters` : "Loading..."}</p>
            </div>
            
            <div className="bg-white text-red-800 rounded-lg shadow-lg p-6">
              <h2 className="text-2xl font-bold mb-4">Current Amount of Fuel</h2>
              {fuelAmount ? (
                
                  <p className="text-lg">{fuelAmount} L</p>
                
              ) : (
                <p className="text-lg">Loading fuel data...</p>
              )}
            </div>

            <div className="bg-white text-red-800 rounded-lg shadow-lg p-6 w-full col-span-2">
            <h3 className="text-2xl font-bold mb-4">Last 10 Transactions</h3>

            <div className="overflow-x-auto">
              <table className="w-full border-collapse border border-red-300">
                <thead className="bg-red-800 text-white">
                  <tr>
                    <th className="border border-red-300 px-4 py-2 text-left">Time</th>
                    <th className="border border-red-300 px-4 py-2 text-left">Amount (L)</th>
                    <th className="border border-red-300 px-4 py-2 text-left">Fuel Station Name</th>
                  </tr>
                </thead>
                <tbody>
                  {transactions.length > 0 ? (
                    transactions.map((transaction, index) => (
                      <tr key={index} className="hover:bg-red-100">
                        <td className="border border-red-300 px-4 py-2">{transaction.transactionTime}</td>
                        <td className="border border-red-300 px-4 py-2">{transaction.fuelAmount}</td>
                        <td className="border border-red-300 px-4 py-2">{transaction.fuelStationName}</td>
                      </tr>
                    ))
                  ) : (
                    <tr>
                      <td colSpan="3" className="text-center p-4">No transactions found</td>
                    </tr>
                  )}
                </tbody>
              </table>
            </div>
          </div>


          </div>
        )}
      </main>
    </div>
  );
};

export default Dashboard;
