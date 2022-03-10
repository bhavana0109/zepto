package com.hiring.zepto.DTO;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CoachDTO {
    private Integer trainNumber;
    private Integer coachTypeId;
    private Integer coachCount;
    private Integer coachCapacity;
    private Integer coachAvailability;
}
