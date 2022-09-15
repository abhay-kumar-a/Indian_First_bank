package com.example.bank_od.controller;

import com.example.bank_od.model.Bank;
import com.example.bank_od.model.UserAccountDetails;
import com.example.bank_od.repository.UserAccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/account")
public class AccountDetailsController {

    @Autowired
    UserAccountRepo userAccountRepo;

    @GetMapping("/")
    List<UserAccountDetails> userAccountDetails()
    {
        return userAccountRepo.findAll();
    }


    @PostMapping("/save")
    UserAccountDetails save1(@RequestBody Bank bank)
    {   UserAccountDetails accountDetails = new UserAccountDetails();
        accountDetails.setAccount_type(bank.getAccountType());
        accountDetails.setAccount_balance(bank.getUserRegistration().getAccountOpeningMinBalance());
        accountDetails.setAccount_number(bank.getAccountNumber());
        accountDetails.setUser_id(bank.getUserRegistration().getUser_id());
        return userAccountRepo.save(accountDetails);
    }
// --------------------------------------------------------------------------------------------------------------------------------
    @GetMapping("/{user_id}")
    public UserAccountDetails userAccountDetails(@PathVariable("user_id") Long user_id)
    {
        return userAccountRepo.getUserAccountDetails(user_id);
    }
    // --------------------------------------------------------------------------------------------------------------------------------
    UserAccountDetails accountDetails1 = new UserAccountDetails();

    @GetMapping("/balance/{account_id}/{user_id}")
    ResponseEntity<String> getBalance(@PathVariable("account_id") Long account_id ,
                                      @PathVariable("user_id") Long user_id)
    {
        Optional<UserAccountDetails> user_id1 = userAccountRepo.findById(user_id);
        Optional<UserAccountDetails> account_id1 = userAccountRepo.findById(account_id);
        if(user_id1.isPresent() && account_id1.isPresent())
        {
            return ResponseEntity.ok().body("You account Balance is + : "+ userAccountRepo.getBalance(account_id,user_id)) ;
        }
        return ResponseEntity.ok().body("Error: Please check you ID");

    }
    // --------------------------------------------------------------------------------------------------------------------------------
    @GetMapping("/balance/d/{account_id}/{user_id}/{deposit}")
    public ResponseEntity<String> deposit(@PathVariable("account_id") Long account_id ,
                                          @PathVariable("user_id") Long user_id,
                                          @PathVariable("deposit") Integer deposit)
    {
        Integer amount = userAccountRepo.getBalance(account_id,user_id)+deposit;
        Optional<UserAccountDetails> user_id1 = userAccountRepo.findById(user_id);
        Optional<UserAccountDetails> account_id1 = userAccountRepo.findById(account_id);
        if(user_id1.isPresent() && account_id1.isPresent())
        {
            userAccountRepo.deposit(amount,account_id);
            return ResponseEntity.ok().body("amount Deposit successfully");
        }
        return ResponseEntity.ok().body("Amount not deposit ,Please check Id's");
    }


    @GetMapping("/balance/w/{account_id}/{user_id}/{withdrawal}")
    public ResponseEntity<String> withdrawal(@PathVariable("account_id") Long account_id ,
                                             @PathVariable("user_id") Long user_id,
                                             @PathVariable("withdrawal") Integer withdrawal)
    {
        Optional<UserAccountDetails> user_id1 = userAccountRepo.findById(user_id);
        Optional<UserAccountDetails> account_id1 = userAccountRepo.findById(account_id);

        if(userAccountRepo.getBalance(account_id,user_id)<withdrawal){
            return ResponseEntity.ok().body("Insufficient Balance");
        }
        Integer amount = userAccountRepo.getBalance(account_id,user_id)-withdrawal;
        userAccountRepo.withdrawal(amount,account_id);
        return ResponseEntity.ok().body("Given amount "+withdrawal+" successfully withdrawal");
    }

    // --------------------------------------------------------------------------------------------------------------------------------

