package com.fuelmanagement.service.entityService;

import com.fuelmanagement.model.dto.response.FuelTransactionDTO;
import com.fuelmanagement.model.entity.mysql.User;
import com.fuelmanagement.repository.mysql.FuelLogRepository;
import com.fuelmanagement.repository.mysql.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class FuelLogService {

    private final FuelLogRepository fuelLogRepository;
    private final UserRepository userRepository;

    @Autowired
    public FuelLogService(FuelLogRepository fuelLogRepository, UserRepository userRepository) {
        this.fuelLogRepository = fuelLogRepository;
        this.userRepository = userRepository;
    }

    public List<FuelTransactionDTO> getLast10Transactions(String mobileNumber) {

        User user = userRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new IllegalArgumentException("User not found with mobileNumber: " + mobileNumber));
        Long userId = user.getId();

        List<Object[]> results = fuelLogRepository.findLast10TransactionsByUser(userId);

        List<FuelTransactionDTO> transactions = new ArrayList<>();
        for (Object[] result : results) {
            // Convert Timestamp to LocalDateTime
            Timestamp timestamp = (Timestamp) result[0];
            LocalDateTime transactionTime = timestamp.toLocalDateTime(); // Convert Timestamp to LocalDateTime

            Float fuelAmount = (Float) result[1];
            String fuelStationName = (String) result[2];

            FuelTransactionDTO dto = new FuelTransactionDTO(transactionTime, fuelAmount, fuelStationName);
            transactions.add(dto);
        }

        return transactions;
    }


}
