package com.fuelmanagement.controller;


import com.fuelmanagement.model.entity.mysql.FuelLog;
import com.fuelmanagement.repository.mysql.FuelLogRepository;
import com.fuelmanagement.repository.mysql.VehicleTypeRepository;
import com.fuelmanagement.service.ReportService;

import java.util.List;

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

   public List<FuelLog> getLatest10Transaction(String registrationNumber){

       List<FuelLog> Latest10Transaction = reportService.GetLast10Transaction(registrationNumber);

       return (Latest10Transaction);

   }



}