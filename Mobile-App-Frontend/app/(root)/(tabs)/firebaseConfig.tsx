import { initializeApp } from "firebase/app";
import { getAuth, PhoneAuthProvider } from "firebase/auth";

export const firebaseConfig = {
    apiKey: "AIzaSyB7UoXoeYUBCUiFZu_WT8UoC_wviVJWuPk",
    authDomain: "fms-mobile-otp-part.firebaseapp.com",
    projectId: "fms-mobile-otp-part",
    storageBucket: "fms-mobile-otp-part.appspot.com",
    messagingSenderId: "308452616804",
    appId: "1:308452616804:web:1cc4a53483e5a0d89ccd71",
    measurementId: "G-GNMP4MFZDC"
};

const app = initializeApp(firebaseConfig);
export const auth = getAuth(app);
export const phoneProvider = new PhoneAuthProvider(auth);