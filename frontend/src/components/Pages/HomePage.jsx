import React from 'react';
import { useNavigate } from 'react-router-dom';

export default function HomePage() {
  const navigate = useNavigate();

  return (
    <div className="h-screen flex items-center justify-center text-white relative overflow-hidden bg-gradient-to-br from-red-900 via-red-700 to-red-500"
    style={{
      backgroundImage: `url('../../public/fuel2.jpg')`,
      backgroundRepeat: 'no-repeat', 
      backgroundSize: 'cover',      
  
    }}>
     
      <div className="absolute inset-0">
        <div className="w-96 h-96 bg-red-300 opacity-40 rounded-full blur-3xl animate-bounce absolute -top-20 -left-20"></div>
        <div className="w-80 h-80 bg-red-400 opacity-30 rounded-full blur-2xl animate-spin-slow absolute top-1/3 left-1/3"></div>
        <div className="w-96 h-96 bg-red-300 opacity-50 rounded-full blur-3xl animate-pulse absolute bottom-20 right-20"></div>
      </div>

     
      <div className="bg-transparent bg-opacity-90 p-10 rounded-2xl text-center shadow-2xl relative z-10 backdrop-blur-md">
        <h1 className="text-6xl font-extrabold mb-8 text-yellow-300 drop-shadow-lg">
          Welcome to <span className="text-yellow-400">Fuel Management System</span>
        </h1>
        <p className="text-2xl mb-10 text-gray-200">
          Effortless fuel tracking and management for your convenience.
        </p>

        <div className="space-x-6">
          <button
            onClick={() => navigate('/login')}
            className="px-10 py-4 bg-red-500 hover:bg-red-600 text-white text-xl font-bold rounded-lg shadow-xl transition-transform transform hover:scale-110 hover:shadow-2xl duration-300"
          >
            Sign In
          </button>
          <button
            onClick={() => navigate('/register')}
            className="px-10 py-4 bg-red-600 hover:bg-red-700 text-white text-xl font-bold rounded-lg shadow-xl transition-transform transform hover:scale-110 hover:shadow-2xl duration-300"
          >
            Sign Up
          </button>
        </div>
      </div>

     
      <div className="absolute bottom-0 left-0 w-full h-20 bg-gradient-to-t from-red-900 to-transparent"></div>
    </div>
  );
}
