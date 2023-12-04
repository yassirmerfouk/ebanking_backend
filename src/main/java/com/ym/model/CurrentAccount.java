package com.ym.model;


import com.ym.enums.AccountStatus;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;

import java.time.LocalDate;

@Entity
@DiscriminatorValue(value = "CURRENT")
@NoArgsConstructor @AllArgsConstructor @Setter @Getter
public class CurrentAccount extends BankAccount{

    private Double overDraft;

    @Builder
    public CurrentAccount(String id, LocalDate createdAt, Double balance, AccountStatus status, Customer customer, Double overDraft) {
        super(id, createdAt, balance, status, customer);
        this.overDraft = overDraft;
    }

    @Override
    public void copy(BankAccount bankAccount){
        super.copy(bankAccount);
        CurrentAccount currentAccount = (CurrentAccount) bankAccount;
        if(currentAccount.overDraft != null) this.overDraft = currentAccount.overDraft;
    }
}
