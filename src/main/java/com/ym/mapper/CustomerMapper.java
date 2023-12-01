package com.ym.mapper;

import com.ym.dto.CustomerRequestDTO;
import com.ym.dto.CustomerResponseDTO;
import com.ym.model.Customer;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

    public Customer toCustomer(CustomerRequestDTO customerRequestDTO){
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerRequestDTO, customer);
        return customer;
    }

    public CustomerResponseDTO toCustomerResponseDTO(Customer customer){
        CustomerResponseDTO customerResponseDTO = new CustomerResponseDTO();
        BeanUtils.copyProperties(customer, customerResponseDTO);
        return customerResponseDTO;
    }

}
