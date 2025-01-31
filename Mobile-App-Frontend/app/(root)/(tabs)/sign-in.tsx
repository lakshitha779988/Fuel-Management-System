import React, { useState } from "react";
import { View, Text, TextInput, Pressable, Alert, ActivityIndicator, TouchableWithoutFeedback } from "react-native";
import AsyncStorage from "@react-native-async-storage/async-storage";
import axios from "axios";
import { Link, useRouter } from "expo-router";

export default function SignInScreen() {
    const router = useRouter();
    const [mobileNumber, setMobileNumber] = useState("");
    const [password, setPassword] = useState("");
    const [loading, setLoading] = useState(false); // State to control the loader

    const handleSignIn = async () => {
        if (!mobileNumber || !password) {
            Alert.alert("Error", "Please fill out all fields.");
            return;
        }

        setLoading(true);
        try {
            const response = await axios.post("http://172.19.67.1:8080/api/fuel-stations/login", {
                mobileNumber,
                password,
            });

            if (response.status === 200) {
                const { token } = response.data;


                await AsyncStorage.setItem("authToken", token);


                Alert.alert("Success", "Login successful!", [
                    { text: "OK", onPress: () => router.push("/dashboard") },
                ]);
            }
        } catch (error) {
            // @ts-ignore
            if (error.response && error.response.data) {
                // @ts-ignore
                Alert.alert("Error", error.response.data.message || "Login failed. Please try again.");
            } else {
                Alert.alert("Error", "Login failed. Please check your connection or credentials.");
            }
            console.error("Error during login:", error);
        } finally {
            setLoading(false);
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
                    Sign In
                </Text>


                <TextInput
                    className="border border-gray-300 bg-white py-5 px-6 rounded-full text-lg mb-8"
                    placeholder="Enter Mobile Number"
                    keyboardType="phone-pad"
                    value={mobileNumber}
                    onChangeText={setMobileNumber}
                    editable={!loading}
                />


                <TextInput
                    className="border border-gray-300 bg-white py-5 px-6 rounded-full text-lg mb-12"
                    placeholder="Enter Password"
                    secureTextEntry
                    value={password}
                    onChangeText={setPassword}
                    editable={!loading}
                />

                <Pressable
                    className={`bg-orange-600 py-4 rounded-full mb-10 ${loading ? "opacity-50" : ""}`}
                    onPress={handleSignIn}
                    disabled={loading}
                >
                    <Text className="text-white text-xl font-bold text-center">
                        {loading ? "Loading..." : "Sign In"}
                    </Text>
                </Pressable>


                <Link href="/registration" asChild>
                    <Pressable disabled={loading}>
                        <Text className={`text-black text-lg text-center ${loading ? "opacity-50" : ""}`}>
                            Go to Register
                        </Text>
                    </Pressable>
                </Link>
            </View>
        </TouchableWithoutFeedback>
    );
}
