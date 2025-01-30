package com.fuelmanagement.repository.mysql;

import com.fuelmanagement.model.entity.mysql.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface AdminRepository extends JpaRepository<Admin,Long> {

    Optional<Admin>  findByUserName(String userName);
}
