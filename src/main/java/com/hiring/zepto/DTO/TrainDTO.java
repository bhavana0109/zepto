package com.hiring.zepto.DTO;

import lombok.*;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class TrainDTO {

    private Integer trainNumber;
    private String trainName;
    private String startStation;
    private String destinationStation;
    private Date date;
}
