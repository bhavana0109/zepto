package com.hiring.zepto.Service;

import com.hiring.zepto.DTO.TicketDTO;
import com.hiring.zepto.Model.Booking;
import com.hiring.zepto.Model.Seat;
import com.hiring.zepto.Repository.BookingRepository;
import com.hiring.zepto.Repository.CoachTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookingService {

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    SeatService seatService;

    @Autowired
    CoachTypeRepository coachTypeRepository;

    public TicketDTO bookingAutoAllocation(Booking booking) {
        //check for available compartments
        Seat finalCoach = seatService.findAvailableCoaches(booking.getTrainNumber(), booking.getCoachTypeId(), booking.getNoOfPassengers());
        if (finalCoach!=null) {
            //allocate seat numbers
            ArrayList<Integer> seatNumbers = allocateSeatNumbers(booking.getNoOfPassengers(), finalCoach.getCoachAvailability());
            //decrease seat availability
            seatService.updateCoachAvailability(finalCoach.getSeatId(), finalCoach.getCoachAvailability() - booking.getNoOfPassengers());
            //save booking and return ticket with pnr
//        List<Seat> seatList = seatService.findByTrainNumberAndCoachTypeId(booking.getTrainNumber(), booking.getCoachTypeId());
//        int coachNumber = seatList.indexOf(finalCoach) + 1;
            booking = bookingRepository.save(new Booking(booking.getNoOfPassengers(), booking.getDateOfJourney(), seatNumbers, booking.getTrainNumber(), booking.getCoachTypeId(), finalCoach.getCoachNumber(), booking.getUsername(), finalCoach.getSeatId()));
            return getTicket(booking);
        }
        else return null;
    }

    public TicketDTO getTicket(Booking booking) {
        return new TicketDTO(booking.getPnr(), booking.getNoOfPassengers(), booking.getDateOfJourney(), booking.getSeatNumbers(), booking.getTrainNumber(), coachTypeRepository.findById(booking.getCoachTypeId()).get().getCoachTypeName(), booking.getCoachNumber(), booking.getUsername());
    }

    public ArrayList<Integer> allocateSeatNumbers(Integer passengerCount, Integer availabilityInCoach) {
        ArrayList<Integer> seatNumbers = new ArrayList<>();

        for (int i = passengerCount; i > 0; i--) {
            seatNumbers.add(availabilityInCoach);
            availabilityInCoach--;
        }
        return seatNumbers;
    }

    public List<TicketDTO> viewTicketsByUsername(String username) {
        List<TicketDTO> tickets = new ArrayList<>();
        for (Booking b: bookingRepository.findByUsername(username)
             ) {
            tickets.add(getTicket(b));
        }
        return tickets;
    }

    public List<TicketDTO> getAllBookedSeats(Integer trainNumber) {
        List<TicketDTO> tickets = new ArrayList<>();
        for (Booking b: bookingRepository.findByTrainNumber(trainNumber)
        ) {
            tickets.add(getTicket(b));
        }
        return tickets;
    }
}
