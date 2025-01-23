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
      const response = await fetch(`http://localhost:8080/api/check-mobile-existence?mobileNumber=${mobileNumber}`);
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
      const { token } = await verifyOtp(confirmationResult, otp);
      localStorage.setItem('token', token);
      
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
    <div className="flex justify-center items-center min-h-screen bg-red-100">
      <form className="bg-white p-6 rounded-lg shadow-md w-full max-w-sm">
        <h2 className="text-2xl font-bold text-red-500 text-center mb-4">
          LOGIN
          <p className="text-sm text-gray-600 mt-2">
            Don't have an account?{' '}
            <a href="/UserDetails" className="text-red-500 underline hover:text-blue-600">
              Register here
            </a>
          </p>
        </h2>

        {alert.show && (
          <div className={`mb-4 p-3 rounded ${
            alert.type === 'success' ? 'bg-green-100 text-green-700' : 'bg-red-100 text-red-700'
          }`}>
            <p>{alert.message}</p>
            <button 
              onClick={closeAlert}
              className="mt-2 text-sm underline"
            >
              Close
            </button>
          </div>
        )}

        <div className="mb-4">
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
            className="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:ring-blue-500 focus:border-blue-500 sm:text-sm"
            disabled={loading || otpSent}
          />
        </div>

        {!otpSent && (
          <div className="mb-4">
            <button
              id="sign-in-button"
              onClick={handleSendOTP}
              disabled={loading}
              className={`w-full bg-red-500 text-white py-2 px-4 rounded-md hover:bg-red-600 focus:outline-none focus:ring-2 focus:ring-blue-400 focus:ring-offset-2 ${
                loading ? 'opacity-50 cursor-not-allowed' : ''
              }`}
            >
              {loading ? 'Sending...' : 'Send OTP'}
            </button>
          </div>
        )}

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
                className="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:ring-blue-500 focus:border-blue-500 sm:text-sm"
                disabled={loading}
              />
            </div>
            <div className="mb-4">
              <button
                onClick={handleVerifyOTP}
                disabled={loading}
                className={`w-full bg-red-600 text-white py-2 px-4 rounded-md hover:bg-red-700 focus:outline-none focus:ring-2 focus:ring-green-400 focus:ring-offset-2 ${
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
  );
}

export default LoginForm;
