import React, { useState } from "react";
import { View, Text, TextInput, Pressable, Alert, ActivityIndicator, TouchableWithoutFeedback } from "react-native";
import { useRouter, useLocalSearchParams } from "expo-router";
import { PhoneAuthProvider, signInWithCredential } from "firebase/auth";
import axios from "axios";
import { auth } from "./firebaseConfig";

export default function OtpVerificationScreen() {
    const router = useRouter();
    const params = useLocalSearchParams();
    const [otp, setOtp] = useState("");
    const [loading, setLoading] = useState(false);

    const {
        verificationId,
        fuelStationName,
        email,
        mobileNumber,
        password,
    } = params as Record<string, string>;

    const handleVerifyOTP = async () => {
        if (!otp || otp.length !== 6) {
            Alert.alert("Error", "Please enter a valid 6-digit OTP");
            return;
        }

        setLoading(true);
        try {

            const credential = PhoneAuthProvider.credential(verificationId, otp);
            await signInWithCredential(auth, credential);


            const response = await axios.post("http://172.19.67.1:8080/api/fuel-stations/register", {
                email,
                mobileNumber,
                name: fuelStationName,
                password,
            });

            console.log(verificationId);

            if (response.status === 200) {
                Alert.alert("Success", "Registration successful!", [
                    { text: "OK", onPress: () => router.push("/sign-in") }
                ]);
            }
        } catch (error) {
            let errorMessage = "Registration failed. Please try again.";
            if (axios.isAxiosError(error)) {
                errorMessage = error.response?.data?.message || errorMessage;
            } else if (error instanceof Error) {
                errorMessage = error.message;
            }
            Alert.alert("Error", errorMessage);
        } finally {
            setLoading(false);
            await auth.signOut(); // Clear Firebase session
        }
    };

    return (
        <TouchableWithoutFeedback disabled={loading}>
            <View className="flex-1 bg-gray-100 px-6 pt-[40px]">
                {loading && (
                    <View className="absolute inset-0 bg-black bg-opacity-50 flex justify-center items-center z-50">
                        <ActivityIndicator size="large" color="#FF6600" />
                    </View>
                )}

                <Text className="text-5xl font-bold text-orange-600 text-center mb-12">
                    Verify OTP
                </Text>

                <TextInput
                    className="border border-gray-300 bg-white py-5 px-6 rounded-full text-lg mb-12"
                    placeholder="Enter 6-digit OTP"
                    placeholderTextColor="#A9A9A9"
                    keyboardType="number-pad"
                    maxLength={6}
                    value={otp}
                    onChangeText={setOtp}
                    editable={!loading}
                />

                <Pressable
                    className={`bg-orange-600 py-4 rounded-full mb-10 ${loading ? "opacity-50" : ""}`}
                    onPress={handleVerifyOTP}
                    disabled={loading}
                >
                    <Text className="text-white text-xl font-bold text-center">
                        {loading ? "Verifying..." : "Complete Registration"}
                    </Text>
                </Pressable>
            </View>
        </TouchableWithoutFeedback>
    );
}