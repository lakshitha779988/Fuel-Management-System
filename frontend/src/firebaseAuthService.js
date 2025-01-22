import { signInWithPhoneNumber, RecaptchaVerifier } from 'firebase/auth';
import { auth } from './firebaseConfig';

const formatPhoneNumber = (number) => {
  
  return number.startsWith('0') 
    ? '+94' + number.slice(1) 
    : '+94' + number;
};

let appVerifier = null;

const initializeRecaptcha = () => {
  if (!appVerifier) {
    appVerifier = new RecaptchaVerifier(auth, 'sign-in-button', {
      size: 'invisible',
      callback: () => {
        console.log('reCAPTCHA verified');
      },
      'expired-callback': () => {
        console.log('reCAPTCHA expired');
      }
    });
  }
  return appVerifier;
};

export const sendOtp = async (mobileNumber) => {
  try {
    const formattedNumber = formatPhoneNumber(mobileNumber);
    const verifier = initializeRecaptcha();
    const confirmationResult = await signInWithPhoneNumber(auth, formattedNumber, verifier);
    return confirmationResult;
  } catch (error) {
    console.error('Error in sendOtp:', error);
    if (appVerifier) {
      appVerifier.clear();
      appVerifier = null;
    }
    throw new Error(
      error.code === 'auth/invalid-phone-number'
        ? 'Please enter a valid phone number'
        : 'Error sending OTP. Please try again.'
    );
  }
};

export const verifyOtp = async (confirmationResult, otp) => {
  try {
    const userCredential = await confirmationResult.confirm(otp);
    const user = userCredential.user;

    const idToken = await user.getIdToken();
    
    
    return sendTokenToBackend(user.phoneNumber, idToken);
  } catch (error) {
    console.error("Error verifying OTP: ", error);
    throw new Error("OTP verification failed. Please try again.");
  }
};

const sendTokenToBackend = async (mobileNumber, idToken) => {
  const cleanedMobileNumber = mobileNumber.replace(/^\+94/, '0');
  
  try {
    const response = await fetch("http://localhost:8080/api/auth/fuel-user/login", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        mobileNumber: cleanedMobileNumber,
        firebaseToken: idToken,
      }),
    });

    const contentType = response.headers.get("Content-Type");
    let data;

    if (contentType && contentType.includes("application/json")) {
      data = await response.json();  
    } else {
      data = await response.text();  
    }

    if (response.ok) {
       
      return data;  
    } else {
      console.error("Error: ", data);
      throw new Error(data);
    }
  } catch (error) {
    console.error("Failed to send token to backend: ", error);
    throw new Error("Login failed. Please try again.");
  }
};




