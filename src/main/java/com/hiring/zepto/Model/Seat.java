package com.hiring.zepto.Model;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "seat")
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer seatId;
    private Integer trainNumber;
    private Integer coachTypeId;
    private Integer coachNumber;
    private Integer coachCapacity;
    private Integer coachAvailability;
    private Integer trainCoachCountId;

    public Seat(Integer trainNumber, Integer coachTypeId, Integer coachCapacity, Integer coachAvailability, Integer trainCoachCountId) {
        this.trainNumber = trainNumber;
        this.coachTypeId = coachTypeId;
        this.coachCapacity = coachCapacity;
        this.coachAvailability = coachAvailability;
        this.trainCoachCountId = trainCoachCountId;
    }

    public Seat(Integer trainNumber, Integer coachTypeId, Integer coachCapacity, Integer coachAvailability, Integer trainCoachCountId, Integer coachNumber) {
        this.trainNumber = trainNumber;
        this.coachTypeId = coachTypeId;
        this.coachCapacity = coachCapacity;
        this.coachAvailability = coachAvailability;
        this.trainCoachCountId = trainCoachCountId;
        this.coachNumber = coachNumber;
    }
}
