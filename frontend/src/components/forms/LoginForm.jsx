import React from 'react'
import { useState } from 'react';

function LoginForm() {
    const [otpSent, setOtpSent] = useState(false);
    const [otp, setOtp] = useState('');
    const [mobileNumber,setMobileNumber] = useState('');


  return (
    <div>
      <form>
            <label for="mobile">Mobile Number</label>
            <input id="mobile" 
            type="text"
            value={mobileNumber}
            placeholder="Enter your mobile number"
            onChange={(e) => setMobileNumber(e.target.value)}/>
            <button>Sent OTP</button>
            {otpSent && (
                <>
                   <label for="otp">OTP</label>
                    <input
                    id="otp"
                    type="text"
                    placeholder="Enter the OTP"
                    value={otp}
                    onChange={(e) => setOtp(e.target.value)}
                    />
                    <button>Verify OTP</button>

                </>
            )}
        </form>
    </div>
  );
}

export default LoginForm;
