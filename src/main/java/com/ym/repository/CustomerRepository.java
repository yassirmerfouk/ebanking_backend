package com.ym.repository;

import com.ym.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    /*boolean existsById(Long id);*/
}
