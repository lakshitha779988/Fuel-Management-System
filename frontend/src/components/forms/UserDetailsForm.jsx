import React from 'react'

function UserDetailsForm() {
  return (
    <div>
        <form>
        
  
      <>
      
      <h3>User Details</h3>

      <label htmlFor="First Name" >First Name</label>
      <input id="First Name" type="text" placeholder="Ex:Saman" />

      <label htmlFor="Last Name" >Last Name</label>
      <input id="Last Name" type="text" placeholder="Ex: Perera" />

      <label htmlFor="Address" >Address</label>
      <input id="Address" type="text" placeholder="Ex: 399/8, Station Road, Colombo" />

      <label htmlFor="NIC" >NIC</label>
      <input id="NIC" type="text" placeholder="Ex: 200134587570" />

      <label htmlFor="Mobile Number" >Mobile Number</label>
      <input id="Mobile Number" type="text" placeholder="Ex: 077 123 4567" />

      </>

  </form>
  </div>
  )

}

export default UserDetailsForm