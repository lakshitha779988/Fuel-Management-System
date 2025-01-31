package com.fuelmanagement.service;

import com.mailgun.client.MailgunClient;
import com.mailgun.api.v3.MailgunMessagesApi;
import com.mailgun.model.message.Message;
import com.mailgun.model.message.MessageResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Service
public class EmailService {

    @Value("${mailgun.api.key}")
    private String apiKey;

    @Value("${mailgun.domain}")
    private String domain;

    @Value("${mailgun.from}")
    private String from;

    public String sendEmail(String to, String subject, String text) {
        try {
            // Initialize Mailgun client and Messages API
            MailgunMessagesApi mailgunMessagesApi = MailgunClient.config(apiKey).createApi(MailgunMessagesApi.class);

            // Build the message payload
            Message message = Message.builder()
                    .from(from)
                    .to(to)
                    .subject(subject)
                    .text(text)
                    .build();

            // Send the email and capture the response
            MessageResponse response = mailgunMessagesApi.sendMessage(domain, message);

            return "Email sent successfully! Message ID: " + response.getId();
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed to send email: " + e.getMessage();
        }
    }

    public String generateFuelStationRegistrationEmailContent(String fuelStationName) {
        // Get the current date in a readable format
        String currentDate = LocalDate.now().format(DateTimeFormatter.ofPattern("dd MMMM yyyy"));

        // Construct the email content
        return String.format(
                "Subject: Registration Confirmation\n\n" +
                        "Dear %s,\n\n" +
                        "Thank you for registering your fuel station with us on %s. We are delighted to welcome you to the Fuel Management System powered by FuelManagement, Sri Lanka.\n\n" +
                        "Your registration is currently under review and will be approved by our admin team shortly. If you have any questions or require assistance, please don't hesitate to contact us at support@fuelmanagement.lk.\n\n" +
                        "We are committed to providing you with the best service to manage your fuel station operations efficiently.\n\n" +
                        "Best regards,\n" +
                        "The FuelManagement Team\n" +
                        "FuelManagement, Sri Lanka\n" +
                        "support@fuelmanagement.lk\n",
                fuelStationName, currentDate
        );
    }

    public String generateUserRegistrationEmailContent(String customerName, String mobileNumber, String vehicleRegistrationNumber) {
        // Mask the mobile number to show only the last 3 digits
        String maskedMobileNumber = "XXXXXX" + mobileNumber.substring(mobileNumber.length() - 3);

        // Get the current date
        String registrationDate = LocalDate.now().format(DateTimeFormatter.ofPattern("dd MMMM yyyy"));

        // Construct the email content
        return String.format(
                "Subject: Welcome to FuelManagement!\n\n" +
                        "Dear %s,\n\n" +
                        "We are excited to inform you that your registration was successful on %s.\n\n" +
                        "Your vehicle with registration number '%s' has been successfully added to our system.\n\n" +
                        "You can now sign in using your mobile number: %s.\n\n" +
                        "If you have any questions or need assistance, feel free to reach out to us at support@fuelmanagement.lk.\n\n" +
                        "Thank you for choosing FuelManagement, Sri Lanka!\n\n" +
                        "Best regards,\n" +
                        "The FuelManagement Team\n" +
                        "FuelManagement, Sri Lanka\n" +
                        "support@fuelmanagement.lk\n",
                customerName, registrationDate, vehicleRegistrationNumber, maskedMobileNumber
        );
    }

    public String generateUserLoginNotificationEmailContent(String customerName, LocalDateTime loginTime) {
        // Format the login time
        String formattedLoginTime = loginTime.format(DateTimeFormatter.ofPattern("dd MMMM yyyy, hh:mm a"));

        // Construct the email content
        return String.format(
                "Subject: Login Notification\n\n" +
                        "Dear %s,\n\n" +
                        "We noticed that you logged in to your account on %s.\n\n" +
                        "If this was you, no further action is needed. If you suspect unauthorized access, please contact us immediately at support@fuelmanagement.lk.\n\n" +
                        "Thank you for being a valued user of FuelManagement.\n\n" +
                        "Best regards,\n" +
                        "The FuelManagement Team\n" +
                        "FuelManagement, Sri Lanka\n" +
                        "support@fuelmanagement.lk\n",
                customerName, formattedLoginTime
        );
    }

    public String generateFuelLimitUpdateEmailContent(String fuelStationName, float usage, LocalDateTime localTime, float existingLimit) {
        return "Subject: Fuel Limit Update Notification\n\n" +
                "Dear Valued Customer,\n\n" +
                "We are writing to inform you that your fuel limit has been updated as per the latest transaction at our station.\n\n" +
                "Here are the details of the update:\n" +
                "Fuel Station Name: " + fuelStationName + "\n" +
                "Fuel Used: " + usage + " liters\n" +
                "Update Time: " + localTime + "\n" +
                "Remaining Fuel Limit: " + existingLimit + " liters\n\n" +
                "Thank you for choosing our services. If you have any questions or concerns, please feel free to reach out to us.\n\n" +
                "Warm Regards,\n" +
                "FuelManagement Team\n" +
                "Sri Lanka";
    }

    public String generateFuelStationAccountActiveEmailContent(String fuelStationName, LocalDateTime activationTime) {
        return "Subject: Fuel Station Account Activation\n\n" +
                "Dear " + fuelStationName + " Team,\n\n" +
                "We are pleased to inform you that your fuel station account has been successfully activated.\n\n" +
                "Activation Details:\n" +
                "Fuel Station Name: " + fuelStationName + "\n" +
                "Activation Time: " + activationTime + "\n\n" +
                "You can now log in and start managing fuel distribution through our system. If you have any questions or need assistance, feel free to contact our support team.\n\n" +
                "Thank you for being a valued partner.\n\n" +
                "Best Regards,\n" +
                "FuelManagement Team\n" +
                "Sri Lanka";
    }


    public String generateFuelStationAccountBlockedEmailContent(String fuelStationName, LocalDateTime blockTime) {
        return "Subject: Fuel Station Account Blocked\n\n" +
                "Dear " + fuelStationName + " Team,\n\n" +
                "We regret to inform you that your fuel station account has been blocked due to policy violations or operational concerns.\n\n" +
                "Blocking Details:\n" +
                "Fuel Station Name: " + fuelStationName + "\n" +
                "Block Time: " + blockTime + "\n\n" +
                "If you believe this was a mistake or need further clarification, please contact our support team immediately.\n\n" +
                "Best Regards,\n" +
                "FuelManagement Team\n" +
                "Sri Lanka";
    }

    public String generateQRCodeUpdateEmailContent(String vehicleRegNumber, LocalDateTime updateTime) {
        return "Subject: QR Code Update Notification\n\n" +
                "Dear User,\n\n" +
                "We would like to inform you that your QR code has been successfully updated.\n\n" +
                "Update Details:\n" +
                "Vehicle Registration Number: " + vehicleRegNumber + "\n" +
                "Update Time: " + updateTime + "\n\n" +
                "If you did not request this update or have any concerns, please contact our support team immediately.\n\n" +
                "Best Regards,\n" +
                "FuelManagement Team\n" +
                "Sri Lanka";
    }



}
