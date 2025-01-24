import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';

import UserDetailsForm from './components/forms/UserDetailsForm';
import VehicleDetailsForm from './components/forms/VehicleDetailsForm';

import RegistrationPage from './components/Pages/RegistrationPage';


import Dashboard from './components/Pages/Dashboard';
import AdminDashboard from './components/Pages/AdminDashboard';
import AdminLoginForm from './components/forms/AdminLoginForm';



import LoginPage from './components/Pages/LoginPage';
import HomePage from './components/Pages/HomePage';


function App() {
  return (
    <Router>
      <>

        <Routes>
          <Route path="/login" element={<LoginPage />} />
          <Route path="/UserDetails" element={<UserDetailsForm />} />

          <Route path="/dashboard" element={<Dashboard/>} />
        
          <Route path="/VehicleDetails" element={<VehicleDetailsForm />} />


          <Route path="/admin" element={<AdminDashboard/>} />

          <Route path="/register" element={<UserDetailsForm />} />
          <Route path="/adminlogin" element={<AdminLoginForm />} />



          <Route path="/" element={<HomePage />} />
                
                <Route path="/register" element={<RegistrationPage />} />
                

          
        </Routes>
        
      </>
    </Router>
  );

 
}

export default App;
