package com.idld.communicationservice.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestNotificationDto {

    private String recipient;
    private String message;
    private String object;


}

