package com.hiring.zepto.Repository;

import com.hiring.zepto.Model.DateSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;

@Repository
public interface DateRepository extends JpaRepository<DateSchedule, Date> {
}
