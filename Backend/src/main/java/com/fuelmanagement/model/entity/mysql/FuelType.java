package com.fuelmanagement.model.entity.mysql;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class FuelType {
    public static Long Id;

    @Entity
    public class fuelType {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String name;
        private String description;
        private BigDecimal price;

        @CreationTimestamp
        private LocalDateTime createdAt;

        // One FuelType can be used by many Vehicles
        @OneToMany(mappedBy = "fuelType", cascade = CascadeType.ALL)
        private List<Vehicle> vehicles;

        // Many FuelTypes can belong to a FuelStation
        @ManyToOne
        @JoinColumn(name = "fuel_station_id")
        private FuelStation fuelStation;

        // Getters and Setters


        public List<Vehicle> getVehicles() {return vehicles;}

        public void setVehicles(List<Vehicle> vehicles) {this.vehicles = vehicles;}

        public FuelStation getFuelStation() {return fuelStation;}

        public void setFuelStation(FuelStation fuelStation) {this.fuelStation = fuelStation;}

        public Long getId() {return id;}

        public void setId(Long id) {this.id = id;}

        public String getName() {return name;}

        public void setName(String name) {this.name = name;}

        public String getDescription() {return description;}

        public void setDescription(String description) {this.description = description;}

        public BigDecimal getPrice() {return price;}

        public void setPrice(BigDecimal price) {this.price = price;}

        public LocalDateTime getCreatedAt() {return createdAt;}

        public void setCreatedAt(LocalDateTime createdAt) {this.createdAt = createdAt;}
    }

}
