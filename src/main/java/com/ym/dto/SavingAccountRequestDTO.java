package com.ym.dto;

import com.ym.enums.AccountStatus;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor @AllArgsConstructor @Setter @Getter
public class SavingAccountRequestDTO extends BankAccountRequestDTO{

    private Double interestRate;

    @Builder
    public SavingAccountRequestDTO(Double balance, AccountStatus status, Long customerId, Double interestRate) {
        super(balance, status, customerId);
        this.interestRate = interestRate;
    }
}
