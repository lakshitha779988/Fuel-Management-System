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
    setNumber(e.target.value.replace(/\D/g,''));
 };

  return (
    <div>
  <form onSubmit={handleSubmit}>
 <h2>Vehicle Details</h2>
 <div>
    <label htmlFor='letter'>Vehicle Number</label>
    <input
      type='text'
      id='letter'
      maxLength="3"
      value={letter}
      onChange={handleLetterChange}
      placeholder='ABC'
      />
 </div>
 <div>
<label htmlFor='number'>Vehicle Number</label>
<input
type='text'
id='number'
maxLength="4"
value={number}
onChange={handleNumberChange}
placeholder='1234'
/>
<div>
<label htmlFor='chassis number '>Chassis Number</label>
<input 
type='text'
id='Chassis Number'
placeholder='1236AC5685AF5'
/>

</div>
<div>
<label htmlFor='VehicleType'>VehicleType</label>
<select
id='vehicleType'
value={vehicleType}
onChange={(e) =>setVehicleType(e.target.value)}

>
<option value="">Select Vechile Type</option>
<option value="Car">Car</option>
<option value="Bike">Bike</option>

<option value="Truck">Van</option>
</select>
</div>
<select
id='fuelType'
value={fuelType}
onChange={(e) =>setFuelType(e.target.value)}

>
<option value="">Select Fuel Type</option>
<option value="Petrol">Petrol</option>
<option  value="Diesel">Diesel</option>

</select>

<div>
<button type="submit" href="/login">Submit </button>

</div>




 </div>

  </form>






    </div>
  )
}

export default VehicleDetailsForm