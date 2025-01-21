import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import LoginForm from './components/forms/LoginForm';
import UserDetailsForm from './components/forms/UserDetailsForm';

function App() {
  return (
    <Router>
      <>
        <h1 className="text-4xl font-bold text-center text-blue-500 mt-4">
          Welcome to Fuel Management System
        </h1>
        <Routes>
          <Route path="/login" element={<LoginForm />} />
          <Route path="/UserDetails" element={<UserDetailsForm />} />
        </Routes>
      </>
    </Router>
  );
}

export default App;
