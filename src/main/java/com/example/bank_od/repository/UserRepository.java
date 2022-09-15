package com.example.bank_od.repository;

import com.example.bank_od.model.User_details;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User_details, Long> {
}
