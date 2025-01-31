import React from "react";
import { View, Text, Pressable } from "react-native";
import { useRouter } from "expo-router";

export default function DashboardScreen() {
    const router = useRouter();

    return (
        <View className="flex-1">
            {/* Top Section: 1/3 of the screen with orange background and welcome message */}
            <View className="h-1/3 bg-orange-600 justify-center items-center mb-12">
                <Text className="text-4xl font-bold text-white">Welcome</Text>
            </View>

            {/* Bottom Section: 2/3 of the screen with large card-like buttons */}
            <View className="h-2/3 bg-gray-100 py-6">
                <Pressable
                    className="bg-orange-600 py-6 mb-8 mx-6 rounded-xl shadow-2xl"
                    onPress={() => router.push("/scan")}
                >
                    <Text className="text-white text-center text-2xl font-bold">Scan</Text>
                </Pressable>

                <Pressable
                    className="bg-orange-600 py-6 mx-6 rounded-xl shadow-2xl"
                    onPress={() => router.push("/reports")}
                >
                    <Text className="text-white text-center text-2xl font-bold">Reports</Text>
                </Pressable>
            </View>
        </View>
    );
}