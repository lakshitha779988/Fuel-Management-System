import { initializeApp } from "firebase/app";
import { getAuth } from "firebase/auth";
import { getAnalytics } from "firebase/analytics";


const firebaseConfig = {
  apiKey: "AIzaSyB0fBYXn5jmNVXVCHAfOCeavBkio_cu5RM",
  authDomain: "fuel-managment-system.firebaseapp.com",
  projectId: "fuel-managment-system",
  storageBucket: "fuel-managment-system.appspot.com",
  messagingSenderId: "78414497951",
  appId: "1:78414497951:web:53428f777bcb043818baaa",
  measurementId: "G-DEBFCMRFDC",
};


const app = initializeApp(firebaseConfig);
const auth = getAuth(app);
const analytics = getAnalytics(app);


export { app, auth, analytics };
                                