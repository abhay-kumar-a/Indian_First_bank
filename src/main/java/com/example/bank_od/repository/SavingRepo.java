package com.example.bank_od.repository;

import com.example.bank_od.model.Bank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SavingRepo extends JpaRepository<Bank,Long> {
}
