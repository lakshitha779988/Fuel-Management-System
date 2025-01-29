import {Alert} from "react-native";
import {useState} from "react";

export default function RegistrationScreen() {
    const [fuelStationName, setFuelStationName] = useState("");
    const [email, setEmail] = useState("");
    const [mobileNumber, setMobileNumber] = useState("");
    const [password, setPassword] = useState("");

    const handleRegister = async () => {

        if (!fuelStationName || !email || !mobileNumber || !password) {
            Alert.alert("Error", "Please fill out all fields.");
            return;
        }

    }
}