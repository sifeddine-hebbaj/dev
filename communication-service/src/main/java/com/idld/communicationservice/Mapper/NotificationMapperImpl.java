package com.idld.communicationservice.Mapper;

import com.idld.communicationservice.Dto.RequestNotificationDto;
import com.idld.communicationservice.Dto.ResponseNotificationDto;
import com.idld.communicationservice.Entity.Notification;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class NotificationMapperImpl implements NotificationMapper {

    @Override
    public Notification toEntity(RequestNotificationDto requestNotificationDto) {
        Notification notification = new Notification();
        BeanUtils.copyProperties(requestNotificationDto, notification);
        notification.setStatus("PENDING");
        return notification;
    }

    @Override
    public ResponseNotificationDto toDto(Notification notification) {
        ResponseNotificationDto responseNotificationDto = new ResponseNotificationDto();
        BeanUtils.copyProperties(notification, responseNotificationDto);
        return responseNotificationDto;
    }
}

