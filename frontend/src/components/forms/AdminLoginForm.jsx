import React from 'react';
import { useState } from 'react'; 
import { useNavigate } from 'react-router-dom';

function AdminLoginForm() {
    const[username , setUsername]= useState ('');
    const[password , setPassword]= useState('');
    const navigate = useNavigate();

    function handleLogin(e){
        e.preventDefault();

        if(username === 'admin' && password === '123'){
            navigate('/admin');
        }
        else{
            return('Invalid username or password');
        }


    }


  return (
    <div>
      <form onSubmit={handleLogin}>
        <h2>LOGIN</h2>
        <input type="text" placeholder="Enter Username" value={username} onChange={(e)=>setUsername(e.target.value)} />
        <input type="password" placeholder="Enter Password" value={password} onChange={(e)=>setPassword(e.target.value)} />
        <button type="submit">Log in</button>
      </form>
    </div>
  )
}

export default AdminLoginForm;
