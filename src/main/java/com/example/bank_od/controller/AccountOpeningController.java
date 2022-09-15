package com.example.bank_od.controller;

import com.example.bank_od.model.Bank;
import com.example.bank_od.repository.SavingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bank")
public class AccountOpeningController {

    @Autowired
    SavingRepo savingRepo;

    @GetMapping("/userDetails")
    List<Bank> savingAccountOpenList()
    {
        return savingRepo.findAll();
    }

    @PostMapping("saving_account/create")
     public ResponseEntity accountOpen(@RequestBody Bank bank)
    {
        savingRepo.save(bank);
        return new  ResponseEntity<>("CREATED", HttpStatus.CREATED);
    }


}
