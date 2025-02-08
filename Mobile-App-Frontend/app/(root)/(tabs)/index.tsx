import React from "react";
import { View, Text, Pressable, ImageBackground, StyleSheet } from "react-native";
import { Link } from "expo-router";

export default function AuthScreen() {
    return (
        <ImageBackground
            source={require("../../../assets/images/background-2.jpeg")} // Add your background image to the assets folder
            style={styles.background}
            resizeMode="cover"
        >
            <View style={styles.overlay}>
                //title
                <Text className="font-bold text-white text-5xl mb-4 text-center mt-32 leading-[80px]">
                    FuelQRCode Scanner
                </Text>
                // Sign In Button

                <Link href="/sign-in" asChild>
                    <Pressable style={styles.button} android_ripple={{ color: "#ddd" }}>
                        <Text style={styles.buttonText}>Sign In</Text>
                    </Pressable>
                </Link>

                // Register Button
                <Link href="/registration" asChild>
                    <Pressable style={styles.button} android_ripple={{ color: "#ddd" }}>
                        <Text style={styles.buttonText}>Registration</Text>
                    </Pressable>
                </Link>


            </View>
        </ImageBackground>
    );
}

const styles = StyleSheet.create({
    background: {
        flex: 1,
    },
    overlay: {
        flex: 1,
        backgroundColor: "rgba(0, 0, 0, 0.5)",
        paddingHorizontal: 24,
        justifyContent: "center",
        alignItems: "center",
    },
    button: {
        backgroundColor: "white",
        paddingVertical: 16,
        paddingHorizontal: 32,
        borderRadius: 50,
        marginBottom: 16,
        alignItems: "center",
        justifyContent: "center",
        elevation: 3,
        width: 200,
    },
    buttonText: {
        fontSize: 20,
        fontWeight: "bold",
        color: "black",
    },
    registerButton: {
        backgroundColor: "#f97316",
    },
    registerText: {
        color: "white",
    },
});
