package com.ym.repository;

import com.ym.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    /*boolean existsById(Long id);*/
   @Query("select c from Customer c where c.lastName like %:keyword%")
    List<Customer> findByLastNameContains(@Param("keyword") String keyword);
}
