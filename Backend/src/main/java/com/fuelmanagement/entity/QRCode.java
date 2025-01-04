package com.fuelmanagement.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;



    @Entity
public class QRCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long QRCodeId;
    @OneToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;
    private String QrData; // Encoded QR data (base64 or plain text)
    private LocalDateTime QrCreatedDate;

        public Long getQRCodeId() {
            return QRCodeId;
        }

        public void setQRCodeId(Long QRCodeId) {
            this.QRCodeId = QRCodeId;
        }

        public Vehicle getVehicle() {
            return vehicle;
        }

        public void setVehicle(Vehicle vehicle) {
            this.vehicle = vehicle;
        }

        public String getQrData() {
            return QrData;
        }

        public void setQrData(String qrData) {
            QrData = qrData;
        }

        public LocalDateTime getQrCreatedDate() {
            return QrCreatedDate;
        }

        public void setQrCreatedDate(LocalDateTime qrCreatedDate) {
            QrCreatedDate = qrCreatedDate;
        }
    }



