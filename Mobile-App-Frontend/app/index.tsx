import React, { useState } from "react";
import { View, Text, TextInput, Pressable, ActivityIndicator, TouchableWithoutFeedback } from "react-native";
import { Link} from "expo-router";

export default function SignInScreen() {
    const [mobileNumber, setMobileNumber] = useState("");
    const [password, setPassword] = useState("");
    const [loading, setLoading] = useState(false);




    return (
        <TouchableWithoutFeedback disabled={loading}>
            <View className="flex-1 bg-gray-100 px-6 pt-[40px]">
                {/* Show loading indicator when loading */}
                {loading && (
                    <View className="absolute inset-0 bg-black bg-opacity-50 flex justify-center items-center z-50">
                        <ActivityIndicator size="large" color="#FF6600" />
                    </View>
                )}

                {/* Title */}
                <Text className="text-5xl font-bold text-orange-600 text-center mb-12">
                    Sign In
                </Text>

                {/* Mobile Number Input */}
                <TextInput
                    className="border border-gray-300 bg-white py-5 px-6 rounded-full text-lg mb-8"
                    placeholder="Enter Mobile Number"
                    keyboardType="phone-pad"
                    value={mobileNumber}
                    onChangeText={setMobileNumber}
                    editable={!loading} // Disable input when loading
                />

                {/* Password Input */}
                <TextInput
                    className="border border-gray-300 bg-white py-5 px-6 rounded-full text-lg mb-12"
                    placeholder="Enter Password"
                    secureTextEntry
                    value={password}
                    onChangeText={setPassword}
                    editable={!loading} // Disable input when loading
                />

                {/* Sign In Button */}
                <Pressable
                    className={`bg-orange-600 py-4 rounded-full mb-10 ${loading ? "opacity-50" : ""}`}
                    disabled={loading} // Disable button when loading
                >
                    <Text className="text-white text-xl font-bold text-center">
                        {loading ? "Loading..." : "Sign In"}
                    </Text>
                </Pressable>

                {/* Go to Registration Link */}
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