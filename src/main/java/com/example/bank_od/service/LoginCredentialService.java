package com.example.bank_od.service;

import com.example.bank_od.model.UserLoginCredential;
import com.example.bank_od.repository.UserLoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginCredentialService {

    @Autowired
    UserLoginRepository userLoginRepository;

    public UserLoginCredential userLoginCredential(Long user_id)
    {
        return  userLoginRepository.getAccountIdPassword(user_id);
    }
}
