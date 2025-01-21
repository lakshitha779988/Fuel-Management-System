import React, { useState } from 'react';

function LoginForm() {
  const [otpSent, setOtpSent] = useState(false);
  const [otp, setOtp] = useState('');
  const [mobileNumber, setMobileNumber] = useState('');
  const [alert, setAlert] = useState({ message: '', type: '', show: false });

  const handleSendOTP = (e) => {
    e.preventDefault();
    if (!mobileNumber) {
      setAlert({ message: 'Please enter a valid mobile number.', type: 'error', show: true });
      return;
    }
    setAlert({ message: 'OTP will be sent soon.', type: 'success', show: true });
    setOtpSent(true);
  };

  const handleVerifyOTP = (e) => {
    e.preventDefault();
    if (!otp) {
      setAlert({ message: 'Please enter the OTP.', type: 'error', show: true });
      return;
    }
    setAlert({ message: 'Login successful!', type: 'success', show: true });
  };

  const closeAlert = () => {
    setAlert({ ...alert, show: false });
  };

  const AlertModal = ({ message, type, show }) => {
    if (!show) return null;

    const modalStyles = {
      success: 'bg-green-100 text-green-700 border-green-400',
      error: 'bg-red-100 text-red-700 border-red-400',
    };

    return (
      <div className="fixed inset-0 bg-gray-900 bg-opacity-50 flex justify-center items-center z-50">
        <div className={`p-6 bg-white rounded-lg shadow-lg w-80 ${modalStyles[type]}`}>
          <p className="mb-4">{message}</p>
          <button
            onClick={closeAlert}
            className="w-full bg-yellow-500 text-white py-2 px-4 rounded-md hover:bg-yellow-600 focus:outline-none focus:ring-2 focus:ring-blue-400"
          >
            Close
          </button>
        </div>
      </div>
    );
  };

  return (
    <div className="flex justify-center items-center min-h-screen bg-gray-100">
      <form className="bg-white p-6 rounded-lg shadow-md w-full max-w-sm">
      <h2 className="text-2xl font-bold text-red-500 text-center mb-4">
  LOGIN
  <p className="text-sm text-gray-600 mt-2">
    Don't have an account?{' '}
    <a
      href="/register"
      className="text-red-500 underline hover:text-blue-600"
    >
      Register here
    </a>
  </p>
</h2>


        
        <AlertModal message={alert.message} type={alert.type} show={alert.show} />

        <div className="mb-4">
          <label htmlFor="mobile" className="block text-sm font-medium text-gray-700">
            Mobile Number
          </label>
          <input
            id="mobile"
            type="text"
            value={mobileNumber}
            placeholder="Enter your mobile number"
            onChange={(e) => setMobileNumber(e.target.value)}
            className="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:ring-blue-500 focus:border-blue-500 sm:text-sm"
          />
        </div>
        <div className="mb-4">
          <button
            onClick={handleSendOTP}
            className="w-full bg-red-500 text-white py-2 px-4 rounded-md hover:bg-red-600 focus:outline-none focus:ring-2 focus:ring-blue-400 focus:ring-offset-2"
          >
            Send OTP
          </button>
        </div>
        {otpSent && (
          <>
            <div className="mb-4">
              <label htmlFor="otp" className="block text-sm font-medium text-gray-700">
                OTP
              </label>
              <input
                id="otp"
                type="text"
                placeholder="Enter the OTP"
                value={otp}
                onChange={(e) => setOtp(e.target.value)}
                className="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:ring-blue-500 focus:border-blue-500 sm:text-sm"
              />
            </div>
            <div className="mb-4">
              <button
                onClick={handleVerifyOTP}
                className="w-full bg-red-600 text-white py-2 px-4 rounded-md hover:bg-red-700 focus:outline-none focus:ring-2 focus:ring-green-400 focus:ring-offset-2"
              >
                Verify OTP
              </button>
            </div>
          </>
        )}
      </form>
    </div>
  );
}

export default LoginForm;
