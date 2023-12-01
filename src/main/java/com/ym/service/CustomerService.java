package com.ym.service;

import com.ym.dto.CustomerRequestDTO;
import com.ym.dto.CustomerResponseDTO;
import com.ym.exception.CustomerCannotDeleted;
import com.ym.exception.CustomerNotFoundException;
import com.ym.mapper.CustomerMapper;
import com.ym.model.Customer;
import com.ym.repository.BankAccountRepository;
import com.ym.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CustomerService {

    private CustomerRepository customerRepository;
    private CustomerMapper customerMapper;
    private BankAccountRepository bankAccountRepository;

    public CustomerResponseDTO addCustomer(CustomerRequestDTO customerRequestDTO){
        Customer customer = customerMapper.toCustomer(customerRequestDTO);
        customerRepository.save(customer);
        return customerMapper.toCustomerResponseDTO(customer);
    }

    public CustomerResponseDTO updateCustomer(Long id, CustomerRequestDTO customerRequestDTO){
        if(!customerRepository.existsById(id))
            throw new CustomerNotFoundException("Customer " +id+ " not found");
        Customer customer = customerMapper.toCustomer(customerRequestDTO);
        customer.setId(id);
        customerRepository.save(customer);
        return customerMapper.toCustomerResponseDTO(customer);
    }

    public CustomerResponseDTO getCustomer(Long id){
        Customer customer = customerRepository.findById(id).orElseThrow(
                () -> new CustomerNotFoundException("Customer " +id+ " not found")
        );
        return customerMapper.toCustomerResponseDTO(customer);
    }

    public List<CustomerResponseDTO> getCustomers(){
        List<Customer> customers = customerRepository.findAll();
        return customers.stream().map(
                customer -> customerMapper.toCustomerResponseDTO(customer)
        ).collect(Collectors.toList());
    }

    public void deleteCustomer(Long id){
        Customer customer = customerRepository.findById(id).orElseThrow(
                () -> new CustomerNotFoundException("Customer " +id+ " not found")
        );
        if(bankAccountRepository.existsByCustomerId(id))
            throw new CustomerCannotDeleted("Can not delete customern has accounts");
        customerRepository.delete(customer);
    }
}