    // fd_opening
    @GetMapping("/fd_opening/{account_id}/{user_id}")
    public ResponseEntity<String> open_fd(@PathVariable("account_id") Long account_id ,
                                          @PathVariable("user_id") Long user_id)
    {
        Optional<UserAccountDetails> user_id1 = userAccountRepo.findById(user_id);
        Optional<UserAccountDetails> account_id1 = userAccountRepo.findById(account_id);
        if(user_id1.isPresent() && account_id1.isPresent())
        {
            Integer amount = userAccountRepo.getBalance(account_id,user_id) * 50/100;
            // update the saving account balance
            userAccountRepo.withdrawal(amount,account_id);
            //
             userAccountRepo.fixed_deposit_open(account_id,amount);
             return ResponseEntity.ok().body("FD successfully open");
        }
        return ResponseEntity.ok().body("Error");
    }

    // get fd_amount  balance
    @GetMapping("/fd_balance/{account_id}/{user_id}")
   ResponseEntity<String> getFdBalance(@PathVariable("account_id") Long account_id ,
                                       @PathVariable("user_id") Long user_id)
    {
        Optional<UserAccountDetails> user_id1 = userAccountRepo.findById(user_id);
        Optional<UserAccountDetails> account_id1 = userAccountRepo.findById(account_id);
        if(user_id1.isPresent() && account_id1.isPresent())
        {
            return ResponseEntity.ok().body("You account Balance is + : "+ userAccountRepo.getFdBalance(account_id,user_id)) ;
        }
        return ResponseEntity.ok().body("Error: Please check you ID");

    }
    // --------------------------------------------------------------------------------------------------------------------------------
    // od account opening
    @GetMapping("/od_opening/{account_id}/{user_id}")
    public ResponseEntity<String> open_over_draft(@PathVariable("account_id") Long account_id ,
                                                  @PathVariable("user_id") Long user_id)
    {
        Optional<UserAccountDetails> user_id1 = userAccountRepo.findById(user_id);
        Optional<UserAccountDetails> account_id1 = userAccountRepo.findById(account_id);
        if(user_id1.isPresent() && account_id1.isPresent())
        {
            Integer amount = userAccountRepo.getFdBalance(account_id,user_id) * 80/100;
            userAccountRepo.over_draft_open(account_id,amount);

            // Fd account balance update
        //    userAccountRepo.fd_update(getFdBalance(account_id,user_id)-amount,account_id);
            return ResponseEntity.ok().body("Od account successfully open");
        }
        return ResponseEntity.ok().body("Error");
    }

    // get od balance;

    @GetMapping("/od_balance/{account_id}/{user_id}")
    ResponseEntity<String>  getOdBalance(@PathVariable("account_id") Long account_id ,
                         @PathVariable("user_id") Long user_id)
    {
        Optional<UserAccountDetails> user_id1 = userAccountRepo.findById(user_id);
        Optional<UserAccountDetails> account_id1 = userAccountRepo.findById(account_id);
        if(user_id1.isPresent() && account_id1.isPresent())
        {
         return ResponseEntity.ok().body("You account Balance is + : "+ userAccountRepo.getOdBalance(account_id,user_id)) ;
        }
        return ResponseEntity.ok().body("Error: Please check you ID");

    }
    // withdrawal od balance
    @GetMapping("/od_withdrawal/{account_id}/{user_id}/{withdrawal}")
    public ResponseEntity<String> od_withdrawal(@PathVariable("account_id") Long account_id ,
                                             @PathVariable("user_id") Long user_id,
                                             @PathVariable("withdrawal") Integer withdrawal)
    {
        Optional<UserAccountDetails> user_id1 = userAccountRepo.findById(user_id);
        Optional<UserAccountDetails> account_id1 = userAccountRepo.findById(account_id);

        if(userAccountRepo.getOdBalance(account_id,user_id)<withdrawal){
            return ResponseEntity.ok().body("Insufficient Balance");
        }
        Integer amount = userAccountRepo.getOdBalance(account_id,user_id)-withdrawal;
        userAccountRepo.withdrawal_od_amount(amount,account_id);
        return ResponseEntity.ok().body("Given amount "+withdrawal+" successfully withdrawal");
    }

// close FD AND OD accounts


