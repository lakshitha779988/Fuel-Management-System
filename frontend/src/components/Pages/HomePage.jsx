import React from 'react';
import { useNavigate } from 'react-router-dom';

export default function HomePage() {
  const navigate = useNavigate();

  return (
    <div className="flex flex-col min-h-screen bg-gray-100">
      {/* Hero Section */}
      <section
        className="relative flex items-center justify-center h-screen bg-cover bg-center"
        style={{ backgroundImage: "url('/gas.jpg')" }}
      >
        <div className="absolute inset-0 bg-black opacity-50"></div>
        <div className="relative z-10 text-center px-4">
          <h1 className="text-5xl md:text-7xl font-bold text-white mb-6">
            LuxeFuel Management System
          </h1>
          <p className="text-xl md:text-2xl text-gray-200 mb-8">
            Experience a luxurious and efficient way to manage your fuel needs.
          </p>
          <div className="flex justify-center space-x-6">
            <button
              onClick={() => navigate('/login')}
              className="px-8 py-4 bg-yellow-500 hover:bg-yellow-600 text-white font-semibold rounded-lg shadow transition duration-300"
            >
              Sign In
            </button>
            <button
              onClick={() => navigate('/register')}
              className="px-8 py-4 bg-yellow-500 hover:bg-yellow-600 text-white font-semibold rounded-lg shadow transition duration-300"
            >
              Sign Up
            </button>
          </div>
        </div>
      </section>

      {/* Services Section */}
      <section className="py-20 bg-white">
        <div className="container mx-auto px-4">
          <h2 className="text-3xl font-bold text-center text-gray-800 mb-12">
            Our Services
          </h2>
          <div className="grid grid-cols-1 md:grid-cols-3 gap-8">
            {/* Card 1 */}
            <div className="bg-gray-50 rounded-lg shadow-lg p-6 hover:shadow-2xl transition duration-300">
              <h3 className="text-2xl font-semibold mb-4">User Portal</h3>
              <p className="text-gray-600">
                Register with your unique mobile number, receive your personalized QR code, and track your fuel usage effortlessly.
              </p>
            </div>
            {/* Card 2 */}
            <div className="bg-gray-50 rounded-lg shadow-lg p-6 hover:shadow-2xl transition duration-300">
              <h3 className="text-2xl font-semibold mb-4">Admin Dashboard</h3>
              <p className="text-gray-600">
                Manage fuel stations, update vehicle categories, and soon, view detailed reports to optimize fuel distribution.
              </p>
            </div>
            {/* Card 3 */}
            <div className="bg-gray-50 rounded-lg shadow-lg p-6 hover:shadow-2xl transition duration-300">
              <h3 className="text-2xl font-semibold mb-4">Fuel Station App</h3>
              <p className="text-gray-600">
                Scan QR codes to update fuel consumption, ensuring a seamless experience for fuel station operators.
              </p>
            </div>
          </div>
        </div>
      </section>

      {/* Features Section */}
      <section className="py-20 bg-gray-100">
        <div className="container mx-auto px-4">
          <h2 className="text-3xl font-bold text-center text-gray-800 mb-12">
            What You Can Do
          </h2>
          <div className="grid grid-cols-1 md:grid-cols-2 gap-8">
            <div className="flex items-center">
              <div className="flex-shrink-0 h-16 w-16 bg-yellow-500 rounded-full flex items-center justify-center">
                <svg className="h-8 w-8 text-white" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path strokeLinecap="round" strokeLinejoin="round" strokeWidth="2" d="M5 13l4 4L19 7" />
                </svg>
              </div>
              <div className="ml-4">
                <h3 className="text-xl font-semibold">Track Fuel Usage</h3>
                <p className="text-gray-600">
                  Monitor and manage your fuel consumption effortlessly.
                </p>
              </div>
            </div>
            <div className="flex items-center">
              <div className="flex-shrink-0 h-16 w-16 bg-yellow-500 rounded-full flex items-center justify-center">
                <svg className="h-8 w-8 text-white" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path strokeLinecap="round" strokeLinejoin="round" strokeWidth="2" d="M9 12l2 2l4-4" />
                </svg>
              </div>
              <div className="ml-4">
                <h3 className="text-xl font-semibold">Instant Notifications</h3>
                <p className="text-gray-600">
                  Receive SMS and email alerts for every important activity.
                </p>
              </div>
            </div>
            <div className="flex items-center">
              <div className="flex-shrink-0 h-16 w-16 bg-yellow-500 rounded-full flex items-center justify-center">
                <svg className="h-8 w-8 text-white" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path strokeLinecap="round" strokeLinejoin="round" strokeWidth="2" d="M12 8v4l3 3" />
                </svg>
              </div>
              <div className="ml-4">
                <h3 className="text-xl font-semibold">Effortless Login</h3>
                <p className="text-gray-600">
                  Sign in securely with your mobile number and access your dashboard with ease.
                </p>
              </div>
            </div>
            <div className="flex items-center">
              <div className="flex-shrink-0 h-16 w-16 bg-yellow-500 rounded-full flex items-center justify-center">
                <svg className="h-8 w-8 text-white" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path strokeLinecap="round" strokeLinejoin="round" strokeWidth="2" d="M13 16h-1v-4h-1m1-4h.01" />
                </svg>
              </div>
              <div className="ml-4">
                <h3 className="text-xl font-semibold">Complete Control</h3>
                <p className="text-gray-600">
                  Update your profile, regenerate your QR code, or even delete your account anytime.
                </p>
              </div>
            </div>
          </div>
        </div>
      </section>

      {/* Footer */}
      <footer className="bg-gray-800 text-gray-200 py-8">
        <div className="container mx-auto px-4">
          <div className="flex flex-col md:flex-row justify-between items-center">
            <div className="mb-4 md:mb-0">
              <h4 className="text-xl font-bold">LuxeFuel Management System</h4>
              <p className="text-gray-400">Excellence in Fuel Management.</p>
            </div>
            <div className="flex space-x-6">
              <a href="#" className="hover:text-white">Privacy Policy</a>
              <a href="#" className="hover:text-white">Terms of Service</a>
              <a href="#" className="hover:text-white">Contact Us</a>
            </div>
          </div>
          <div className="text-center mt-4 text-gray-500">
            © {new Date().getFullYear()} LuxeFuel Management System. All rights reserved.
          </div>
        </div>
      </footer>
    </div>
  );
}
