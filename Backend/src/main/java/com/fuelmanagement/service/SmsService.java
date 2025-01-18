package com.fuelmanagement.service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;



@Service
public class SmsService {

    @Value("${twilio.account-sid}")
    private String accountSid;

    @Value("${twilio.auth-token}")
    private String authToken;

    @Value("${twilio.phone-number}")
    private String twilioPhoneNumber;

    // Initialize Twilio after properties are loaded
    @PostConstruct
    public void init() {
        Twilio.init(accountSid, authToken);
    }

    /**
     * Sends an OTP to the specified phone number.
     *
     * @param toPhoneNumber The recipient's phone number in E.164 format (e.g., +1234567890).
     * @param otp           The OTP to be sent.
     * @return Message SID if the message is sent successfully.
     */
    public String sendOtp(String toPhoneNumber, String otp) {
        try {
            Message message = Message.creator(
                            new com.twilio.type.PhoneNumber(toPhoneNumber),
                            new com.twilio.type.PhoneNumber(twilioPhoneNumber),
                            "Your OTP is: " + otp)
                    .create();

            return message.getSid();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
