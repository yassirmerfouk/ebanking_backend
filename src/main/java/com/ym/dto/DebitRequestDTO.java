package com.ym.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor @Setter @Getter
public class DebitRequestDTO {
    private String accountId;
    private Double amount;
}
