import React, { useEffect, useState } from 'react'

function VehicleDetailsForm() {
 const [vehicleType,setVehicleType]=useState('');
 const [vehicleTypes,setVehicleTypes]=useState([]);
 const[fuelType,setFuelType]=useState('');
 const[letter,setLetter]=useState('');
 const[number,setNumber]=useState('');
 const[submitted,setSubmitted]=useState('false');
 const[chassisNumber,setChassisNumber]=useState('');
const[vehicleDetails,setVehicleDetails] = useState({registrationNumber: '',
  chassisNumber: '',
  vehicleType: '',
  fuelType: '',});


 const fetchVehicleType = async () => {

  try {
    const response = await fetch('http://localhost:8080/api/vehicle-types', {
      method: 'GET',
      headers: {
        'Content-Type': 'application/json',
      },
    });

    if (!response.ok) {
      throw new Error(`Error: ${response.status}`);
    }

    const vehicleTypes = await response.json();
    console.log('Fetched Vehicle Types:', vehicleTypes);

    // Do something with the vehicle types, e.g., update state
    return vehicleTypes;
  } catch (error) {
    console.error('Failed to fetch vehicle types:', error);
    return [];
  }
};


const sendRegistrationRequest = async () => {
  
  
  const userDetails = JSON.parse(localStorage.getItem('userDetails'));

  if (!userDetails) {
    
    alert("User details are missing.");
    return;
  }

  
  const registrationRequest = {
    mobileNumber: userDetails.mobileNumber, 
    firstName: userDetails.firstName,  
    lastName: userDetails.lastName,  
    address: userDetails.address,  
    email: userDetails.email,
    nationalId: userDetails.nic,  
    chaseNumber: vehicleDetails.chassisNumber, 
    vehicleNumber: vehicleDetails.registrationNumber,  
    vehicleTypeId: vehicleDetails.vehicleType,  
    fuelType: vehicleDetails.fuelType, 
  };

  try {
   
    const response = await fetch('http://localhost:8080/api/auth/register', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(registrationRequest),
    });

    const responseData = await response.text();

    if (response.ok) {
    
      alert("Registration successful!");
      window.location.href = "/login";  
    } else {
      
      alert(responseData.message || "An error occurred during registration.");
    }
  } catch (error) {
    // 6. Handle network or unexpected errors
    console.error("Error during registration:", error);
    alert("An error occurred while processing your request.");
  }
};


useEffect(() => {
  const getVehicleTypes = async () => {
    const types = await fetchVehicleType();
    setVehicleTypes(types);
  };

  getVehicleTypes();
}, []);





const handleSubmit = async (e) => {
  e.preventDefault();
 
  const vehicleDetails = {
    vehicleType,
    fuelType,
    registrationNumber: `${letter}-${number}`,
    chassisNumber,
  };

  setVehicleDetails(vehicleDetails);

  //console.log('Vehicle Details Submitted:', vehicleDetails);

  try {
    
    const response = await fetch('http://localhost:8080/api/vehicles/check-vehicle-details', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(vehicleDetails), 
    });

    if (!response.ok) {
      const errorData = await response.text();
      alert(errorData.message);  // This is a basic alert, you can replace it with UI error display
      return;
    }

    

    const result = await response.text();

    sendRegistrationRequest();
    
  } catch (error) {
    console.error('Error submitting vehicle details:', error);
    alert('Failed to register vehicle.');
  }
};
 const handleLetterChange = (e) =>
    {
        setLetter(e.target.value.toUpperCase());
 };
 const handleNumberChange = (e) =>
 {
  setNumber(e.target.value.replace(/\D/g, ''));
 };


 const handleChassisNumber = (e) =>{
    setChassisNumber(e.target.value);
 };

  return (
    <div className='min-h-screen bg-gray-100 flex items-center justify-center p-4'>
     <div className="max-w-lg w-full bg-white rounded-lg shadow-md p-6"> 
  <form onSubmit={handleSubmit} className='space-y-4'>
 <h2 className='text-2xl font-bold text-gray-800 text-center mb-6'>Vehicle Details</h2>
 <div className='flex space-x-4' >
  <div className="flex-1">
    <label htmlFor='letter' className='block text-gray-700 font-semibold'>Vehicle Number:</label>
    <input
      type='text'
      id='letter'
      maxLength="3"
      value={letter}
      onChange={handleLetterChange}
      placeholder='ABC'
      className='mt-1 block w-full p-2 border border-gray-300 rounded-md shadow-sm focus:ring-indigo-500 focus:border-indigo-500'
      />
 </div>
 <div className='flex-1'>
 <label htmlFor='number' className='block text-gray-700 font-semibold'>Digit</label>
<input
type='text'
id='number'
maxLength="4"
value={number}
onChange={handleNumberChange}
placeholder='1234'
className='mt-1 block w-full p-2 border border-gray-300 rounded-md shadow-sm focus:ring-indigo-500 focus:border-indigo-500'
/>
</div>
</div>
<div>
<label htmlFor='chassis number ' className='block text-gray-700 font-semibold'>Chassis Number</label>
<input 
type='text'
id='Chassis Number'
value={chassisNumber}
onChange={handleChassisNumber}
placeholder='1236AC5685AF5'
className='mt-1 block w-full p-2 border border-gray-300 rounded-md shadow-sm focus:ring-red-500 focus:border-indigo-500'
/>

</div>
<div>
<label htmlFor='VehicleType' className='block text-gray-700 font-semibold'>VehicleType</label>
<select
id='vehicleType'
value={vehicleType}
onChange={(e) =>setVehicleType(e.target.value)}
className="mt-1 block w-full p-2 border border-gray-300 rounded-md shadow-sm focus:ring-red-500 focus:border-indigo-500"
style={{ backgroundColor: 'white' }}
>
<option value="">Select Vechile Type</option>
{vehicleTypes.map((type) => (
        <option key={type.id} value={type.id}>
          {type.vehicleTypeName}
        </option>
      ))}
</select>
</div>

<div>
<label htmlFor="fuelType" className="block text-gray-700 font-semibold">Fuel Type:</label>
<select
id='fuelType'
value={fuelType}
onChange={(e) =>setFuelType(e.target.value)}
className="mt-1 block w-full p-2 border border-gray-300 rounded-md shadow-sm focus:ring-indigo-500 focus:border-indigo-500"
style={{ backgroundColor: 'white' }}
>
<option value="">Select Fuel Type</option>
<option value="Petrol">Petrol</option>
<option  value="Diesel">Diesel</option>

</select>
</div>
<div>
<button type="submit"
 className="mt-4 w-full bg-red-600 text-white py-2 rounded-md hover:bg-red-700 transition"
 href="/login"
 >Submit
  </button>

</div>




 

  </form>



</div>
</div>


    
  );
}

export default VehicleDetailsForm