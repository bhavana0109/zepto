package com.hiring.zepto.Model;

import lombok.*;
import org.hibernate.annotations.Check;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;

@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "booking")
@NoArgsConstructor
@GenericGenerator(
        name = "pnr-generator",
        strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
        parameters = {
                @Parameter(name = "sequence_name", value = "pnr_sequence"),
                @Parameter(name = "initial_value", value = "10210"),
                @Parameter(name = "increment_size", value = "1")
        })

public class Booking {
    @Id
    @GeneratedValue(generator = "pnr-generator")
    @Column(name = "pnr", nullable = false)
    private Long pnr;
    @Check(constraints = "noOfPassengers<7")
    private Integer noOfPassengers;
    private Date dateOfJourney;
    private ArrayList<Integer> seatNumbers;
    private Integer trainNumber;
    private Integer coachTypeId;
    private Integer coachNumber;
    @Column(name = "username", length = 25)
    private String username;
    private Integer seatId;

    public Booking(Integer noOfPassengers, Date dateOfJourney, ArrayList<Integer> seatNumbers, Integer trainNumber, Integer coachTypeId, Integer coachNumber, String username, Integer seatId) {
        this.noOfPassengers = noOfPassengers;
        this.dateOfJourney = dateOfJourney;
        this.seatNumbers = seatNumbers;
        this.trainNumber = trainNumber;
        this.coachTypeId = coachTypeId;
        this.coachNumber = coachNumber;
        this.username = username;
        this.seatId = seatId;
    }

    public Booking(Integer noOfPassengers, Date dateOfJourney, Integer trainNumber, Integer coachTypeId, String username) {
        this.noOfPassengers = noOfPassengers;
        this.dateOfJourney = dateOfJourney;
        this.trainNumber = trainNumber;
        this.coachTypeId = coachTypeId;
        this.username = username;
    }
}