package com.fuelmanagement.repository.mysql;

import com.fuelmanagement.model.entity.mysql.FuelType;
import org.hibernate.mapping.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public class FuelTypeRepository {
    public List findAll() {
        return null;
    }

    @Repository
    public interface fuelTypeRepository extends JpaRepository<FuelType, Long> {
        Optional<FuelType> findByName(String name);

    }

}
