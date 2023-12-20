package com.ym.web;

import com.ym.dto.BankAccountResponseDTO;
import com.ym.dto.CustomerRequestDTO;
import com.ym.dto.CustomerResponseDTO;
import com.ym.service.BankAccountService;
import com.ym.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
@CrossOrigin
@AllArgsConstructor
public class CustomerController {

    private CustomerService customerService;
    private BankAccountService bankAccountService;

    @PostMapping
    public CustomerResponseDTO addCustomer(@RequestBody CustomerRequestDTO customerRequestDTO){
        return customerService.addCustomer(customerRequestDTO);
    }

    @PutMapping("/{id}")
    public CustomerResponseDTO updateCustomer(@PathVariable Long id,@RequestBody CustomerRequestDTO customerRequestDTO){
        return customerService.updateCustomer(id,customerRequestDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable Long id){
        customerService.deleteCustomer(id);
    }

    @GetMapping("/{id}")
    public CustomerResponseDTO getCustomer(@PathVariable Long id){
        return customerService.getCustomer(id);
    }

    @GetMapping
    public List<CustomerResponseDTO> getCustomer(){
        return customerService.getCustomers();
    }

    @GetMapping("/{id}/accounts")
    public List<BankAccountResponseDTO> getCustomerAccounts(@PathVariable Long id){
        return bankAccountService.getCustomerBankAccounts(id);
    }

    @GetMapping("/search/lastname")
    public List<CustomerResponseDTO> getCustomersByLastName(@RequestParam String keyword){
        return customerService.getCustomersByLastName(keyword);
    }
}
