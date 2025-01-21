import React from 'react'

function VehicleDetailsForm() {
 


  return (
    <div>VehicleDetailsForm
  <form>
 <h2>Vehicle Details</h2>
 <div>
    <label htmlFor='letter'>Vehicle Number</label>
    <input
      type='text'
      id='letter'
      maxLength="3"
      value={letter}
      placeholder='ABC'
      />
 </div>
 <div>
<label htmlFor='number'>Vehicle Number</label>
<input
type='text'
id='number'
maxLength="4"
value={Number}
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
<select>
<option value="">Select Vechile Type</option>
<option value="Car">Car</option>
<option value="Bike">Bike</option>

<option value="Truck">Van</option>
</select>
</div>
<select>
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