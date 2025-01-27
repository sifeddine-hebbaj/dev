package com.idld.communicationservice.Repository;

import com.idld.communicationservice.Entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
