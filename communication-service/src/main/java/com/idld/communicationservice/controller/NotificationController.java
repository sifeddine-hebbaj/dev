package com.idld.communicationservice.controller;


import com.idld.communicationservice.Dto.ResponseNotificationDto;
import com.idld.communicationservice.Dto.ResultDto;
import com.idld.communicationservice.Entity.Notification;
import com.idld.communicationservice.service.NotificationServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:5174"})
@RestController
@RequestMapping("/api/notificationsEmail")
public class NotificationController implements NotificationControllerInterface {


    @Autowired
    private NotificationServiceInterface notificationService;

    @PostMapping("/send")
    public void sendNotification(@RequestBody Notification notification) {
        try {
            String subject = notification.getObject() != null ? notification.getObject() : "Mise à jour importante";
            if (notification.getRecipient().contains(" ")) {
                notificationService.sendNotifications(notification.getRecipient(), subject, notification.getMessage());
            } else {
                notificationService.sendNotification(notification.getRecipient(), subject, notification.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error sending notification(s)", e);
        }
    }

    @GetMapping("/all")
    public List<Notification> getAllNotifications() {

        return notificationService.getAllNotifications();
    }

}