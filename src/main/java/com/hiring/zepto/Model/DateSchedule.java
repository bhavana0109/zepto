package com.hiring.zepto.Model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "date_schedule")
@NoArgsConstructor
public class DateSchedule {
    @Id
    @Column(name = "date", nullable = false)
    private Date date;


}