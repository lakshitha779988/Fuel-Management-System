import { Pressable, Text, View } from "react-native";
import { Link } from "expo-router";
import React from "react";

export default function Index() {
    return (
        <View className="flex-1 bg-orange-600 px-6 pt-8">
            {/* Title */}
            <Text className="font-bold text-white text-5xl mb-4 text-center mt-32 leading-[80px]">
                FuelQRCode Scanner
            </Text>

            <View className="flex-1" />

            {/* Sign In Button */}
            <Link href="/sign-in" asChild>
                <Pressable className="bg-white py-6 rounded-full mb-6 w-full" android_ripple={{ color: "#000" }}>
                    <Text className="text-black font-bold text-2xl text-center">Sign In</Text>
                </Pressable>
            </Link>

            {/* Register Button */}
            <Link href="/registration" asChild>
                <Pressable className="bg-white py-6 rounded-full mb-40 w-full" android_ripple={{ color: "#000" }}>
                    <Text className="text-black font-bold text-2xl text-center">Register</Text>
                </Pressable>
            </Link>
        </View>
    );
}
