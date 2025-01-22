import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import LoginForm from './components/forms/LoginForm';
import UserDetailsForm from './components/forms/UserDetailsForm';
import VehicleDetailsForm from './components/forms/VehicleDetailsForm';
import Dashboard from './components/Pages/Dashboard';



function App() {
  return (
    <Router>
      <>
        <h1 className="text-4xl font-bold text-center text-red-500 mt-4">
          Welcome to Fuel Management System
        </h1>
        <Routes>
          <Route path="/login" element={<LoginForm />} />
          <Route path="/UserDetails" element={<UserDetailsForm />} />

          <Route path="/dashboard" element={<Dashboard/>} />
        
          <Route path="/VehicleDetails" element={<VehicleDetailsForm />} />

          
        </Routes>
      </>
    </Router>
  );
}

export default App;
