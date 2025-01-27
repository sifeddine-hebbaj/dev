package com.idld.communicationservice.service;

import com.idld.communicationservice.Dto.ResponseNotificationDto;
import com.idld.communicationservice.Email.EmailServiceInterface;
import com.idld.communicationservice.Entity.Notification;
import com.idld.communicationservice.Mapper.NotificationMapper;
import com.idld.communicationservice.Repository.NotificationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class NotificationService implements com.idld.communicationservice.service.NotificationServiceInterface {

    private static final Logger logger = LoggerFactory.getLogger(NotificationService.class);

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private EmailServiceInterface emailService;

    @Autowired
    private NotificationMapper notificationMapper;

    @Override
    public Notification sendNotification(String recipient, String subject, String message) {
        try {
            Notification notification = new Notification(recipient, message, subject, "SENT", LocalDateTime.now());
            notificationRepository.save(notification);
            emailService.sendEmail(recipient, subject, message);
            return notification;
        } catch (Exception e) {
            logger.error("Error while sending notification to " + recipient, e);
        }
        return null;
    }

    @Override
    public void sendNotifications(String recipients, String subject, String message) {
        String[] recipientArray = recipients.split(" ");
        for (String recipient : recipientArray) {
            try {
                sendNotification(recipient, subject, message);
            } catch (Exception e) {
                logger.error("Failed to send notification to: " + recipient, e);
            }
        }
    }


    @Override
    public List<Notification> getAllNotifications() {

        return notificationRepository.findAll();
    }
    @Override
    public ResponseNotificationDto getNotificationById(Long id) {
        Optional<Notification> notification = notificationRepository.findById(id);
        if (notification.isPresent()) {
            return notificationMapper.toDto(notification.get());
        } else {
            return null;
        }
    }
}
