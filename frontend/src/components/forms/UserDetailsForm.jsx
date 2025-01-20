import React from 'react'

function UserDetailsForm() {

    const [otpSent, setOtpSent] = useState(false);
    const [otpVerified, setOtpVerified] = useState(false);
    const [otp, setOtp] = useState('');
    const [mobileNumber,setMobileNumber] = useState('');


    const handleSendOTP = () => {
        if(!mobileNumber){
            alert('Please enter a valid mobile number.');
            return;
        }
        alert('OTP will be sent soon..');
        setOtpSent(true);

    };

    
      const handleVerifyOTP = () => {
        if (verifyOTP(otp)) {
          setOtpVerified(true);
          alert('OTP Verified!');
        } else {
          alert('Invalid OTP!');
        }
      };
  return (
    <div>
        <form>
        
  
      
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

      <input id="Mobile Number" 
      type="text" 
      placeholder="Ex: 077 123 4567"
      value={MobileNumber}
      onChange={(e) => setMobileNumber(e.target.value)}

       />

      <button onClick={handleSendOTP}>
        Send OTP
      </button>

      {otpSent && (
        <>
        <label htmlFor="OTP" >OTP</label>
      <input id="OTP"
       type="text"
        placeholder="Enter OTP"
       value={OTP}
       onChange={(e) => setOTP(e.target.value)}
        />

<button  onClick={handleVerifyOTP}>
        Verify OTP
      </button>
        
        
        </>
      )}
    </form>
  </div>
  
  );
  

}


export default UserDetailsForm