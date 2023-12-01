package com.ym.repository;

import com.ym.model.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BankAccountRepository extends JpaRepository<BankAccount, String> {
    boolean existsByCustomerId(Long customerId);
    List<BankAccount> findByCustomerId(Long cusstomerId);
}
