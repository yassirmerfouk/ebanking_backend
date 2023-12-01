package com.ym.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@NoArgsConstructor @AllArgsConstructor @Setter @Getter @Builder
public class PageAccountOperationsDTO {
    private Long customerId;
    private String fullName;
    private String email;
    private String accountId;
    private Double balance;

    private Integer page;
    private Integer size;
    private Integer totalPages;
    @JsonProperty(value = "operations")
    private List<AccountOperationResponseDTO> accountOperationResponseDTOS;
}
