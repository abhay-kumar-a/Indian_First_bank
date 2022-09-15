package com.example.bank_od.repository;

import com.example.bank_od.model.UserLoginCredential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserLoginRepository  extends JpaRepository<UserLoginCredential,Long> {

    @Query(value = "select p from UserLoginCredential as p where user_id=:user_id")
    UserLoginCredential getAccountIdPassword(@Param("user_id") Long user_id);
}
