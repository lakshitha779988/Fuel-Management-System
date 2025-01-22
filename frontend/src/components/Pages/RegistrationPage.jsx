import React from 'react'
import UserDetailsForm from '../forms/UserDetailsForm';
import VehicleDetailsForm from '../forms/VehicleDetailsForm';
import { useState } from 'react';



function RegistrationPage() {
const[currentStep,setCurrentStep]=useState(1);
const[userDetails,setUserDetails]=useState({});
const[vehicleDetails,setVehicleDetails]=useState({});

const handleUserDetailsSubmit=(details)=>{
  setUserDetails(details);
  setCurrentStep(2);
};
const handleVehicleDetailsSubmit=(details)=>{
  setVehicleDetails(details);
  console.log("User Details: ",userDetails);
  console.log("Vehicle Details: ",vehicleDetails);
  alert("Registration successful!");
};  


  return (

    <div className='min-h-screen bg-gray-100 flex items-center justify-center p-4'>
      <div className='max-w-lg w-full bg-white rounded-lg shadow-md p-6'>
        {currentStep===1?<UserDetailsForm handleUserDetailsSubmit={handleUserDetailsSubmit} />
        :<VehicleDetailsForm handleVehicleDetailsSubmit={handleVehicleDetailsSubmit} />}
      </div>
    </div>
  );
}

export default RegistrationPage;
