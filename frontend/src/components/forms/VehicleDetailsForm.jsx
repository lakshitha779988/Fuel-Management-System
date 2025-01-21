import React, { useState } from 'react'

function VehicleDetailsForm() {
 const [vehicleType,setVehicleType]=useState('');
 const[fuelType,setFuelType]=useState('');
 const[letter,setLetter]=useState('');
 const[number,setNumber]=useState('');
 const[submitted,setSubmitted]=useState('false');
 const[chassisNumber,setChassisNumber]=useState('false');

const  handleSubmit = (event) =>{
    event.preventDefault();
    if (vehicleType && fuelType && letter && number && chassisNumber) {
        console.log("Vehicle Details Submitted: ", { vehicleType, fuelType, letter, number, chassisNumber });
        setSubmitted(true); 
        alert("Form submitted sucessfully!");
      } else {
        alert("Please fill in all the fields.");
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
<option value="Car">Car</option>
<option value="Bike">Bike</option>

<option value="Truck">Van</option>
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