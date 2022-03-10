package com.hiring.zepto.Service;

import com.hiring.zepto.Repository.DateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DateScheduleService {

    @Autowired
    DateRepository dateRepository;
}
