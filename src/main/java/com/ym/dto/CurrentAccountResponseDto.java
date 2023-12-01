package com.ym.dto;

import com.ym.enums.AccountStatus;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor @AllArgsConstructor @Setter @Getter
public class CurrentAccountResponseDto extends BankAccountResponseDTO{

    private Double overDraft;

    @Builder

    public CurrentAccountResponseDto(String id, LocalDate createdAt, Double balance, AccountStatus status, String type, CustomerResponseDTO customerResponseDTO, Double overDraft) {
        super(id, createdAt, balance, status, type, customerResponseDTO);
        this.overDraft = overDraft;
    }
}
