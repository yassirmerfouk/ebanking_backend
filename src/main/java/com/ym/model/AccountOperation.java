package com.ym.model;

import com.ym.enums.OperationType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor @AllArgsConstructor @Setter @Getter @Builder
public class AccountOperation {

    @Id
    private String id;
    private LocalDateTime date;
    private Double amount;
    @Enumerated(value = EnumType.STRING)
    private OperationType type;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private BankAccount bankAccount;
}
