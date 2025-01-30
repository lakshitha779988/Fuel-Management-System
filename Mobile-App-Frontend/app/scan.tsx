import React from "react";
import {Camera, CameraView} from "expo-camera";
import {Platform, SafeAreaView,StatusBar} from "react-native";


export default function ScanScreen() {



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