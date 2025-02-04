import React from "react";
import { Stack } from "expo-router";
import "./global.css";


export default function Layout() {
  return (
      <Stack screenOptions={{ headerShown: false }}>
        {/* Your screens go here */}
      </Stack>
  );
}
