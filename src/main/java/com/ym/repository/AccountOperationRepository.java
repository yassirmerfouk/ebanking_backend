package com.ym.repository;

import com.ym.model.AccountOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountOperationRepository extends JpaRepository<AccountOperation, String> {
    List<AccountOperation> findByBankAccountId(String bankAccountId);
    Page<AccountOperation> findByBankAccountId(String bankAccountId, Pageable pageable);
}
