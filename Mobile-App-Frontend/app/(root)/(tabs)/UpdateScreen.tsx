import { useEffect, useState } from "react";
import { SafeAreaView, Text, TextInput, Alert, Pressable, StyleSheet, View, TouchableWithoutFeedback, Keyboard } from "react-native";
import axios from "axios";
import AsyncStorage from "@react-native-async-storage/async-storage";
import { useRouter } from "expo-router";

export default function UpdateScreen() {
    const [data, setData] = useState<any>(null);
    const [newFuelLimit, setNewFuelLimit] = useState<string>("");
    const [loading, setLoading] = useState(false);
    const router = useRouter();

    useEffect(() => {
        const loadData = async () => {
            const storedData = await AsyncStorage.getItem("scannedData");
            if (storedData) {
                const parsedData = JSON.parse(storedData);
                setData(parsedData);
                setNewFuelLimit(parsedData.exsistingFuel.toString());
            } else {
                Alert.alert("Error", "No data found. Redirecting to dashboard.");
                router.push("/");
            }
        };

        loadData();
    }, []);

    const handleUpdate = async () => {
        setLoading(true);
        const token = await AsyncStorage.getItem("authToken");

        if (!token) {
            Alert.alert("Error", "No authentication token found. Please log in.");
            setLoading(false);
            return;
        }

        const usage = parseFloat(newFuelLimit);

        if (isNaN(usage)) {
            Alert.alert("Error", "Invalid fuel limit value. Please enter a valid number.");
            setLoading(false);
            return;
        }

        try {
            const response = await axios.put(
                "http://172.19.67.1:8080/api/fuel-quota/update-limit",
                null,
                {
                    headers: {
                        Authorization: `Bearer ${token}`,
                    },
                    params: {
                        qrString: data.qrString,
                        usage,
                    },
                }
            );

            if (response.status === 200) {
                Alert.alert("Success", "Fuel limit updated successfully.");
            }
        } catch (error) {
            Alert.alert("Error", "Failed to update fuel limit. Please try again.");
        } finally {
            setLoading(false);

            await AsyncStorage.removeItem("scannedData");
            router.push("/dashboard");
        }
    };

    const handleCancel = async () => {
        await AsyncStorage.removeItem("scannedData");
        router.push("/dashboard");
    };

    if (!data) {
        return null;
    }

    return (
        <TouchableWithoutFeedback onPress={() => Keyboard.dismiss()}>
            <SafeAreaView style={styles.container}>
                <Text style={styles.title}>Update Fuel Limit</Text>
                <View style={styles.detailsContainer}>
                    <Text style={styles.vehicleDetail}>Vehicle Registration Number: {data.vehicleRegistrationNumber}</Text>
                    <Text style={styles.vehicleDetail}>Existing Fuel Limit: {data.exsistingFuel}</Text>
                </View>
                <TextInput
                    style={styles.input}
                    value={newFuelLimit}
                    keyboardType="numeric"
                    placeholder="Enter new fuel limit"
                    onChangeText={(text) => setNewFuelLimit(text)}
                />
                <Pressable
                    style={styles.updateButton}
                    onPress={handleUpdate}
                    disabled={loading}
                >
                    <Text style={styles.buttonText}>Update Fuel Limit</Text>
                </Pressable>
                <Pressable
                    style={styles.cancelButton}
                    onPress={handleCancel}
                >
                    <Text style={styles.buttonText}>Cancel</Text>
                </Pressable>
            </SafeAreaView>
        </TouchableWithoutFeedback>
    );
}

const styles = StyleSheet.create({
    container: {
        flex: 1,
        justifyContent: "center",
        paddingHorizontal: 20,
        backgroundColor: "#f3f3f3",
    },
    title: {
        textAlign: "center",
        fontSize: 55,
        fontWeight: "bold",
        color: "#FF6600",
        marginBottom: 40,
    },
    detailsContainer: {
        marginBottom: 30,
        alignItems: "center",
    },
    vehicleDetail: {
        fontSize: 18,
        fontWeight: "600",
        textAlign: "center",
        color: "#333",
        marginBottom: 10,
    },
    input: {
        borderWidth: 1,
        borderColor: "#ccc",
        backgroundColor: "#fff",
        paddingVertical: 12,
        paddingHorizontal: 20,
        borderRadius: 50,
        fontSize: 18,
        marginBottom: 30,
    },
    updateButton: {
        backgroundColor: "#FF6600",
        paddingVertical: 15,
        borderRadius: 50,
        marginBottom: 20,
        marginHorizontal: 20,
    },
    cancelButton: {
        backgroundColor: "#E53E3E",
        paddingVertical: 15,
        borderRadius: 50,
        marginHorizontal: 20,
    },
    buttonText: {
        textAlign: "center",
        color: "#fff",
        fontSize: 18,
        fontWeight: "bold",
    },
});
