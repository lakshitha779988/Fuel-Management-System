import React, { useState } from 'react';

import {sendOtp, verifyOtpForRegistration } from '../../firebaseAuthService';


function UserDetailsForm() {



  const [otpSent, setOtpSent] = useState(false);
  const [otpVerified, setOtpVerified] = useState(false);
  const [otp, setOtp] = useState('');
  const [mobileNumber, setMobileNumber] = useState('');
  const[firstName, setFirstName] = useState('');
  const[lastName, setLastName] = useState('');
  const[address, setAddress] = useState('');
  const[email, setEmail] = useState('');
  const[nic, setNic] = useState('');
  const [loading, setLoading] = useState(false);
  const [alert, setAlert] = useState({ message: '', type: '', show: false });


  const[confirmationResult,setConfirmationResult]=useState('null');


  
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
    if (mobileExists) {
      setAlert({
        message: 'This mobile number already registerd',
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

  const handleVerifyOTP = async(e) => {
   
    e.preventDefault();
    setLoading(true);
    if(!otp || otp.length!==6){
      console.log(otp);
      setAlert({
        message: 'Please enter a valid 6-digit OTP',
        type: 'error',
        show: true
      });
      setLoading(false);


      return;
    }
   try{
    const massage = await
    verifyOtpForRegistration(confirmationResult,otp);
    setAlert(
      {
        message:'OTP verified successfully!',
        type:'success',
        show:true,
      });

      setOtpVerified(true)
    }
    catch(error){
      setAlert({
        message:error.message,
        type: 'error',
        show:true,

      }
    );
   }
    finally{
      setLoading(false);
    }  

  };
  const closeAlert=()=>{
    setAlert({...alert,show:false});
  };
const handleSubmit = (e) => {
  e.preventDefault();
  if (!otpVerified) {

    setAlert({ message: 'Please verify the OTP first.',
       type: 'error',
        show: true
       });

    return;
  }

  const userDetails = {
    firstName : firstName,
    lastName : lastName,
    address : address,
    nic: nic,
    mobileNumber : mobileNumber,
    email :email,
  };
  localStorage.setItem("userDetails", JSON.stringify(userDetails));
  console.log(localStorage.getItem("userDetail"))
  window.window.location.href = "/VehicleDetails";
setAlert({
  message:'Form submitted successfully!',
  type:'success',
  show:true,

});

 
  

};

  return (

    <div className="flex items-center justify-center relative bg-gradient-to-br from-red-900 via-red-700 to-red-500"
    style={{
      backgroundImage: `url('../../public/login11.jpg')`,
      backgroundRepeat: 'no-repeat',
      backgroundSize: 'cover',
      minHeight: '100vh', 
  
    }}>
    
       <div className="max-w-lg w-full bg-white rounded shadow-md p-6 my-6 min-h-[400px]">
       <h2 className="text-2xl font-bold text-gray-800 text-center mb-6">User Details</h2> 
      <form onSubmit={handleSubmit} className="space-y-4 min-h-[400px]" style={{ position:"relative" }}>
     
      {alert.show && 
      (
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
   
<div className="grid grid-cols-2 gap-6">    
<div>
        <label htmlFor="firstName" className="block text-sm font-medium text-gray-700">First Name:</label>
        <input id="firstName"
         type="text"
          placeholder="Ex: Saman"
          className="mt-1 block w-full p-2 border border-gray-300 rounded shadow-sm focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm"   
          onChange={(e) => setFirstName(e.target.value)}
          />

</div>
<div>
        <label htmlFor="lastName" className="block text-sm font-medium text-gray-700">Last Name:</label>
        <input id="lastName"
         type="text"
          placeholder="Ex: Perera"
          className="mt-1 block w-full p-2 border border-gray-300 rounded shadow-sm focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm"
          onChange={(e) => setLastName(e.target.value)}
          />
</div>
<div className="col-span-2">
        <label htmlFor="address" className="block text-sm font-medium text-gray-700">Address:</label>
        <input id="address"
         type="text"
          placeholder="Ex: 399/8, Station Road, Colombo"
          className="mt-1 block w-full p-2 border border-gray-300 rounded shadow-sm focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm" 
          onChange={(e) => setAddress(e.target.value)}
          />
</div>

<div className="col-span-2">
        <label htmlFor="email" className="block text-sm font-medium text-gray-700">E-mail:</label>
        <input id="email"
         type="email"
          placeholder="example@gmail.com"
          className="mt-1 block w-full p-2 border border-gray-300 rounded shadow-sm focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm" 
          onChange={(e) => setEmail(e.target.value)}
          />
</div>
<div>
        <label htmlFor="nic" className="block text-sm font-medium text-gray-700">NIC:</label>
        <input id="nic"
         type="text"
          placeholder="Ex: 200134587570" 
          className="mt-1 block w-full p-2 border border-gray-300 rounded shadow-sm focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm"
          onChange={(e) => setNic(e.target.value)}
          />

</div>          
<div>
        <label htmlFor="mobileNumber"className="block text-sm font-medium text-gray-700">
          
          Mobile Number:</label>
        <input
          id="mobileNumber"
          type="tel"
          pattern='[0-9]*'
          placeholder="Ex: 077 123 4567"
          className="mt-1 block w-full  p-2 border border-gray-300 rounded shadow-sm focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm"
          value={mobileNumber}
          onChange={(e) => setMobileNumber(e.target.value.replace(/\D/g,''))}
        />
</div>
<div className="col-span-2">
        <button onClick={handleSendOTP}  
        id="sign-in-button"
        className="mt-4 w-full bg-gradient-to-r from-red-500 to-purple-500 text-white py-2 rounded hover:opacity-90 focus:outline-none focus:ring-2 focus:ring-purple-400 focus:ring-offset-2 transition"
        disabled={loading}
        >
          Send OTP
        </button>
        </div>

        {otpSent && (
          <div className="mt-4">
            <label htmlFor="otp" className="block text-sm font-medium text-gray-700">
              OTP
              </label>
            <input
              id="otp"
              type="text"
              placeholder="Enter the 6-digit OTP"
              className='mt-1 block w-full p-2 border border-gray-300 rounded shadow-sm focus:ring-indigo-500 focus:border-indigo-500'
              value={otp}
              onChange={(e) => setOtp(e.target.value)}
              
            />


            <button onClick={handleVerifyOTP}
              className="mt-4 w-full bg-gradient-to-r from-red-500 to-purple-500 text-white py-2 rounded hover:opacity-90 focus:outline-none focus:ring-2 focus:ring-purple-400 focus:ring-offset-2 transition" >
              Verify OTP
            </button>
          </div>
        )}

        {otpVerified && <p className="text-green-600">OTP verified successfully!</p>}
        <div className="col-span-2">
        <button 
        type='submit'
        disabled={!otpVerified}
         className={` mt-1 w-full py-2 rounded text-white ${
          otpVerified ? 'bg-indigo-600 hover:bg-indigo-700' : 'bg-gray-400 cursor-not-allowed'
        } transition`} >
          Submit
          </button>
          </div>
          </div> 
      </form>
    </div>

    
    </div> 
  );

}

export default UserDetailsForm;
