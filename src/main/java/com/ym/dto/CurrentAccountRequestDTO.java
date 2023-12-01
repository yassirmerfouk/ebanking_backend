package com.ym.dto;

import com.ym.enums.AccountStatus;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor @AllArgsConstructor @Setter @Getter
public class CurrentAccountRequestDTO extends BankAccountRequestDTO {

    private Double overDraft;

    @Builder
    public CurrentAccountRequestDTO(Double balance, AccountStatus status, Long customerId, Double overDraft) {
        super(balance, status, customerId);
        this.overDraft = overDraft;
    }
}
