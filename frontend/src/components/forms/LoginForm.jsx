import React, { useState } from 'react';
import { sendOtp,verifyOtp } from '../../firebaseAuthService';

function LoginForm() {
  const [otpSent, setOtpSent] = useState(false);
  const [otp, setOtp] = useState('');
  const [mobileNumber, setMobileNumber] = useState('');
  const [loading, setLoading] = useState(false);
  const [alert, setAlert] = useState({ message: '', type: '', show: false });
  const [confirmationResult, setConfirmationResult] = useState(null);

  
  const checkMobileExistence = async (mobileNumber) => {
    try {
      const response = await fetch(`http://localhost:8080/api/user/check-mobile-existence?mobileNumber=${mobileNumber}`);
      const data = await response.json();
      return data.exists; 
    } catch (error) {
      console.error('Error checking mobile number:', error);
      setAlert({ message: 'Error checking mobile number.', type: 'error', show: true });
      return false;
    }
  };

 
  const handleSendOTP = async (e) => {
    e.preventDefault();
    setLoading(true);

    if (!mobileNumber || mobileNumber.length < 9) {
      setAlert({
        message: 'Please enter a valid mobile number (9 digits)',
        type: 'error',
        show: true
      });
      setLoading(false);
      return;
    }

    const mobileExists = await checkMobileExistence(mobileNumber);
    if (!mobileExists) {
      setAlert({
        message: 'This mobile number is not registered.',
        type: 'error',
        show: true
      });
      setLoading(false);
      return;
    }

    try {
      const result = await sendOtp(mobileNumber); 
      setConfirmationResult(result);
      setOtpSent(true);
      setAlert({
        message: 'OTP sent successfully!',
        type: 'success',
        show: true
      });
    } catch (error) {
      setAlert({
        message: error.message,
        type: 'error',
        show: true
      });
    } finally {
      setLoading(false);
    }
  };

  const handleVerifyOTP = async (e) => {
    e.preventDefault();
    setLoading(true);

    if (!otp || otp.length !== 6) {
      setAlert({
        message: 'Please enter a valid 6-digit OTP',
        type: 'error',
        show: true
      });
      setLoading(false);
      return;
    }

    try {
      const data = await verifyOtp(confirmationResult, otp);
      const jwtToken = data.token;
      localStorage.setItem('token', jwtToken);
      const cleanedMobileNumber = mobileNumber.replace(/^\+94/, '0');
      localStorage.setItem('mobileNumber' , cleanedMobileNumber)
      console.log(jwtToken);
      window.location.href = "/dashboard";
      setAlert({
        message: 'Login successful!',
        type: 'success',
        show: true
      });

      
    } catch (error) {
      setAlert({
        message: error.message,
        type: 'error',
        show: true
      });
    } finally {
      setLoading(false);
    }
  };

  const closeAlert = () => {
    setAlert({ ...alert, show: false });
  };

  return (

    <div className="h-screen flex items-center justify-center relative overflow-hidden bg-gradient-to-br from-red-900 via-red-700 to-red-500"
    style={{
      backgroundImage: `url('../../public/login11.jpg')`,
      backgroundRepeat: 'no-repeat', 
      backgroundSize: 'cover', 
  
    }}>
    
    <div className="flex justify-center items-center min-h-[300px] ">
  <form className="bg-white p-8 rounded  shadow-custom w-full max-w-md my-6" style={{ position:"relative",width:"400px" }}>
    {/* Login Header */}
    
    
      
      <div style={{ position: "absolute", top: 0, left: 0, right: 0 }}>
    <h1 className="flex items-center justify-center bg-gradient-to-r from-red-500 to-purple-500 text-4xl  text-white py-6 px-6">
      Get Started!
    </h1>
    </div>
    

    {/* Register Link */}
    
    
    <p className="text-xs font-semibold text-gray-600 mt-16">
      <h2 className="text-black  text-xl mb-2">USER LOGIN</h2>
      Don't have an account?{' '}
      <a href="/UserDetails" className="text-red-500 underline hover:text-purple-500">
        Register here
      </a>
    </p>
    

    {/* Alert Message */}
    {alert.show && (
      <div
        className={`mb-4 p-3 ${
          alert.type === 'success' ? 'bg-green-100 text-green-700' : 'bg-red-100 text-red-700'
        }`}
      >
        <p>{alert.message}</p>
        <button onClick={closeAlert} className="mt-2 text-sm underline">
          Close
        </button>
      </div>
    )}

    {/* Mobile Number Input */}
    <div className="mt-6 mb-4 ">
      <label htmlFor="mobile" className="block text-sm font-medium text-gray-700">
        Mobile Number
      </label>
      <input
        id="mobile"
        type="tel"
        pattern="[0-9]*"
        value={mobileNumber}
        placeholder="Enter your mobile number"
        onChange={(e) => setMobileNumber(e.target.value.replace(/\D/g, ''))}
        className="mt-1 block w-full px-3 py-2 border rounded border-gray-300 shadow-sm focus:ring-purple-500 focus:border-purple-500 sm:text-sm"
        disabled={loading || otpSent}
      />
    </div>

    {/* OTP Button */}
    {!otpSent && (
      <div className="mb-2 mt-8">
        <button
          id="sign-in-button"
          onClick={handleSendOTP}
          disabled={loading}
          className={`w-full bg-gradient-to-r from-red-500 to-purple-500 rounded text-white py-2 px-4 hover:opacity-90 focus:outline-none focus:ring-2 focus:ring-purple-400 focus:ring-offset-2 ${
            loading ? 'opacity-50 cursor-not-allowed' : ''
          }`}
        >
          {loading ? 'Sending...' : 'Send OTP'}
        </button>
      </div>
    )}

    {/* OTP Input and Verify Button */}
    {otpSent && (
      <>
        <div className="mb-4">
          <label htmlFor="otp" className="block text-sm font-medium text-gray-700">
            OTP
          </label>
          <input
            id="otp"
            type="text"
            pattern="[0-9]*"
            maxLength="6"
            placeholder="Enter the 6-digit OTP"
            value={otp}
            onChange={(e) => setOtp(e.target.value.replace(/\D/g, ''))}
            className="mt-1 block w-full px-3 py-2 border border-gray-300 shadow-sm focus:ring-purple-500 focus:border-purple-500 sm:text-sm"
            disabled={loading}
          />
        </div>
        <div className="mb-4">
          <button
            onClick={handleVerifyOTP}
            disabled={loading}
            className={`w-full bg-gradient-to-r from-red-500 to-purple-500 text-white py-2 px-4 hover:opacity-90 focus:outline-none focus:ring-2 focus:ring-purple-400 focus:ring-offset-2 ${
              loading ? 'opacity-50 cursor-not-allowed' : ''
            }`}
          >
            {loading ? 'Verifying...' : 'Verify OTP'}
          </button>
        </div>
      </>
    )}
  </form>
</div>

    </div>
  );
}

export default LoginForm;
