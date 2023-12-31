package com.ym.model;

import com.ym.enums.AccountStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING,length = 10)
@NoArgsConstructor @AllArgsConstructor @Setter @Getter
public class BankAccount {

    @Id
    protected String id;
    protected LocalDate createdAt;
    protected Double balance;
    @Enumerated(value = EnumType.STRING)
    protected AccountStatus status;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    protected Customer customer;

    public void copy(BankAccount bankAccount){
        if(bankAccount.id != null) this.id = bankAccount.id;
        if(bankAccount.createdAt != null) this.createdAt = bankAccount.createdAt;
        if(bankAccount.balance != null) this.balance = bankAccount.balance;
        if(bankAccount.status != this.status) this.status = bankAccount.status;
        // customer later
    }

}
