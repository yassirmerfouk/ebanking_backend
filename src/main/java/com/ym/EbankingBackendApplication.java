package com.ym;

import com.ym.enums.AccountStatus;
import com.ym.model.AccountOperation;
import com.ym.model.CurrentAccount;
import com.ym.model.Customer;
import com.ym.model.SavingAccount;
import com.ym.repository.AccountOperationRepository;
import com.ym.repository.BankAccountRepository;
import com.ym.repository.CustomerRepository;
import com.ym.service.BankAccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.UUID;

@SpringBootApplication
public class EbankingBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(EbankingBackendApplication.class, args);
    }


    @Bean
    public CommandLineRunner commandLineRunner(
            CustomerRepository customerRepository,
            BankAccountRepository bankAccountRepository,
            AccountOperationRepository accountOperationRepository,
            BankAccountService bankAccountService
    ){
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                Customer customer1 = Customer.builder()
                        .firstName("yassir")
                        .lastName("merfouk")
                        .email("yassirmerfouk@gmail.com")
                        .build();
                Customer customer2 = Customer.builder()
                        .firstName("ym")
                        .lastName("ym")
                        .email("ym@gmail.com")
                        .build();
                customerRepository.save(customer1);
                customerRepository.save(customer2);
                Arrays.asList(customer1, customer2).forEach(
                        customer -> {
                            SavingAccount savingAccount = SavingAccount.builder()
                                    .id(UUID.randomUUID().toString())
                                    .createdAt(LocalDate.now())
                                    .balance(1000 + Math.random() * 90000)
                                    .status(AccountStatus.CREATED)
                                    .interestRate(10.0)
                                    .customer(customer)
                                    .build();
                            CurrentAccount currentAccount = CurrentAccount.builder()
                                    .id(UUID.randomUUID().toString())
                                    .createdAt(LocalDate.now())
                                    .balance(1000 + Math.random() * 90000)
                                    .status(AccountStatus.CREATED)
                                    .overDraft(1000.0)
                                    .customer(customer)
                                    .build();
                            bankAccountRepository.save(savingAccount);
                            bankAccountRepository.save(currentAccount);
                        }
                );

                    bankAccountService.getBankAccounts().forEach(
                            account -> {
                                for (int i = 0; i<10;i++) {
                                    bankAccountService.debit(account.getId(), 1000.0);
                                    bankAccountService.credit(account.getId(), 1000.0);
                                }
                            }
                    );
            }
        };
    }
}
