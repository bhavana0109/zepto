package com.hiring.zepto.Model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Date;

@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "train")
@NoArgsConstructor
public class Train implements Serializable {
    @Id
    @Column(name = "trainNumber", nullable = false)
    private Integer trainNumber;
    private String trainName;
    private String startStation;
    private String destinationStation;
    private Date date;
}