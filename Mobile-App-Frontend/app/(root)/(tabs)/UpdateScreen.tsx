import { View, Text } from "react-native";

export default function UpdateScreen() {
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

