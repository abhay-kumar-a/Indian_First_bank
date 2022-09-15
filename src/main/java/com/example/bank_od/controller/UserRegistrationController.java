package com.example.bank_od.controller;

import com.example.bank_od.model.User_details;
import com.example.bank_od.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserRegistrationController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/data")
    public List<User_details> allRegistration()
    {
        return userRepository.findAll();
    }
    @PostMapping("/registration")
    public ResponseEntity userRegistration(@RequestBody User_details userDetailsRegistration)
    {
        userRepository.save(userDetailsRegistration);
       return new  ResponseEntity<>("CREATED", HttpStatus.CREATED);

    }

}
