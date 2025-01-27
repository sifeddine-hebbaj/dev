package com.idld.communicationservice.service;

import com.idld.communicationservice.Dto.ResponseNotificationDto;
import com.idld.communicationservice.Entity.Notification;
import java.util.List;

public interface NotificationServiceInterface {
    void sendNotifications(String recipients, String subject, String message);
    Notification sendNotification(String recipient, String subject, String message);
    List<Notification> getAllNotifications();
    public ResponseNotificationDto getNotificationById(Long id);
}
