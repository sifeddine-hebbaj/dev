package com.idld.communicationservice.Mapper;

import com.idld.communicationservice.Dto.RequestNotificationDto;
import com.idld.communicationservice.Dto.ResponseNotificationDto;
import com.idld.communicationservice.Entity.Notification;

public interface NotificationMapper {

    Notification toEntity(RequestNotificationDto requestNotificationDto);

    ResponseNotificationDto toDto(Notification notification);
}

