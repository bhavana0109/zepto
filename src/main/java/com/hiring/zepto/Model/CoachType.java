package com.hiring.zepto.Model;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
@Table(name = "coach_type")
public class CoachType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer coachTypeId;
    private String coachTypeName;
}
