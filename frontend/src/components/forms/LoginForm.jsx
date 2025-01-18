import React from 'react'
import { useState } from 'react';

function LoginForm() {
    const [otpSent, setOtpSent] = useState(false);
    const [otp, setOtp] = useState('');
    const [mobileNumber,setMobileNumber] = useState('');

    const handleSendOTP = () => {
        if(!mobileNumber){
            alert('Please enter a valid mobile number.');
            return;
        }
        alert('OTP will be sent soon..');
        setOtpSent(true);

    };

    const handleVerifyOTP = () => {
        alert('Login successful!.');
    };


  return (
    <div>
      <form>
            <label htmlFor="mobile">Mobile Number</label>
            <input id="mobile" 
            type="text"
            value={mobileNumber}
            placeholder="Enter your mobile number"
            onChange={(e) => setMobileNumber(e.target.value)}/>
            <button onClick={handleSendOTP}>Sent OTP</button>
            {otpSent && (
                <>
                   <label htmlFor="otp">OTP</label>
                    <input
                    id="otp"
                    type="text"
                    placeholder="Enter the OTP"
                    value={otp}
                    onChange={(e) => setOtp(e.target.value)}
                    />
                    <button onClick={handleVerifyOTP}>Verify OTP</button>

                </>
            )}
        </form>
    </div>
  );
}

export default LoginForm;
