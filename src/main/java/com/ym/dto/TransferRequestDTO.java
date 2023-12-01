package com.ym.dto;

import lombok.*;

@NoArgsConstructor @AllArgsConstructor @Setter @Getter @Builder
public class TransferRequestDTO {
    private String sourceAccountId;
    private String destAccountId;
    private Double amount;
}
