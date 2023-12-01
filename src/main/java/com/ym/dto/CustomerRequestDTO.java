package com.ym.dto;

import lombok.*;

@NoArgsConstructor @AllArgsConstructor @Setter @Getter @Builder
public class CustomerRequestDTO {
    private String firstName;
    private String lastName;
    private String email;
}
