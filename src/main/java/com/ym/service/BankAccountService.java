package com.ym.service;

import com.ym.dto.*;
import com.ym.enums.AccountStatus;
import com.ym.enums.OperationType;
import com.ym.exception.AccountNotFoundException;
import com.ym.exception.CustomerNotFoundException;
import com.ym.exception.InsufficientBalanceException;
import com.ym.mapper.AccountOperationMapper;
import com.ym.mapper.BankAccountMapper;
import com.ym.model.*;
import com.ym.repository.AccountOperationRepository;
import com.ym.repository.BankAccountRepository;
import com.ym.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BankAccountService {

    private BankAccountRepository bankAccountRepository;
    private CustomerRepository customerRepository;
    private BankAccountMapper bankAccountMapper;
    private AccountOperationRepository accountOperationRepository;
    private AccountOperationMapper accountOperationMapper;

    public BankAccountResponseDTO addCurrentAccount(CurrentAccountRequestDTO currentAccountRequestDTO) {
        Customer customer = customerRepository.findById(currentAccountRequestDTO.getCustomerId())
                .orElseThrow(
                        () -> new CustomerNotFoundException("Customer " + currentAccountRequestDTO.getCustomerId() + " not found")
                );
        CurrentAccount currentAccount = bankAccountMapper.toCurrentAccount(currentAccountRequestDTO);
        currentAccount.setId(UUID.randomUUID().toString());
        currentAccount.setCreatedAt(LocalDate.now());
        currentAccount.setStatus(AccountStatus.CREATED);
        currentAccount.setCustomer(customer);
        bankAccountRepository.save(currentAccount);
        CurrentAccountResponseDto currentAccountResponseDto =
                bankAccountMapper.toCurrentAccountResponseDTO(currentAccount);
        currentAccountResponseDto.setType(currentAccount.getClass().getSimpleName());
        return currentAccountResponseDto;
    }

    public BankAccountResponseDTO updateCurrentAccount(String id, CurrentAccountRequestDTO currentAccountRequestDTO){
        CurrentAccount currentAccount = (CurrentAccount) bankAccountRepository.findById(id).orElseThrow(
                () -> new AccountNotFoundException("Account " +id+ " not found")
        );
        Customer customer = customerRepository.findById(currentAccountRequestDTO.getCustomerId())
                .orElseThrow(
                        () -> new CustomerNotFoundException("Customer " + currentAccountRequestDTO.getCustomerId() + " not found")
                );
        currentAccount.copy(bankAccountMapper.toCurrentAccount(currentAccountRequestDTO));
        currentAccount.setCustomer(customer);
        bankAccountRepository.save(currentAccount);
        CurrentAccountResponseDto currentAccountResponseDto
                = bankAccountMapper.toCurrentAccountResponseDTO(currentAccount);
        currentAccountResponseDto.setType(currentAccount.getClass().getSimpleName());
        return currentAccountResponseDto;
    }

    public BankAccountResponseDTO addSavingAccount(SavingAccountRequestDTO savingAccountRequestDTO) {
        Customer customer = customerRepository.findById(savingAccountRequestDTO.getCustomerId())
                .orElseThrow(
                        () -> new CustomerNotFoundException("Customer " + savingAccountRequestDTO.getCustomerId() + " not found")
                );
        SavingAccount savingAccount = bankAccountMapper.toSavingAccount(savingAccountRequestDTO);
        savingAccount.setId(UUID.randomUUID().toString());
        savingAccount.setCreatedAt(LocalDate.now());
        savingAccount.setStatus(AccountStatus.CREATED);
        savingAccount.setCustomer(customer);
        bankAccountRepository.save(savingAccount);
        SavingAccountResponseDTO savingAccountResponseDTO =
                bankAccountMapper.toSavingAccountResponseDTO(savingAccount);
        savingAccountResponseDTO.setType(savingAccount.getClass().getSimpleName());
        return savingAccountResponseDTO;
    }

    public BankAccountResponseDTO updateSavingAccount(
            String id,
            SavingAccountRequestDTO savingAccountRequestDTO
    ){
        SavingAccount savingAccount = (SavingAccount) bankAccountRepository.findById(id).orElseThrow(
                () -> new AccountNotFoundException("Account " +id+ " not found")
        );
        Customer customer = customerRepository.findById(savingAccountRequestDTO.getCustomerId())
                .orElseThrow(
                        () -> new CustomerNotFoundException("Customer " + savingAccountRequestDTO.getCustomerId() + " not found")
                );
        savingAccount.copy(bankAccountMapper.toSavingAccount(savingAccountRequestDTO));
        savingAccount.setCustomer(customer);
        bankAccountRepository.save(savingAccount);
        SavingAccountResponseDTO savingAccountResponseDTO
                = bankAccountMapper.toSavingAccountResponseDTO(savingAccount);
        savingAccountResponseDTO.setType(savingAccount.getClass().getSimpleName());
        return savingAccountResponseDTO;
    }

    public List<BankAccountResponseDTO> getBankAccounts() {
        List<BankAccount> bankAccounts = bankAccountRepository.findAll();
        return bankAccounts.stream().map(
                account -> {
                    BankAccountResponseDTO bankAccountResponseDTO = null;
                    if (account instanceof CurrentAccount)
                        bankAccountResponseDTO = bankAccountMapper.toCurrentAccountResponseDTO((CurrentAccount) account);
                    else
                        bankAccountResponseDTO = bankAccountMapper.toSavingAccountResponseDTO((SavingAccount) account);
                    bankAccountResponseDTO.setType(account.getClass().getSimpleName());
                    return bankAccountResponseDTO;
                }
        ).collect(Collectors.toList());
    }

    public BankAccountResponseDTO getAccount(String id){
        BankAccount bankAccount = bankAccountRepository.findById(id)
                .orElseThrow(
                        () -> new AccountNotFoundException("Account " +id+" not found")
                );
        BankAccountResponseDTO bankAccountResponseDTO = null;
        if(bankAccount instanceof CurrentAccount)
            bankAccountResponseDTO = bankAccountMapper.toCurrentAccountResponseDTO((CurrentAccount) bankAccount);
        else
            bankAccountResponseDTO = bankAccountMapper.toSavingAccountResponseDTO((SavingAccount) bankAccount);
        bankAccountResponseDTO.setType(bankAccount.getClass().getSimpleName());
        return bankAccountResponseDTO;
    }

    public List<AccountOperationResponseDTO> getBankAccountOperations(String accountId){
        if(!bankAccountRepository.existsById(accountId))
            throw new AccountNotFoundException("Account " +accountId+ " not found");
        List<AccountOperation> accountOperations =
                accountOperationRepository.findByBankAccountId(accountId);
        return accountOperations.stream().map(
                operation -> accountOperationMapper.toAccountOperationResponseDTO(operation)
        ).collect(Collectors.toList());
    }

    public PageAccountOperationsDTO getPageAccountOperations(String accountId, Integer page, Integer size){
        BankAccount bankAccount = bankAccountRepository.findById(accountId)
                .orElseThrow(
                        () -> new AccountNotFoundException("Account " +accountId+ " not found")
                );
        Page<AccountOperation> accountOperations =
                accountOperationRepository.findByBankAccountId(accountId, PageRequest.of(page,size));
        PageAccountOperationsDTO pageAccountOperationsDTO = PageAccountOperationsDTO.builder()
                .customerId(bankAccount.getCustomer().getId())
                .fullName(bankAccount.getCustomer().getLastName() + " " + bankAccount.getCustomer().getFirstName())
                .email(bankAccount.getCustomer().getEmail())
                .accountId(bankAccount.getId())
                .balance(bankAccount.getBalance())
                .page(page)
                .size(size)
                .build();
        pageAccountOperationsDTO.setTotalPages(accountOperations.getTotalPages());
        pageAccountOperationsDTO.setAccountOperationResponseDTOS(
                accountOperations.getContent().stream().map(
                        operation -> accountOperationMapper.toAccountOperationResponseDTO(operation)
                ).collect(Collectors.toList())
        );
        return pageAccountOperationsDTO;
    }

    public void debit(String accountId, Double amount){
        BankAccount bankAccount = bankAccountRepository.findById(accountId)
                .orElseThrow(
                        () -> new AccountNotFoundException("Account " +accountId+" not found")
                );
        if(amount > bankAccount.getBalance())
            throw new InsufficientBalanceException("Insufficient Balance in this account");
        bankAccount.setBalance(bankAccount.getBalance() - amount);
        AccountOperation accountOperation = AccountOperation.builder()
                .id(UUID.randomUUID().toString())
                .date(LocalDate.now())
                .amount(amount)
                .type(OperationType.DEBIT)
                .bankAccount(bankAccount)
                .build();
        accountOperationRepository.save(accountOperation);
        bankAccountRepository.save(bankAccount);

    }

    public void credit(String accountId, Double amount){
        BankAccount bankAccount = bankAccountRepository.findById(accountId)
                .orElseThrow(
                        () -> new AccountNotFoundException("Account " +accountId+" not found")
                );
        bankAccount.setBalance(bankAccount.getBalance() + amount);
        AccountOperation accountOperation = AccountOperation.builder()
                .id(UUID.randomUUID().toString())
                .date(LocalDate.now())
                .amount(amount)
                .type(OperationType.CREDIT)
                .bankAccount(bankAccount)
                .build();
        accountOperationRepository.save(accountOperation);
        bankAccountRepository.save(bankAccount);
    }

    public void transfer(TransferRequestDTO transferRequestDTO){
        debit(transferRequestDTO.getSourceAccountId(), transferRequestDTO.getAmount());
        credit(transferRequestDTO.getDestAccountId(), transferRequestDTO.getAmount());
    }

    public List<BankAccountResponseDTO> getCustomerBankAccounts(Long customerId){
        if(!customerRepository.existsById(customerId))
            throw new CustomerNotFoundException("Customer "+customerId+" not found");
        List<BankAccount> bankAccounts = bankAccountRepository.findByCustomerId(customerId);
        return bankAccounts.stream().map(
                account -> {
                    BankAccountResponseDTO bankAccountResponseDTO = null;
                    if (account instanceof CurrentAccount)
                        bankAccountResponseDTO = bankAccountMapper.toCurrentAccountResponseDTO((CurrentAccount) account);
                    else
                        bankAccountResponseDTO = bankAccountMapper.toSavingAccountResponseDTO((SavingAccount) account);
                    bankAccountResponseDTO.setType(account.getClass().getSimpleName());
                    return bankAccountResponseDTO;
                }
        ).collect(Collectors.toList());
    }
}
