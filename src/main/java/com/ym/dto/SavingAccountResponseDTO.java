package com.ym.dto;

import com.ym.enums.AccountStatus;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor @AllArgsConstructor @Setter @Getter
public class SavingAccountResponseDTO extends BankAccountResponseDTO{

    private Double interestRate;

    @Builder
    public SavingAccountResponseDTO(String id, LocalDate createdAt, Double balance, AccountStatus status, String type, CustomerResponseDTO customerResponseDTO, Double interestRate) {
        super(id, createdAt, balance, status, type, customerResponseDTO);
        this.interestRate = interestRate;
    }
}
