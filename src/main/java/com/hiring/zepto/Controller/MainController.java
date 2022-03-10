package com.hiring.zepto.Controller;

import com.hiring.zepto.Constants.Constants;
import com.hiring.zepto.Model.DateSchedule;
import com.hiring.zepto.Model.Train;
import com.hiring.zepto.Model.User;
import com.hiring.zepto.Repository.DateRepository;
import com.hiring.zepto.Repository.TrainRepository;
import com.hiring.zepto.Repository.UserRepository;
import com.hiring.zepto.Service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.relation.Role;
import javax.print.attribute.standard.Media;
import java.sql.Date;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/common")
class MainController {

    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    DateRepository dateRepository;
    @Autowired
    TrainRepository trainRepository;

    @GetMapping(path = "/sayHi")
    public ResponseEntity<String> sayHello() {
        log.info("in /main/sayHi");
        try {
            return new ResponseEntity<>("Hello Bhavana Namburu!!", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/findRoleOfUser")
    @ResponseBody
    public String findRoleOfUser(@RequestParam("username") String username, @RequestParam("password") String password) {
        log.info("in /main/findRoleOfUser with parameters "+username+" - "+password );
        return userService.verifyUserRole(username, password);
    }

    @PostMapping(path = "/addUser")
    @ResponseBody
    public String addUser(@RequestParam("username") String username, @RequestParam("password") String password) {

        if (userRepository.existsById(username)) {
            return Constants.ALREADY_EXISTS;
        } else {
            userRepository.save(new User(username,password,Constants.USER));
            return Constants.SUCCESS;
        }
    }

    @GetMapping(path = "/viewDate")
    @ResponseBody
    public DateSchedule viewDate(@RequestParam("date") Date date) {
        return dateRepository.findById(date).get();
    }

    @GetMapping(path = "/viewAllDates")
    @ResponseBody
    public List<DateSchedule> viewAllDates() {
        return dateRepository.findAll();
    }

    @GetMapping(path = "/viewAllTrains")
    @ResponseBody
    public List<Train> viewAllTrains() {
        return trainRepository.findAll();
    }

    @GetMapping(path="/viewTrain")
    @ResponseBody
    public Train viewTrain(@RequestParam("trainNumber") Integer trainNumber){
        return trainRepository.findById(trainNumber).get();
    }
}