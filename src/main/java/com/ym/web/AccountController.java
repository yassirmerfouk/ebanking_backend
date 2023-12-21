package com.ym.web;

import com.ym.dto.*;
import com.ym.service.BankAccountService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
@CrossOrigin
@AllArgsConstructor
public class AccountController {

    private BankAccountService bankAccountService;

    @PostMapping("/currents")
    BankAccountResponseDTO addCurrentAccount(@RequestBody CurrentAccountRequestDTO currentAccountRequestDTO){
        return bankAccountService.addCurrentAccount(currentAccountRequestDTO);
    }

    @PutMapping("/currents/{id}")
    BankAccountResponseDTO updateCurrentAccount(
            @PathVariable String id,
            @RequestBody CurrentAccountRequestDTO currentAccountRequestDTO
    ){
        return bankAccountService.updateCurrentAccount(id, currentAccountRequestDTO);
    }

    @PostMapping("/savings")
    BankAccountResponseDTO addSavingAccount(@RequestBody SavingAccountRequestDTO savingAccountRequestDTO){
        return bankAccountService.addSavingAccount(savingAccountRequestDTO);
    }

    @PutMapping("/savings/{id}")
    BankAccountResponseDTO updateSavingAccount(
            @PathVariable String id,
            @RequestBody SavingAccountRequestDTO savingAccountRequestDTO
    ){
        return bankAccountService.updateSavingAccount(id,savingAccountRequestDTO);
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

    @PostMapping("/debit")
    public void debit(@RequestBody DebitRequestDTO debitRequestDTO){
        bankAccountService.debit(debitRequestDTO.getAccountId(), debitRequestDTO.getAmount());
    }

    @PostMapping("/credit")
    public void credit(@RequestBody CreditRequestDTO creditRequestDTO){
        bankAccountService.credit(creditRequestDTO.getAccountId(), creditRequestDTO.getAmount());
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
