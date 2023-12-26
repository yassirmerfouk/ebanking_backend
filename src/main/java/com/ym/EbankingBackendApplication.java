package com.ym;

import com.ym.enums.AccountStatus;
import com.ym.model.*;
import com.ym.repository.*;
import com.ym.service.BankAccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

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
            BankAccountService bankAccountService,
            UserRepository userRepository,
            RoleRepository roleRepository,
            PasswordEncoder passwordEncoder
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
                Role role1 = Role.builder().name("ADMIN").build();
                Role role2 = Role.builder().name("USER").build();
                roleRepository.save(role1);
                roleRepository.save(role2);
                User user1 = User.builder().username("admin").password(passwordEncoder.encode("123456")).roles(Arrays.asList(role1,role2)).build();
                User user2 = User.builder().username("user").password(passwordEncoder.encode("123456")).roles(Arrays.asList(role2)).build();
                userRepository.save(user1);
                userRepository.save(user2);
            }

        };
    }
}
