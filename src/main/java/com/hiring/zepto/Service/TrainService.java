package com.hiring.zepto.Service;

import com.hiring.zepto.DTO.TrainDTO;
import com.hiring.zepto.Model.DateSchedule;
import com.hiring.zepto.Model.Train;
import com.hiring.zepto.Repository.DateRepository;
import com.hiring.zepto.Repository.TrainRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TrainService {

    @Autowired
    TrainRepository trainRepository;

    @Autowired
    DateRepository dateRepository;

    public Train insertTrain(TrainDTO trainDTO) {

        Train train = new Train(trainDTO.getTrainNumber(), trainDTO.getTrainName(), trainDTO.getStartStation(), trainDTO.getDestinationStation(), trainDTO.getDate());
        if (!trainRepository.existsById(trainDTO.getTrainNumber())) {
            if (!dateRepository.existsById(train.getDate()))
                dateRepository.save(new DateSchedule(train.getDate()));
            Train t = trainRepository.save(train);
            log.info("in insertTrain, TrainService.java. Saved Train into DB with details " + t);
            return t;
        }
        return trainRepository.findById(trainDTO.getTrainNumber()).get();
    }
}
