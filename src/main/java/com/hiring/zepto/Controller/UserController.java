package com.hiring.zepto.Controller;

import com.hiring.zepto.DTO.TicketDTO;
import com.hiring.zepto.Model.Booking;
import com.hiring.zepto.Repository.BookingRepository;
import com.hiring.zepto.Service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;
import java.sql.Date;
import java.util.List;

@Controller
@RequestMapping(path = "/user")
public class UserController {

    @Autowired
    BookingService bookingService;

    @Autowired
    BookingRepository bookingRepository;

    //singleBookingAutoAllocation
    @PostMapping(path="/bookingAutoAllocation",
    produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public TicketDTO bookingAutoAllocation(@RequestParam("Date Of Journey")Date dateOfJourney, @RequestParam("noOfPassengers") Integer noOfPassengers,
                                           @RequestParam("trainNumber") Integer trainNumber, @RequestParam("coachTypeId") Integer coachTypeId,
                                           @RequestParam("username") String username){
        return bookingService.bookingAutoAllocation(new Booking(noOfPassengers,dateOfJourney,trainNumber,coachTypeId,username));
    }

    @GetMapping(path = "/viewTicketsByUsername", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<TicketDTO> viewTicketsByUsername(@RequestParam("username") String username){
        return bookingService.viewTicketsByUsername(username);
    }

    @GetMapping(path = "/viewTicket", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Booking viewTicket(@RequestParam("PNR") Long pnr){
        return bookingRepository.findById(pnr).get();
    }

}
