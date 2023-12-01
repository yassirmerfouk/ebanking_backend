package com.ym.dto;

import com.ym.enums.AccountStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor @AllArgsConstructor @Setter @Getter
public abstract class BankAccountRequestDTO {
    protected Double balance;
    protected AccountStatus status;
    protected Long customerId;
}
