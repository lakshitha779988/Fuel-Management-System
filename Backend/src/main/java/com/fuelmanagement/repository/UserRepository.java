package com.fuelmanagement.repository;

import com.fuelmanagement.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findByMobileNumber(String mobileNumber);

    User findByLastName(String username);

    boolean existsByMobileNumber(String mobileNumber);

    boolean existsByNationalId(String nationalId);
}
