
# Fuel Management System  
*A Next-Generation System to Optimize Fuel Distribution & Usage*


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
- [TeamMebers](#Team-Members)


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
   git clone https://lakshitha779988/Fuel-Management-System.git
   cd Fuel-Management-System/backend
   ```

2. **Configure Application Properties:**

   Update the `src/main/resources/application.properties` file with your database credentials, and any notification service API keys (Twilio, Mailgun).

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
  
   ```

3. **Run the Development Server:**

   ```bash
   npm run dev
 
   ```

   The web portal should be accessible on [http://localhost:3000](http://localhost:3000).

### Mobile Setup (React Native with Expo)

1. **Navigate to the Mobile Directory:**

   ```bash
   cd ../Mobile-App-Frontend
   ```

2. **Install Dependencies:**

   ```bash
   npm install
   
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
TWILIO_ACCOUNT_SID
TWILIO_AUTH_TOKEN
TWILIO_PHONE_NUMBER
TWILIO_API_URL

MAILGUN_API_KEY
MAILGUN_DOMAIN
MAILGUN_FROM

```

### Frontend (`frontend/.env`)
```ini
VITE_API_URL=http://localhost:8080/api
VITE_FIREBASE_API_KEY=your_firebase_api_key
```

### create firebase-service-account.json 
```
{
  "type": "accoutntype",
  "project_id": "****",
  "private_key_id": "your key",
  "private_key",
  "auth_uri": "your url",
  "token_uri": "token_url",
  "auth_provider_x509_cert_url": "***",
  "client_x509_cert_url": "***",
  "universe_domain": "***"
}


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



## Team Members

SE/2021/033 - Lakshitha Madushan
SE/2021/042 - Buddhika Madumali
SE/2021/025 - Induwara Mihisara
SE/2021/013 - JoJeeven
SE/2021/031 - Praveen Dadigama
SE/2021/062 - Thagya Kavindi
