package com.hiring.zepto.Service;

import com.hiring.zepto.Model.Seat;
import com.hiring.zepto.Repository.CoachTypeRepository;
import com.hiring.zepto.Repository.SeatRepository;
import com.hiring.zepto.Repository.TrainCoachCountRepository;
import com.hiring.zepto.Repository.TrainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatService {

    @Autowired
    TrainCoachCountRepository trainCoachCountRepository;
    @Autowired
    CoachTypeRepository coachTypeRepository;
    @Autowired
    SeatRepository seatRepository;
    @Autowired
    TrainRepository trainRepository;

    public void saveSeatOrCoach(Seat seat){
        seatRepository.save(seat);
    }

    public void deleteByTrainCoachCountId(Integer trainCoachCountId) {
        seatRepository.deleteSeats(trainCoachCountId);
    }

    public List<Seat> findByTrainNumberAndCoachTypeId(Integer trainNumber, Integer coachTypeId) {
        return seatRepository.findByTrainNumberAndCoachTypeId(trainNumber,coachTypeId);
    }

    public Seat findAvailableCoaches(Integer trainNumber, Integer coachTypeId, Integer noOfPassengers) {
        return seatRepository.findAvailableSeats(trainNumber,coachTypeId,noOfPassengers).stream().findFirst().get();
    }

    public void updateCoachAvailability(Integer seatId, int coachAvailability) {
        seatRepository.updateCoachAvailability(seatId,coachAvailability);
    }

    public List<Seat> findAvailableByTrainNumberAndCoachTypeId(Integer trainNumber, Integer coachTypeId) {
        return seatRepository.findAvailableByTrainNumberAndCoachTypeId(trainNumber,coachTypeId);
    }
}
