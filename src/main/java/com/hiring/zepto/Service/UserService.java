package com.hiring.zepto.Service;

import com.hiring.zepto.Constants.Constants;
import com.hiring.zepto.Model.User;
import com.hiring.zepto.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public String verifyUserRole(String username, String password) {
        Optional<User> user = userRepository.findById(username);
        User u = user.get();
        if (u.getUserPassword().equals(password)) {
            if (u.getUserRole().equals(Constants.ADMIN)) {
                return Constants.ADMIN;
            } else return Constants.USER;
        }
        return Constants.NOT_MATCHED;
    }
}
