package com.hiring.zepto.DTO;

import lombok.*;
import org.hibernate.annotations.Check;

import javax.persistence.Column;
import java.sql.Date;
import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TicketDTO {
    private Long pnr;
    private Integer noOfPassengers;
    private Date dateOfJourney;
    private ArrayList<Integer> seatNumbers;
    private Integer trainNumber;
    private String coachTypeName;
    private Integer coachNumber;
    private String username;

    public TicketDTO(ArrayList<Integer> seatNumbers, Integer trainNumber, String coachTypeName, Integer coachNumber) {
        this.seatNumbers = seatNumbers;
        this.trainNumber = trainNumber;
        this.coachTypeName = coachTypeName;
        this.coachNumber = coachNumber;
    }
}
