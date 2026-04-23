package com.example.demo.domain.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TenantResponseDTO {

    private Long id;
    private String name;
    private String email;
    private String phoneNumber;
    private Long landlordId;
    private String landlordName;

}
