package com.hiring.zepto.Controller;


import com.hiring.zepto.Constants.Constants;
import com.hiring.zepto.DTO.CoachDTO;
import com.hiring.zepto.DTO.TicketDTO;
import com.hiring.zepto.DTO.TrainDTO;
import com.hiring.zepto.Model.DateSchedule;
import com.hiring.zepto.Model.Seat;
import com.hiring.zepto.Model.Train;
import com.hiring.zepto.Repository.BookingRepository;
import com.hiring.zepto.Repository.DateRepository;
import com.hiring.zepto.Service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(path = "/admin")
public class AdminController {

    @Autowired
    TrainService trainService;
    @Autowired
    DateRepository dateRepository;
    @Autowired
    TrainCoachCountService trainCoachCountService;
    @Autowired
    BookingService bookingService;
    @Autowired
    SeatService seatService;

    @PostMapping(path = "/addTrain",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Train addTrain(@RequestBody TrainDTO train) {
        log.info("In /admin/addTrain with details - " + train.toString());
        return trainService.insertTrain(train);
    }

    @PostMapping(path = "/addCoachToTrain", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String addCoachToTrain(@RequestBody CoachDTO coachDTO) {

        return trainCoachCountService.addCoachToTrain(coachDTO);
    }

    @PostMapping(path = "/addDate")
    @ResponseBody
    public String addDate(@RequestParam("date") Date date) {
        DateSchedule ds = new DateSchedule(date);
        if (dateRepository.existsById(date)) {
            return Constants.ALREADY_EXISTS;
        } else {
            dateRepository.save(ds);
            return Constants.SUCCESS;
        }
    }

    @DeleteMapping(path = "/deleteCoachInTrain")
    @ResponseBody
    public String deleteCoachInTrain(@RequestParam("trainNumber") Integer trainNumber,
                                     @RequestParam("coachTypeId") Integer coachTypeId){
        return trainCoachCountService.deleteCoachInTrain(trainNumber,coachTypeId);
    }

    //@PostMapping(path = "/updateCoachAvailabilityInTrain")

    @GetMapping(path = "/viewAvailableSeats")
    @ResponseBody
    public List<Seat> viewAvailableSeats(@RequestParam("trainNumber") Integer trainNumber,
                                         @RequestParam("coachTypeId") Integer coachTypeId){
        return seatService.findAvailableByTrainNumberAndCoachTypeId(trainNumber,coachTypeId);
    }

    @GetMapping(path = "/viewAllBookedSeats", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<TicketDTO> viewAllBookedSeats(@RequestParam("trainNumber") Integer trainNumber){
        return bookingService.getAllBookedSeats(trainNumber);
    }

}
