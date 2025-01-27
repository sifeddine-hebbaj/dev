package com.idld.communicationservice.controller;

import com.idld.communicationservice.Entity.Notification;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface NotificationControllerInterface {
    public void sendNotification(@RequestBody Notification notification);
    public List<Notification> getAllNotifications();
}
