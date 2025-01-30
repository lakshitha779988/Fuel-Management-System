package com.fuelmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@EnableScheduling
@ComponentScan(basePackages = "com.fuelmanagement")
public class FuelManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(FuelManagementSystemApplication.class, args);
	}

}
