import React, { useState } from 'react';
import { Link } from 'react-router-dom';

function UserDetailsForm() {
  const [otpSent, setOtpSent] = useState(false);
  const [otpVerified, setOtpVerified] = useState(false);
  const [otp, setOtp] = useState('');
  const [mobileNumber, setMobileNumber] = useState('');
  const[firstName, setFirstName] = useState('');
  const[lastName, setLastName] = useState('');
  const[address, setAddress] = useState('');
  const[nic, setNic] = useState('');

  const handleSendOTP = (event) => {
    event.preventDefault();
    if (!mobileNumber) {
      alert('Please enter a valid mobile number.');
      return;
    }
    alert('OTP will be sent soon..');
    setOtpSent(true);
  };

  const handleVerifyOTP = (e) => {
   
    e.preventDefault();
   
      alert('OTP Verified!');
      setOtpVerified(true);

  };
const handleSubmit = (e) => {
  e.preventDefault();
  if (!otpVerified) {
    alert('Please verify the OTP first.');
    return;
  }

  const userDetails = {
    firstName,
    lastName,
    address,
    nic,
    mobileNumber,
  };
  console.log('Form Submitted:', userDetails);

  
  alert('User details submitted successfully!');
};
  return (
    <div className="min-h-screen bg-gray-50 flex items-center justify-center p-4">
       <div className="max-w-lg w-full bg-white rounded-lg shadow-md p-6">
       <h2 className="text-2xl font-bold text-gray-800 text-center mb-6">User Details</h2> 
      <form onSubmit={handleSubmit} classname="space-y-4">
        
     
<div>
        <label htmlFor="firstName" className="block text-gray-700 font-semibold">First Name:</label>
        <input id="firstName"
         type="text"
          placeholder="Ex: Saman"
          className="mt-1 block w-full p-2 border border-gray-300 rounded-md shadow-sm focus:ring-indigo-500 focus:border-indigo-500"   
          onChange={(e) => setFirstName(e.target.value)}
          />
</div>
<div>
        <label htmlFor="lastName" className="block text-gray-700 font-semibold">Last Name:</label>
        <input id="lastName"
         type="text"
          placeholder="Ex: Perera"
          className="mt-1 block w-full p-2 border border-gray-300 rounded-md shadow-sm focus:ring-indigo-500 focus:border-indigo-500"
          onChange={(e) => setLastName(e.target.value)}
          />
</div>
<div>
        <label htmlFor="address" className="block text-gray-700 font-semibold">Address:</label>
        <input id="address"
         type="text"
          placeholder="Ex: 399/8, Station Road, Colombo"
          className="mt-1 block w-full p-2 border border-gray-300 rounded-md shadow-sm focus:ring-indigo-500 focus:border-indigo-500" 
          onChange={(e) => setAddress(e.target.value)}
          />
</div>

        <label htmlFor="nic" className="block text-gray-700 font-semibold">NIC:</label>
        <input id="nic"
         type="text"
          placeholder="Ex: 200134587570" 
          className="mt-1 block w-full p-2 border border-gray-300 rounded-md shadow-sm focus:ring-indigo-500 focus:border-indigo-500"
          onChange={(e) => setNic(e.target.value)}
          />
<div>
        <label htmlFor="mobileNumber"className="block text-gray-700 font-semibold">
          
          Mobile Number:</label>
        <input
          id="mobileNumber"
          type="text"
          placeholder="Ex: 077 123 4567"
          classname="mt-1 block w-full  p-2 border border-gray-300 rounded-md shadow-sm focus:ring-indigo-500 focus:border-indigo-500"
          value={mobileNumber}
          onChange={(e) => setMobileNumber(e.target.value)}
        />
</div>
        <button onClick={handleSendOTP}  
        className="mt-4 w-full bg-blue-600 text-white py-2 rounded-md hover:bg-blue-700 transition"
        >Send OTP
        </button>

        {otpSent && (
          <div className="mt-4">
            <label htmlFor="otp" className="block text-sm font-medium text-gray-700">OTP</label>
            <input
              id="otp"
              type="text"
              placeholder="Enter OTP"
              className='mt-1 block w-full p-2 border border-gray-300 rounded-md shadow-sm focus:ring-indigo-500 focus:border-indigo-500'
              value={otp}
              onChange={(e) => setOtp(e.target.value)}
            />

            <button onClick={handleVerifyOTP}
              className="mt-4 w-full bg-green-600 text-white py-2 rounded-md hover:bg-blue-700 transition" >
              Verify OTP
            </button>
          </div>
        )}

        {otpVerified && <p className="text-green-600">OTP verified successfully!</p>}
        <button  disabled={!otpVerified}
         className={` mt-6 w-full py-2 rounded-md text-white ${
          otpVerified ? 'bg-indigo-600 hover:bg-indigo-700' : 'bg-gray-400 cursor-not-allowed'
        } transition`} >
          Submit
          </button>
      </form>
    </div>
    </div>
  );
}

export default UserDetailsForm;
