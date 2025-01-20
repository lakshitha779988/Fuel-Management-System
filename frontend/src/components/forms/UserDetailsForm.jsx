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
    <div>
      <form onSubmit={handleSubmit}>
      <h3 class="some-class">User Details</h3> 


        <label htmlFor="firstName">First Name</label>
        <input id="firstName"
         type="text"
          placeholder="Ex: Saman"
          onChange={(e) => setFirstName(e.target.value)}
          />

        <label htmlFor="lastName">Last Name</label>
        <input id="lastName"
         type="text"
          placeholder="Ex: Perera"
          onChange={(e) => setLastName(e.target.value)}
          />

        <label htmlFor="address">Address</label>
        <input id="address"
         type="text"
          placeholder="Ex: 399/8, Station Road, Colombo" 
          onChange={(e) => setAddress(e.target.value)}
          />

        <label htmlFor="nic">NIC</label>
        <input id="nic"
         type="text"
          placeholder="Ex: 200134587570" 
          onChange={(e) => setNic(e.target.value)}
          />

        <label htmlFor="mobileNumber">Mobile Number</label>
        <input
          id="mobileNumber"
          type="text"
          placeholder="Ex: 077 123 4567"
          value={mobileNumber}
          onChange={(e) => setMobileNumber(e.target.value)}
        />

        <button onClick={handleSendOTP}>
          Send OTP
        </button>

        {otpSent && (
          <div>
            <label htmlFor="otp">OTP</label>
            <input
              id="otp"
              type="text"
              placeholder="Enter OTP"
              value={otp}
              onChange={(e) => setOtp(e.target.value)}
            />

            <button onClick={handleVerifyOTP}>
              Verify OTP
            </button>
          </div>
        )}

        {otpVerified && <p>OTP verified successfully!</p>}
        <button  disabled={!otpVerified} >Submit</button>
      </form>
    </div>
  );
}

export default UserDetailsForm;