    // fd account closing process
    @GetMapping("/fd_close/{account_id}/{user_id}")
    public ResponseEntity<String> close_fd(@PathVariable("account_id") Long account_id ,
                                            @PathVariable("user_id") Long user_id)
    {
        Optional<UserAccountDetails> user_id1 = userAccountRepo.findById(user_id);
        Optional<UserAccountDetails> account_id1 = userAccountRepo.findById(account_id);

        if(user_id1.isPresent() && account_id1.isPresent()) {
            if (userAccountRepo.close_account(account_id, user_id).equalsIgnoreCase("close")) {
                // check od account is closed
                if (userAccountRepo.get_od_running_status(account_id, user_id)) {
//                    Integer amount = userAccountRepo.getFdBalance(account_id, user_id);
//                    // after close the fd account  remaining balance of fd amount will be deposited in SB account
//                    userAccountRepo.deposit(userAccountRepo.getBalance(account_id, user_id) + amount, account_id);
                    userAccountRepo.fixed_deposit_open(account_id,null);

                    // make fd running status close
                    userAccountRepo.update_fd_running_status(true, account_id);

                    return ResponseEntity.ok().body("your account successfully closed");
                }
                return ResponseEntity.ok().body("please , firstly close the OD_account");
            }
            return ResponseEntity.ok().body("Error ,while closing the fd account");
        }
        return ResponseEntity.ok().body("Please check your id's");

    }

    // get fd closing status
    @GetMapping("/check_fd_close_status/{account_id}/{user_id}")
    ResponseEntity<String> check_fd_close_status(@PathVariable("account_id") Long account_id ,
                                                 @PathVariable("user_id") Long user_id)
    {
        Optional<UserAccountDetails> user_id1 = userAccountRepo.findById(user_id);
        Optional<UserAccountDetails> account_id1 = userAccountRepo.findById(account_id);
        if(user_id1.isPresent() && account_id1.isPresent())
        {
            if(!userAccountRepo.get_od_running_status(account_id,user_id)) {
                return ResponseEntity.ok().body("Fd_account is on HOLD");
            }
            if(userAccountRepo.get_fd_running_status(account_id,user_id))
                 {
                     return ResponseEntity.ok().body("Your fd account has been close");
                 }
            return ResponseEntity.ok().body("fd account still running");

        }
        return ResponseEntity.ok().body("Your id's are wrong .. please check");

    }

    // od_account closing process;
    @GetMapping("/od_close/{account_id}/{user_id}")
    public ResponseEntity<String> close_od(@PathVariable("account_id") Long account_id ,
                                            @PathVariable("user_id") Long user_id)
    {
        Optional<UserAccountDetails> user_id1 = userAccountRepo.findById(user_id);
        Optional<UserAccountDetails> account_id1 = userAccountRepo.findById(account_id);

        if(user_id1.isPresent() && account_id1.isPresent()) {
            if (userAccountRepo.close_account(account_id, user_id).equalsIgnoreCase("close")) {
                Integer amount = null; // set overDraft amount
                userAccountRepo.over_draft_open(account_id, amount);
                userAccountRepo.update_od_running_status(true, account_id);
                // pay to withdrawal amount to the bank , from SB account   , also add 1% interest
                Integer odAmount = (userAccountRepo.getFdBalance(account_id, user_id) * 80 / 100);
                Integer payAmount = odAmount / 100 + (userAccountRepo.getFdBalance(account_id, user_id) * 80 / 100) -
                        userAccountRepo.getOdBalance(account_id, user_id);
                // this payAmount will be paid from SB account to tha bank
                // SB account upDated
                userAccountRepo.withdrawal(payAmount, account_id);

                return ResponseEntity.ok().body("Account successfully closed");
            }
            return ResponseEntity.ok().body("Error while closing the account");
        }
        return ResponseEntity.ok().body("Your id's are wrong .. please check");
    }

    // get od account status
    @GetMapping("/check_od_close_status/{account_id}/{user_id}")
    ResponseEntity<String> check_od_close_status(@PathVariable("account_id") Long account_id ,
                                                 @PathVariable("user_id") Long user_id)
    {
        Optional<UserAccountDetails> user_id1 = userAccountRepo.findById(user_id);
        Optional<UserAccountDetails> account_id1 = userAccountRepo.findById(account_id);
       if(user_id1.isPresent() && account_id1.isPresent())
       {
           if(userAccountRepo.get_od_running_status(account_id,user_id))
           {
               return ResponseEntity.ok().body("Your od account has been close");
           }
           return ResponseEntity.ok().body("od account still running");
       }
        return ResponseEntity.ok().body("Your id's are wrong .. please check");

    }




}

