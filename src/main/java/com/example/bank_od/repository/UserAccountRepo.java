package com.example.bank_od.repository;

import com.example.bank_od.model.UserAccountDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface UserAccountRepo extends JpaRepository<UserAccountDetails,Long> {

    @Query(value = "select p from UserAccountDetails as p where user_id=:user_id")
    UserAccountDetails getUserAccountDetails(@Param("user_id") Long user_id);

    @Modifying
    @Query(value ="UPDATE user_account SET account_balance = :amount WHERE account_id =:account_id" , nativeQuery = true)
    @Transactional
    void deposit(@Param("amount") Integer amount, @Param("account_id") Long account_id);

    @Modifying
    @Query(value ="UPDATE user_account SET account_balance = :amount WHERE account_id =:account_id" , nativeQuery = true)
    @Transactional
    void withdrawal(@Param("amount") Integer amount, @Param("account_id") Long account_id);

    @Query(value = "select account_balance from user_account WHERE account_id =:account_id and user_id =:user_id ",nativeQuery = true)
    Integer getBalance(@Param("account_id") Long account_id,@Param("user_id") Long user_id);

    // open fd account..
    @Modifying
    @Query(value ="UPDATE user_account SET fixed_deposit_amount = :amount WHERE account_id =:account_id" , nativeQuery = true)
    @Transactional
    void fixed_deposit_open( @Param("account_id") Long account_id,@Param("amount") Integer amount );

    // get fd balance
    @Query(value = "select fixed_deposit_amount from user_account WHERE account_id =:account_id and user_id =:user_id ",nativeQuery = true)
    Integer getFdBalance(@Param("account_id") Long account_id,@Param("user_id") Long user_id);
    @Modifying
    @Query(value ="UPDATE user_account SET fixed_deposit_amount = :amount WHERE account_id =:account_id" , nativeQuery = true)
    @Transactional
    void fd_update(@Param("amount") Integer amount, @Param("account_id") Long account_id);

    // get fd_balance and year validity
//    @Query(value = "select fixed_deposit_amount,year from user_account WHERE account_id =:account_id and user_id =:user_id ",nativeQuery = true)
//    Integer getFdBalanceAndYear(@Param("account_id") Long account_id,@Param("user_id") Long user_id);

    // open_od account
    @Modifying
    @Query(value ="UPDATE user_account SET over_draft_amount = :amount WHERE account_id =:account_id" , nativeQuery = true)
    @Transactional
    void over_draft_open( @Param("account_id") Long account_id,@Param("amount") Integer amount);

    // get_over_draft_amount
    @Query(value = "select over_draft_amount from user_account WHERE account_id =:account_id and user_id =:user_id ",nativeQuery = true)
    Integer getOdBalance(@Param("account_id") Long account_id,@Param("user_id") Long user_id);

    // withdrawal od amount..
    @Modifying
    @Query(value ="UPDATE user_account SET over_draft_amount = :amount WHERE account_id =:account_id" , nativeQuery = true)
    @Transactional
    void withdrawal_od_amount(@Param("amount") Integer amount, @Param("account_id") Long account_id);


    // close accounts
    @Query(value = "select close_bank from user_account WHERE account_id =:account_id and user_id =:user_id ",nativeQuery = true)
    String close_account(@Param("account_id") Long account_id,@Param("user_id") Long user_id);

    @Modifying
    @Query(value ="UPDATE user_account SET fd_running_status = :fd_running_status WHERE account_id =:account_id" , nativeQuery = true)
    @Transactional
    void update_fd_running_status(@Param("fd_running_status") Boolean fd_running_status, @Param("account_id") Long account_id);

    @Query(value = "select fd_running_status from user_account WHERE account_id =:account_id and user_id =:user_id ",nativeQuery = true)
    Boolean get_fd_running_status(@Param("account_id") Long account_id,@Param("user_id") Long user_id);

    @Modifying
    @Query(value ="UPDATE user_account SET od_running_status = :od_running_status WHERE account_id =:account_id" , nativeQuery = true)
    @Transactional
    void update_od_running_status(@Param("od_running_status") Boolean od_running_status, @Param("account_id") Long account_id);

    @Query(value = "select od_running_status from user_account WHERE account_id =:account_id and user_id =:user_id ",nativeQuery = true)
    Boolean get_od_running_status(@Param("account_id") Long account_id,@Param("user_id") Long user_id);
}
