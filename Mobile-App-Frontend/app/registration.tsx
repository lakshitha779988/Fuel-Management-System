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

    return (
        <TouchableWithoutFeedback disabled={loading}>
            <View className="flex-1 bg-gray-100 px-6 pt-[40px]">
                <FirebaseRecaptchaVerifierModal
                    ref={recaptchaVerifier}
                    firebaseConfig={firebaseConfig}
                    attemptInvisibleVerification
                />

                {loading && (
                    <View className="absolute inset-0 bg-black bg-opacity-50 flex justify-center items-center z-50">
                        <ActivityIndicator size="large" color="#FF6600" />
                    </View>
                )}

                <Text className="text-5xl font-bold text-orange-600 text-center mb-12">
                    Register
                </Text>

                <TextInput
                    className="border border-gray-300 bg-white py-5 px-6 rounded-full text-lg mb-8"
                    placeholder="Fuel Station Name"
                    placeholderTextColor="#A9A9A9"
                    value={fuelStationName}
                    onChangeText={setFuelStationName}
                    editable={!loading} // Disable input when loading
                />

                <TextInput
                    className="border border-gray-300 bg-white py-5 px-6 rounded-full text-lg mb-8"
                    placeholder="Email"
                    placeholderTextColor="#A9A9A9"
                    keyboardType="email-address"
                    value={email}
                    onChangeText={setEmail}
                    editable={!loading} // Disable input when loading
                />

                <TextInput
                    className="border border-gray-300 bg-white py-5 px-6 rounded-full text-lg mb-8"
                    placeholder="Mobile Number"
                    placeholderTextColor="#A9A9A9"
                    keyboardType="phone-pad"
                    value={mobileNumber}
                    onChangeText={setMobileNumber}
                    editable={!loading} // Disable input when loading
                />

                <TextInput
                    className="border border-gray-300 bg-white py-5 px-6 rounded-full text-lg mb-12"
                    placeholder="Password"
                    placeholderTextColor="#A9A9A9"
                    secureTextEntry
                    value={password}
                    onChangeText={setPassword}
                    editable={!loading} // Disable input when loading
                />





                <Pressable
                    className={`bg-orange-600 py-4 rounded-full mb-10 ${loading ? "opacity-50" : ""}`}
                    onPress={handleRegister}
                    disabled={loading}
                >
                    <Text className="text-white text-xl font-bold text-center">
                        {loading ? "Sending OTP..." : "Register"}
                    </Text>
                </Pressable>

                <Link href="/sign-in" asChild>
                    <Pressable disabled={loading}>
                        <Text className={`text-black text-lg text-center ${loading ? "opacity-50" : ""}`}>
                            Go to Sign In
                        </Text>
                    </Pressable>
                </Link>
            </View>
        </TouchableWithoutFeedback>
    );
}
