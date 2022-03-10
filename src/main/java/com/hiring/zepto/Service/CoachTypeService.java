package com.hiring.zepto.Service;

import com.hiring.zepto.Repository.CoachTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CoachTypeService {

    @Autowired
    CoachTypeRepository coachTypeRepository;
}
