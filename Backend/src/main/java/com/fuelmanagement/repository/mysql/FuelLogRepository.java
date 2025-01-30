package com.fuelmanagement.repository.mysql;

import com.fuelmanagement.model.dto.response.FuelTransactionDTO;
import com.fuelmanagement.model.entity.mysql.FuelLog;
import org.springframework.data.domain.Pageable;  // Correct Pageable import
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FuelLogRepository extends JpaRepository<FuelLog, Long> {

    List<FuelLog> findAllByVehicleId(Long id);

    List<FuelLog> findAllByUserId(Long id);

    void deleteAllByVehicleId(Long id);

    @Query("SELECT f.transactionTime, f.fuelAmount, fs.name " +
            "FROM FuelLog f JOIN FuelStation fs ON f.fuelStation.id = fs.id " +
            "WHERE f.user.id = :userId ORDER BY f.transactionTime DESC")
    List<Object[]> findLast10TransactionsByUser(@Param("userId") Long userId);

}
