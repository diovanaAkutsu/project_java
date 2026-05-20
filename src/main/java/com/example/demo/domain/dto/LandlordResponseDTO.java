package com.example.demo.domain.dto;

import com.example.demo.domain.entity.Tenant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LandlordResponseDTO {

    private Long id;
    private String name;
    private String email;

}
