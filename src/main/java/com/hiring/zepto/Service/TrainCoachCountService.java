package com.hiring.zepto.Service;

import com.hiring.zepto.Constants.Constants;
import com.hiring.zepto.DTO.CoachDTO;
import com.hiring.zepto.Model.Seat;
import com.hiring.zepto.Model.TrainCoachCount;
import com.hiring.zepto.Repository.CoachTypeRepository;
import com.hiring.zepto.Repository.SeatRepository;
import com.hiring.zepto.Repository.TrainCoachCountRepository;
import com.hiring.zepto.Repository.TrainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrainCoachCountService {

    @Autowired
    TrainCoachCountRepository trainCoachCountRepository;
    @Autowired
    SeatService seatService;
    @Autowired
    TrainRepository trainRepository;

    public TrainCoachCount findByTrainNumberAndCoachTypeId(Integer trainNumber, Integer coachTypeId){
        return trainCoachCountRepository.findByTrainNumberAndCoachTypeId(trainNumber,coachTypeId);
    }
    public String addCoachToTrain(CoachDTO coachDTO) {
        //check if there is a combination of trainNum and coachType in TrainCoachCount
        //if so inc coachCount in TrainCoachCount
        //else get Train and add count - 1

        //inc/update coachCapacity and Availability in Seat and TrainCoachCountId
        int currentCoachCount = 0;
        if(trainRepository.existsById(coachDTO.getTrainNumber())) {
            TrainCoachCount tcc = findByTrainNumberAndCoachTypeId(coachDTO.getTrainNumber(), coachDTO.getCoachTypeId());
            if(tcc!=null){
                currentCoachCount = tcc.getNoOfCoaches();
                trainCoachCountRepository.changeCoachesCount(coachDTO.getTrainNumber(),coachDTO.getCoachTypeId(),tcc.getNoOfCoaches()+coachDTO.getCoachCount());
            }
            else{
                tcc = trainCoachCountRepository.save(new TrainCoachCount(coachDTO.getTrainNumber(),coachDTO.getCoachTypeId(),coachDTO.getCoachCount()));
            }
            int count = coachDTO.getCoachCount();
            int startCountFrom = currentCoachCount == 0 ? 1 : currentCoachCount+1;
            for(int i=0; i< count; i++)
                seatService.saveSeatOrCoach(new Seat(
                        coachDTO.getTrainNumber(),
                        coachDTO.getCoachTypeId(),
                        coachDTO.getCoachCapacity(),
                        coachDTO.getCoachAvailability(),
                        tcc.getTrainCoachCountId(),
                        startCountFrom+i
                ));
            return Constants.SUCCESS;
        }
        else return "No train available to add a coach";
    }

    public String deleteCoachInTrain(Integer trainNumber, Integer coachTypeId) {
        TrainCoachCount tcc = findByTrainNumberAndCoachTypeId(trainNumber,coachTypeId);
        seatService.deleteByTrainCoachCountId(tcc.getTrainCoachCountId());
        trainCoachCountRepository.deleteById(tcc.getTrainCoachCountId());
        return Constants.DELETED;
    }
}
