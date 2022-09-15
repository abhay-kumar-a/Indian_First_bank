package com.example.bank_od.model;

import javax.persistence.*;

@Entity
@Table(name = "user_account")
public class UserAccountDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long account_id;
    Long user_id;
    Long account_number;
    String account_type;
    Integer account_balance;
    Integer deposit;
    Integer withdrawal;

    Integer fixed_deposit_amount;

    Integer over_draft_amount;
    Float year;

    String close_bank="close";
    Boolean fd_running_status=false;
    Boolean od_running_status=false;

    public Boolean getFd_running_status() {
        return fd_running_status;
    }

    public void setFd_running_status(Boolean fd_running_status) {
        this.fd_running_status = fd_running_status;
    }

    public Boolean getOd_running_status() {
        return od_running_status;
    }

    public void setOd_running_status(Boolean od_running_status) {
        this.od_running_status = od_running_status;
    }

    public String getClose_bank() {
        return close_bank;
    }

    public void setClose_bank(String close_bank) {
        this.close_bank = close_bank;
    }

    public Integer getOver_draft_amount() {
        return over_draft_amount;
    }

    public void setOver_draft_amount(Integer over_draft_amount) {
        this.over_draft_amount = over_draft_amount;
    }
    public Float getYear() {
        return year;
    }

    public void setYear(Float year) {
        this.year = year;
    }

    public Integer getFixed_deposit_amount() {
        return fixed_deposit_amount;
    }

    public void setFixed_deposit_amount(Integer fixed_deposit_amount) {
        this.fixed_deposit_amount = fixed_deposit_amount;
    }

    public Integer getDeposit() {
        return deposit;
    }

    public void setDeposit(Integer deposit) {
        this.deposit = deposit;
    }

    public Integer getWithdrawal() {
        return withdrawal;
    }

    public void setWithdrawal(Integer withdrawal) {
        this.withdrawal = withdrawal;
    }

    public Long getAccount_id() {
        return account_id;
    }

    public void setAccount_id(Long account_id) {
        this.account_id = account_id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Long getAccount_number() {
        return account_number;
    }

    public void setAccount_number(Long account_number) {
        this.account_number = account_number;
    }

    public String getAccount_type() {
        return account_type;
    }

    public void setAccount_type(String account_type) {
        this.account_type = account_type;
    }

    public Integer getAccount_balance() {
        return account_balance;
    }

    public void setAccount_balance(Integer account_balance) {
        this.account_balance = account_balance;
    }

    public Integer checkBalance()
    {
        return this.account_balance;
    }



}
