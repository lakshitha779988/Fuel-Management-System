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
    const token = await userCredential.user.getIdToken();
    return { 
      user: userCredential.user,
      token 
    };
  } catch (error) {
    console.error('Error in verifyOtp:', error);
    throw new Error(
      error.code === 'auth/invalid-verification-code'
        ? 'Invalid OTP. Please try again.'
        : 'Error verifying OTP. Please try again.'
    );
  }
};