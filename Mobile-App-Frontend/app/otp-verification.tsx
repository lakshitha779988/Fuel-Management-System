import {ActivityIndicator, TouchableWithoutFeedback, View, Text, TextInput, Pressable} from "react-native";
import {useState} from "react";
import React from "react";


export default function OtpVerificationScreen(){
    const [loading, setLoading] = useState(false);
    const[otp, setOtp] = useState("");


return (
    <TouchableWithoutFeedback disabled={loading}>

    <View className="flex-1 bg-gray-100 px-6 pt-[40px]">

        {loading && (

            <View className="absolute inset-0 bg-black bg-opacity-50 flex justify-center items-center z-50">

                <ActivityIndicator size="large" color="#FF6600"/>

            </View>
        )}



    <Text className="text-5xl font-bold teext-orange-600 text-center mb-12">
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
        className={`bg-orange-600 py-4 rounded-full mb-10 ${loading ? "opacity-50 " : ""}`}

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





