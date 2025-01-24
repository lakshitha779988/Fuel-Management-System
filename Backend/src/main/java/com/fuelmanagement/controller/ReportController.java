package com.fuelmanagement.controller;

import com.fuelmanagement.repository.mysql.FuelLogRepository;
import com.fuelmanagement.repository.mysql.VehicleTypeRepository;

public class ReportController {

  ReportService reportService;

  public Float fuelUsageForEachVehicle(String registrationNumber) {

    Float fuelUsage = reportService.FuelUsageForEachVehicle(registrationNumber);

    return fuelUsage;
  }

}