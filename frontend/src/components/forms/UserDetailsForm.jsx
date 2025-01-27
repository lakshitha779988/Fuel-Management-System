import React, { useRef, useState } from 'react';

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
const inputRefs = useRef([]);

  
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

const handleOTPChange = (value,index) => {
  const updateOTP = [...otp];
  updateOTP[index] = value;
  setOtp(updateOTP);

  if(value && index < otp.length - 1){
    inputRefs.current[index + 1].focus();
  }

  if(!value && index > 0){
    inputRefs.current[index - 1].focus();
  }
};



  const handleVerifyOTP = async(e) => {
   
    e.preventDefault();
    setLoading(true);

    const fullotp = otp.join('');
    if(!fullotp || otp.length!==6){
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
    verifyOtpForRegistration(confirmationResult,fullotp);
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
    <div className="min-h-screen bg-gray-50 flex items-center justify-center p-4">
       <div className="max-w-lg w-full bg-white rounded-lg shadow-md p-6">
       <h2 className="text-2xl font-bold text-gray-800 text-center mb-6">User Details</h2> 
      <form onSubmit={handleSubmit} className="space-y-4">
     
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

<div>
        <label htmlFor="email" className="block text-gray-700 font-semibold">Address:</label>
        <input id="email"
         type="email"
          placeholder="example@gmail.com"
          className="mt-1 block w-full p-2 border border-gray-300 rounded-md shadow-sm focus:ring-indigo-500 focus:border-indigo-500" 
          onChange={(e) => setEmail(e.target.value)}
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
          type="tel"
          pattern='[0-9]*'
          placeholder="Ex: 077 123 4567"
          className="mt-1 block w-full  p-2 border border-gray-300 rounded-md shadow-sm focus:ring-indigo-500 focus:border-indigo-500"
          value={mobileNumber}
          onChange={(e) => setMobileNumber(e.target.value.replace(/\D/g,''))}
        />
</div>
        <button onClick={handleSendOTP}  
        id="sign-in-button"
        className="mt-4 w-full bg-red-600 text-white py-2 rounded-md hover:bg-blue-700 transition"
        disabled={loading}
        >
          Send OTP
        </button>

        {otpSent && (
          <div className="mt-4">
            <label className="block text-gray-700 font-semibold">OTP:</label>
            <div className="flex space-x-2 mt-2">
              {otp.map((digit, index) => (
            
            <input
              id="otp"
              key={index}
              type="text"
              maxLength="1"
              className="w-12 h-12 text-center text-lg border border-gray-300 rounded-md shadow-sm focus:ring-indigo-500 focus:border-indigo-500"
              onChange={(e)=>handleOTPChange(e.target.value,index)} 
              value={digit}
             ref={(el) =>(inputRefs.current[index]=el)}
              
            />
              ))}
            </div>

            <button onClick={handleVerifyOTP}
              className="mt-4 w-full bg-red-600 text-white py-2 rounded-md hover:bg-blue-700 transition" >
              Verify OTP
            </button>
          </div>
        )}

        {otpVerified && <p className="text-green-600">OTP verified successfully!</p>}
        <button 
        type='submit'
        disabled={!otpVerified}
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
