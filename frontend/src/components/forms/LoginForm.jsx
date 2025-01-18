import React from 'react'

function LoginForm() {
  return (
    <div>
      <form>
            <Input label="Mobile Number"
            type="text"
            placeholder="Enter your mobile number"/>
            <button>Sent OTP</button>
        </form>
    </div>
  )
}

export default LoginForm
