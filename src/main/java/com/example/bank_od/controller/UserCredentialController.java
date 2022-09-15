package com.example.bank_od.controller;

import com.example.bank_od.model.Bank;
import com.example.bank_od.model.UserLoginCredential;
import com.example.bank_od.repository.UserLoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/userLoginDetails")
public class UserCredentialController {

    @Autowired
    UserLoginRepository userLoginRepository;

    @GetMapping("/data")
    public List<UserLoginCredential> get()
    {
        return userLoginRepository.findAll();
    }

    UserLoginCredential userLoginCredential = new UserLoginCredential();
    @PostMapping("/")
    public UserLoginCredential userLoginCredential(@RequestBody Bank bank)
    {
        userLoginCredential.setUser_id(bank.getUserRegistration().getUser_id());
        userLoginCredential.setUser_login_id(bank.getGenerateUserId());
        userLoginCredential.setUser_login_password(bank.getGenerateUserPassword());
        return userLoginRepository.save(userLoginCredential);
    }

    @GetMapping("/{user_id}")
    public UserLoginCredential getUserIDAndPassword(@PathVariable("user_id") Long user_id)
    {
        return userLoginRepository.getAccountIdPassword(user_id);
    }


}
