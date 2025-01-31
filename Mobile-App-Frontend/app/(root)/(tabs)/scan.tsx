import React, {useEffect, useRef, useState} from "react";
import {Camera, CameraView} from "expo-camera";
import {Alert, Platform, SafeAreaView, StatusBar,Text,StyleSheet} from "react-native";
import {useRouter} from "expo-router";
import axios from "axios";
import AsyncStorage from "@react-native-async-storage/async-storage";


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

  const handleBarCodeScanned = async ({data}:{data:string}) => {

      if(data && !qrLock.current) {

          qrLock.current = true;
          SetScanning(true);

          const token = await AsyncStorage.getItem("authToken");

          if (!token) {
              Alert.alert("Error", "No authentication token found. Please log in.");
              SetScanning(false);
              qrLock.current = false;
              return;

          }


          try {
              const response = await axios.post(
                  "http://172.19.67.1:8080/api/qr/check-qr-string",
                  {qrString: data},
                  {
                      headers: {
                          Authorization: `Bearer ${token}`,
                      },
                  }
              );

              if (response.status === 200) {
                  const {exsistingFuel, vehicleRegistrationNumber} = response.data;
                  const payload = {
                      qrString: data,
                      exsistingFuel,
                      vehicleRegistrationNumber,
                  };


                  console.log("Storing payload:", payload);


                  await AsyncStorage.setItem("scannedData", JSON.stringify(payload));

                  router.push("/UpdateScreen");
              } else {
                  Alert.alert("Error", "Invalid QR code.Please try again.");
              }
          } catch (error) {
              Alert.alert("Error", "Failed to validate QR code. Please try again.");
          } finally {
              SetScanning(false);
              qrLock.current = false;
          }
      }

  }

    if (hasPermission === null) {
        return <Text>Requesting camera permission...</Text>;
    }
    if (hasPermission === false) {
        return <Text>No access to camera</Text>;
    }



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