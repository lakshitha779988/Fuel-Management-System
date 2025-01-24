package com.fuelmanagement.repository.mysql;

import com.fuelmanagement.model.entity.mysql.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional <User> findByMobileNumber(String mobileNumber);

    User findByLastName(String username);

    Optional<User> findByNationalId(String nationalId);

    boolean findByVehicleId(Long vehicleId);

    boolean existsByMobileNumber(String mobileNumber);

    boolean existsByNationalId(String nationalId);

}
