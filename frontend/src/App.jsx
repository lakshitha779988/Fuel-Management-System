import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';

import UserDetailsForm from './components/forms/UserDetailsForm';
import VehicleDetailsForm from './components/forms/VehicleDetailsForm';

import RegistrationPage from './components/Pages/RegistrationPage';


import Dashboard from './components/Pages/Dashboard';
import LoginPage from './components/Pages/LoginPage';
import HomePage from './components/Pages/HomePage';


function App() {
  return (
    <Router>
      <>
        <h1 className="text-4xl font-bold text-center text-red-500 mt-4">
          
        </h1>
        <Routes>
          <Route path="/login" element={<LoginPage />} />
          <Route path="/UserDetails" element={<UserDetailsForm />} />

          <Route path="/dashboard" element={<Dashboard/>} />
        
          <Route path="/VehicleDetails" element={<VehicleDetailsForm />} />


          <Route path="/" element={<HomePage />} />
                
                <Route path="/register" element={<RegistrationPage />} />
                

          
        </Routes>
        
      </>
    </Router>
  );

 
}

export default App;
