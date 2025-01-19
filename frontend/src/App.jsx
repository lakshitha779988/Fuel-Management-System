
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import UserDetailsForm from './components/forms/UserDetailsForm';

function App() {
  return (
    <Router>
    <>

    <h1 class="text-4xl font-bold text-center text-blue-500 mt-4">
    Welcome to fuel Management System 
  </h1>
  <Routes>
          <Route path="/UserDetails" element={<UserDetailsForm />} />
        </Routes>
      </>
    </Router>
  );

  
}

export default App;

