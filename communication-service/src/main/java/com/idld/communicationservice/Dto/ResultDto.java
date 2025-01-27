package com.idld.communicationservice.Dto;

import lombok.*;


@Builder
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResultDto {
    private long studentId;
    private long courseId;
    private double grade;

}
