package com.hiring.zepto.Repository;

import com.hiring.zepto.DTO.TicketDTO;
import com.hiring.zepto.Model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> findByUsername(String username);

    List<Booking> findByTrainNumber(Integer trainNumber);
}
