package com.ym.repository;

import com.ym.model.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountRepository extends JpaRepository<BankAccount, String> {
    boolean existsByCustomerId(Long customerId);
}
