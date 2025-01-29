import React, { useState, useRef } from "react";
import { View, Text, TextInput, Pressable, Alert, ActivityIndicator, TouchableWithoutFeedback } from "react-native";
import { Link, useRouter } from "expo-router";
import { FirebaseRecaptchaVerifierModal } from "expo-firebase-recaptcha";
import { auth, phoneProvider, firebaseConfig } from "./firebaseConfig";

export default function RegistrationScreen() {
    const router = useRouter();
    const [fuelStationName, setFuelStationName] = useState("");
    const [email, setEmail] = useState("");
    const [mobileNumber, setMobileNumber] = useState("");
    const [password, setPassword] = useState("");
    const [loading, setLoading] = useState(false);
    const recaptchaVerifier = useRef(null);


    const handleRegister = async () => {

        if (!fuelStationName || !email || !mobileNumber || !password) {
            Alert.alert("Error", "Please fill out all fields.");
            return;
        }
        setLoading(true);
        try {
            const verificationId = await phoneProvider.verifyPhoneNumber(
                mobileNumber, // Ensure mobileNumber includes country code (e.g., +94...)
                recaptchaVerifier.current!
            );

            router.push({
                pathname: "/otp-verification",
                params: {
                    verificationId,
                    fuelStationName,
                    email,
                    mobileNumber,
                    password,
                },
            });
        } catch (error) {
            Alert.alert("Error", "Failed to send OTP. Please check your number.");
            console.error("OTP Error:", error);
        } finally {
            setLoading(false);
        }
    };


}
