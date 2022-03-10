package com.hiring.zepto.Model;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "train_coach_count")
public class TrainCoachCount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "train_coach_count_id", nullable = false)
    private Integer trainCoachCountId;
    private Integer trainNumber;
    private Integer coachTypeId;
    private Integer noOfCoaches;

    public TrainCoachCount(Integer trainNumber, Integer coachTypeId, Integer noOfCoaches) {
        this.trainNumber = trainNumber;
        this.coachTypeId = coachTypeId;
        this.noOfCoaches = noOfCoaches;
    }

    public TrainCoachCount(Integer trainNumber, Integer coachTypeId) {
        this.trainNumber = trainNumber;
        this.coachTypeId = coachTypeId;
    }


}