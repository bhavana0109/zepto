package com.hiring.zepto.Repository;

import com.hiring.zepto.Model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface SeatRepository extends JpaRepository<Seat, Integer> {


    @Modifying
    @Query("delete from Seat s where s.trainCoachCountId=:trainCoachCountId")
    void deleteSeats(@Param("trainCoachCountId") Integer trainCoachCountId);

    List<Seat> findByTrainNumberAndCoachTypeId(Integer trainNumber, Integer coachTypeId);

    @Query(value = "SELECT s FROM Seat s WHERE s.trainNumber=:trainNumber and s.coachTypeId=:coachTypeId and s.coachAvailability>=:noOfPassengers")
    List<Seat> findAvailableSeats(@Param("trainNumber") Integer trainNumber, @Param("coachTypeId") Integer coachTypeId, @Param("noOfPassengers") Integer noOfPassengers);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "UPDATE Seat s SET s.coachAvailability=:coachAvailability WHERE s.seatId=:seatId")
    void updateCoachAvailability(@Param("seatId") Integer seatId, @Param("coachAvailability") int coachAvailability);

    @Query(value = "SELECT s FROM Seat s WHERE s.trainNumber=:trainNumber and s.coachTypeId=:coachTypeId and s.coachAvailability>0")
    List<Seat> findAvailableByTrainNumberAndCoachTypeId(@Param("trainNumber") Integer trainNumber, @Param("coachTypeId") Integer coachTypeId);
}