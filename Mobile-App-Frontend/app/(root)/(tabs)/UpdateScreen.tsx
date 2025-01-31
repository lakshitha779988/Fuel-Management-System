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
