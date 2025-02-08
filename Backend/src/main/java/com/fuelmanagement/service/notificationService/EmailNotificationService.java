package com.fuelmanagement.service.notificationService;

import com.fuelmanagement.model.dto.EmailNotificationDTO;
import com.mailgun.api.v3.MailgunMessagesApi;
import com.mailgun.client.MailgunClient;
import com.mailgun.model.message.Message;
import com.mailgun.model.message.MessageResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class EmailNotificationService implements NotificationService<EmailNotificationDTO> {
    @Value("${mailgun.api.key}")
    private String apiKey;

    @Value("${mailgun.domain}")
    private String domain;

    @Value("${mailgun.from}")
    private String from;

    @Override
    public void sendNotification(EmailNotificationDTO notificationData) {
        try {
            // Initialize Mailgun client and Messages API
            MailgunMessagesApi mailgunMessagesApi = MailgunClient.config(apiKey).createApi(MailgunMessagesApi.class);

            // Build the message payload
            Message message = Message.builder()
                    .from(from)
                    .to(notificationData.getEmail())
                    .subject(notificationData.getSubject())
                    .text(notificationData.getMessage())
                    .build();

            // Send the email and capture the response
            MessageResponse response = mailgunMessagesApi.sendMessage(domain, message);


        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}
