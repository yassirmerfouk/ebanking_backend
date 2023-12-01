package com.ym.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ym.enums.AccountStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor @AllArgsConstructor @Setter @Getter
public class BankAccountResponseDTO {
    protected String id;
    protected LocalDate createdAt;
    protected Double balance;
    protected AccountStatus status;
    protected String type;
    @JsonProperty(value = "customer")
    protected CustomerResponseDTO customerResponseDTO;
}
