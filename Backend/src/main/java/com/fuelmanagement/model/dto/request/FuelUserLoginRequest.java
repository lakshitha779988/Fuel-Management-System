package com.fuelmanagement.model.dto.request;

import com.google.firebase.database.annotations.NotNull;
import lombok.Data;
import org.hibernate.annotations.processing.Pattern;

@Data
public class FuelUserLoginRequest {

    private String mobileNumber;

    private String firebaseToken;
}
