import React, {useEffect, useRef, useState} from "react";
import {Camera, CameraView} from "expo-camera";
import {Platform, SafeAreaView,StatusBar} from "react-native";
import {useRouter} from "expo-router";


export default function ScanScreen() {

    const [hasPermission, setHasPermission] = useState<boolean | null>(null);
    const [scanning,SetScanning] = useState(false);
    const router = useRouter();
    const qrLock = useRef(false);

    useEffect(() => {
        const requestPermission = async () => {
            const { status } = await Camera.requestCameraPermissionsAsync();
            setHasPermission(status === "granted");
        };
        requestPermission();
    }, []);






    return(

        <SafeAreaView style={StyleSheet.absoluteFillObject}>

            {Platform.OS === "android" ? <StatusBar hidden />:  null}

        <CameraView
            style={StyleSheet.absoluteFillObject}
            onBarcodeScanned = {scanning ? undefined : handleBarCodeScanned}
           />
        </SafeAreaView>
    );
}