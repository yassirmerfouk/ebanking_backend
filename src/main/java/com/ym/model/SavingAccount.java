package com.ym.model;

import com.ym.enums.AccountStatus;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;

import java.time.LocalDate;

@Entity
@DiscriminatorValue(value = "SAVING")
@NoArgsConstructor @Setter @Getter
public class SavingAccount extends BankAccount{
    private Double interestRate;

    @Builder
    public SavingAccount(String id, LocalDate createdAt, Double balance, AccountStatus status, Customer customer, Double interestRate) {
        super(id, createdAt, balance, status, customer);
        this.interestRate = interestRate;
    }

    @Override
    public void copy(BankAccount bankAccount){
        super.copy(bankAccount);
        SavingAccount savingAccount = (SavingAccount) bankAccount;
        if(savingAccount.interestRate != null) this.interestRate = savingAccount.interestRate;
    }
}
