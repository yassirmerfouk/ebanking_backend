package com.ym.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ym.enums.OperationType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor @AllArgsConstructor @Setter @Getter
public class AccountOperationResponseDTO {
    private String id;
    private LocalDate date;
    private Double amount;
    private OperationType type;
/*    @JsonProperty(value = "account")
    private BankAccountResponseDTO bankAccountResponseDTO;*/
}
