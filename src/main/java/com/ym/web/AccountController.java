package com.ym.web;

import com.ym.dto.*;
import com.ym.service.BankAccountService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
@AllArgsConstructor
public class AccountController {

    private BankAccountService bankAccountService;

    @PostMapping("/currents")
    BankAccountResponseDTO addCurrentAccount(@RequestBody CurrentAccountRequestDTO currentAccountRequestDTO){
        return bankAccountService.addCurrentAccount(currentAccountRequestDTO);
    }

    @PostMapping("/savings")
    BankAccountResponseDTO addSavingAccount(@RequestBody SavingAccountRequestDTO savingAccountRequestDTO){
        return bankAccountService.addSavingAccount(savingAccountRequestDTO);
    }

    @GetMapping
    List<BankAccountResponseDTO> getAccounts(){
        return bankAccountService.getBankAccounts();
    }

    @GetMapping("/{id}")
    BankAccountResponseDTO getAccount(@PathVariable String id){
        return bankAccountService.getAccount(id);
    }

    @PostMapping("/transfer")
    public void transfer(@RequestBody TransferRequestDTO transferRequestDTO){
        bankAccountService.transfer(transferRequestDTO);
    }

    @GetMapping("/{accountId}/operations")
    public List<AccountOperationResponseDTO> getOperations(@PathVariable String accountId){
        return bankAccountService.getBankAccountOperations(accountId);
    }

    @GetMapping("/{accountId}/operations/page")
    public PageAccountOperationsDTO getPageOperations(
            @PathVariable String accountId,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "5") Integer size
    ){
        return bankAccountService.getPageAccountOperations(accountId, page,size);
    }
}
