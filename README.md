
# Fuel Management System  
*A Next-Generation System to Optimize Fuel Distribution & Usage*

[![Build Status](https://img.shields.io/badge/build-passing-brightgreen)](https://github.com/yourusername/yourrepo) 
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)
[![Spring Boot](https://img.shields.io/badge/SpringBoot-2.x-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![React](https://img.shields.io/badge/React-Vite-blue)](https://react.dev/)
[![React Native](https://img.shields.io/badge/ReactNative-Expo-blueviolet)](https://expo.dev/)

---

## Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Architecture & Design Patterns](#architecture--design-patterns)
- [Technology Stack](#technology-stack)
- [Setup & Installation](#setup--installation)
  - [Backend Setup (Spring Boot)](#backend-setup-spring-boot)
  - [Frontend Setup (React Vite)](#frontend-setup-react-vite)
  - [Mobile Setup (React Native with Expo)](#mobile-setup-react-native-with-expo)
- [Environment Variables](#environment-variables)
- [Usage](#usage)
- [Future Enhancements](#future-enhancements)
- [Contributing](#contributing)
- [License](#license)
- [Contact](#contact)

---

## Overview

The **Fuel Management System** is designed to help manage limited fuel resources efficiently across the country. The project addresses the growing demand for transparent and effective fuel allocation by:

- **Empowering Citizens**: Users can register, verify their mobile numbers via OTP (using Firebase Phone Authentication on the frontend), and receive a personalized QR code.
- **Admin Management**: Administrators can activate or block fuel stations, add vehicle categories, and in future updates, access comprehensive reports.
- **Fuel Station Operations**: Fuel station personnel use a dedicated mobile app to scan user QR codes, verify details, and update fuel consumption records.

The system ensures that each user has a unique mobile number that acts as a primary identifier across all interactions. Users are notified of key events (login, QR code changes, fuel updates) via both SMS (through Twilio) and email (via Mailgun).

---

## Features

### User Web Portal
- **Registration & Login**: Users register with personal and vehicle details using their unique mobile number. OTP verification ensures secure access.
- **QR Code Generation & Management**: On successful login, a unique QR code is generated. Users can download, update, or delete their QR code at any time.
- **Notifications**: Every login and action (QR code change, fuel update) triggers notifications via SMS and email.

### Admin Web Portal
- **Fuel Station Management**: Administrators can activate or block fuel stations.
- **Vehicle Category Management**: Add new vehicle categories to the system.
- **Reporting**: (Coming Soon) Enhanced reporting capabilities to monitor fuel distribution and consumption trends.

### Fuel Station Mobile App
- **Secure Login**: Fuel station users log in with a mobile number and password.
- **QR Code Scanning**: Verify user QR codes and access an update screen for recording fuel usage.
- **Real-time Updates**: Fuel consumption data is immediately updated in the backend.

---

## Architecture & Design Patterns

- **Backend API**: Built using Spring Boot, the backend uses a single API endpoint for login and registration, orchestrated via the **Factory Design Pattern**.
- **Notification System**: A modular notification manager leverages the **Factory Design Pattern** to integrate multiple notification services (SMS via Twilio and Email via Mailgun). New notification methods can be easily added.
- **Observer Pattern**: Employed during QR code scanning, ensuring that when a QR scan event occurs, multiple subscribers are notified to update records and trigger notifications.
- **Data Management**: 
  - **MySQL**: Primary database for user and fuel station data.
  - **H2 Database**: Used as a mock database for validating vehicle information.
- **Security**: 
  - **JWT Authentication**: Secures API endpoints.
  - *Future Improvement*: Tokens currently stored in `localStorage` will be moved to cookies in upcoming updates for enhanced security.

---

## Technology Stack

- **Backend**:  
  - Spring Boot  
  - JWT Authentication  
  - MySQL & H2 Databases

- **Frontend Web**:  
  - React (Vite)  
  - Firebase Phone Authentication

- **Mobile Application**:  
  - React Native with Expo

- **Notifications**:  
  - SMS: [Twilio](https://www.twilio.com/)  
  - Email: [Mailgun](https://www.mailgun.com/)

---

## Setup & Installation

### Prerequisites

- **Java 11+** and **Maven** (for the Spring Boot backend)
- **Node.js** (v14+ recommended) and **npm**/`yarn` (for React and React Native)
- **Expo CLI** (for the mobile app)
- A MySQL database instance (or Docker container)
- (Optional) An IDE like IntelliJ IDEA, VS Code, or Android Studio

### Backend Setup (Spring Boot)

1. **Clone the Repository:**

   ```bash
   git clone https://github.com/yourusername/yourrepo.git
   cd yourrepo/backend
   ```

2. **Configure Application Properties:**

   Update the `src/main/resources/application.properties` file with your database credentials, JWT secret, and any notification service API keys (Twilio, Mailgun).

3. **Build and Run:**

   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

   The backend should now be running on [http://localhost:8080](http://localhost:8080).

### Frontend Setup (React Vite)

1. **Navigate to the Frontend Directory:**

   ```bash
   cd ../frontend
   ```

2. **Install Dependencies:**

   ```bash
   npm install
   # or if you use yarn
   yarn install
   ```

3. **Run the Development Server:**

   ```bash
   npm run dev
   # or
   yarn dev
   ```

   The web portal should be accessible on [http://localhost:3000](http://localhost:3000).

### Mobile Setup (React Native with Expo)

1. **Navigate to the Mobile Directory:**

   ```bash
   cd ../mobile
   ```

2. **Install Dependencies:**

   ```bash
   npm install
   # or
   yarn install
   ```

3. **Start the Expo Development Server:**

   ```bash
   expo start
   ```

   Use the Expo Go app on your device or an emulator to run the mobile application.

---

## Environment Variables

Ensure you create a `.env` file (or use your preferred secret management tool) in each project directory with the following keys (update with your values):

### Backend (`backend/.env`)
```ini
# Database
DB_URL=jdbc:mysql://localhost:3306/fuel_management
DB_USERNAME=your_db_username
DB_PASSWORD=your_db_password

# JWT
JWT_SECRET=your_jwt_secret

# Notification Services
TWILIO_API_KEY=your_twilio_api_key
MAILGUN_API_KEY=your_mailgun_api_key
```

### Frontend (`frontend/.env`)
```ini
VITE_API_URL=http://localhost:8080/api
VITE_FIREBASE_API_KEY=your_firebase_api_key
```

### Mobile (`mobile/.env`)
```ini
EXPO_API_URL=http://localhost:8080/api
```

---

## Usage

- **User Registration & Login:**  
  Visit the web portal, register using your mobile number (OTP verification via Firebase), and receive your unique QR code.

- **Admin Management:**  
  Log in to the admin portal to manage fuel stations and vehicle categories.

- **Fuel Station Operations:**  
  Use the mobile app to scan user QR codes and update fuel usage. The system will automatically trigger notifications and update records.

---

## Future Enhancements

- **Enhanced Reporting:**  
  Admins will soon have access to detailed reports on fuel usage and distribution trends.
  
- **Improved Token Storage:**  
  Transition from `localStorage` to cookies for storing JWT tokens to increase security.

- **Additional Notification Channels:**  
  Integration of further notification methods (e.g., push notifications).

- **Extended Observer Subscriptions:**  
  More modular event subscribers can be added for further system integrations.

---

## Contributing

Contributions are welcome! Please follow these steps:

1. **Fork the Repository**
2. **Create a Feature Branch:**

   ```bash
   git checkout -b feature/YourFeatureName
   ```

3. **Commit Your Changes:**

   ```bash
   git commit -m "Add: description of feature"
   ```

4. **Push to the Branch:**

   ```bash
   git push origin feature/YourFeatureName
   ```

5. **Create a Pull Request** outlining your changes.

For major changes, please open an issue first to discuss what you would like to change.

---

## License

This project is licensed under the [MIT License](LICENSE).

---

## Contact

**Project Maintainer:**  
[Your Name](mailto:your.email@example.com)

Feel free to open issues or contact me directly for any questions or further discussion.
