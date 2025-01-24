package com.fuelmanagement.controller;


import com.fuelmanagement.repository.mysql.FuelLogRepository;
import com.fuelmanagement.repository.mysql.VehicleTypeRepository;
import com.fuelmanagement.service.ReportService;

public class ReportController {

    ReportService reportService;

    public Float fuelUsageForEachVehicle(String registrationNumber) {

        Float FuelUsage = reportService.FuelUsageForEachVehicle(registrationNumber);

        return (FuelUsage);
    }

    public Float costUsageForEachVehicle(String registrationNumber) {

        Float CostUsage = reportService.CostUsageForEachVehicle(registrationNumber);

        return (CostUsage);
    }

   



}