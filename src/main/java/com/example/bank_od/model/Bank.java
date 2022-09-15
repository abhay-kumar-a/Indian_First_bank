package com.example.bank_od.model;

import javax.persistence.*;

@Entity
public class Bank {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long sb_id;
    @OneToOne @JoinColumn(name = "user_registration_user_id",referencedColumnName = "user_id")
    User_details userDetailsRegistration;
    Long AccountNumber;
    String generateUserId;
    String generateUserPassword;
    String AccountType;
//    Long user_id ;

    public User_details getUserRegistration() {
        return userDetailsRegistration;
    }
//
//    public Long getUser_id() {
//        return user_id;
//    }
//
//    public void setUser_id(Long user_id) {
//        this.user_id = user_id;
//    }

    public Long getSb_id() {
        return sb_id;
    }

    public void setSb_id(Long sb_id) {
        this.sb_id = sb_id;
    }

    public Long getAccountNumber() {
        return AccountNumber;
    }

    public void setAccountNumber(Long accountNumber) {
        AccountNumber = accountNumber;
    }

    public String getGenerateUserId() {
        return generateUserId;
    }

    public void setGenerateUserId(String generateUserId) {
        this.generateUserId = generateUserId;
    }

    public String getGenerateUserPassword() {
        return generateUserPassword;
    }

    public void setGenerateUserPassword(String generateUserPassword) {
        this.generateUserPassword = generateUserPassword;
    }

    public String getAccountType() {
        return AccountType;
    }

    public void setAccountType(String accountType) {
        AccountType = accountType;
    }

    public User_details getUserRegistration(Long user_id) {
        return userDetailsRegistration;
    }

    public void setUserRegistration(User_details userDetailsRegistration) {
        this.userDetailsRegistration = userDetailsRegistration;
    }

}
