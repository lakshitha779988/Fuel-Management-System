package com.fuelmanagement.service.notificationService;

import com.fuelmanagement.model.dto.SMSNotificationDTO;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SMSNotificationService implements NotificationService<SMSNotificationDTO> {

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



    @Override
    public void sendNotification(SMSNotificationDTO notificationData) {
        try {
            // Format the mobile number by adding "+94" and removing the leading "0"
            String formattedNumber = formatMobileNumber(notificationData.getMobileNumber());

            Message message = Message.creator(
                            new com.twilio.type.PhoneNumber(formattedNumber),
                            new com.twilio.type.PhoneNumber(twilioPhoneNumber),
                            notificationData.getMessage())
                    .create();

            System.out.println("SMS sent successfully to " + formattedNumber);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Formats a mobile number by:
     *  - Removing the leading "0"
     *  - Adding "+94" country code
     */
    private String formatMobileNumber(String mobileNumber) {
        if (mobileNumber == null || mobileNumber.isEmpty()) {
            throw new IllegalArgumentException("Invalid mobile number");
        }

        // Remove leading "0" and add "+94"
        if (mobileNumber.startsWith("0")) {
            return "+94" + mobileNumber.substring(1);
        }
        return "+94" + mobileNumber;
    }
}
