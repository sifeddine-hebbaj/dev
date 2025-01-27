package com.idld.communicationservice.Dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseNotificationDto {

    private Long id;
    private String recipient;
    private String message;
    private String object;
    private String status;
    private LocalDateTime timestamp;

}

