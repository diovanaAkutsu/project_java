package com.example.demo.controller;

import com.example.demo.domain.dto.LandlordRequestDTO;
import com.example.demo.domain.dto.LandlordResponseDTO;
import com.example.demo.domain.dto.TenantResponseDTO;
import com.example.demo.service.LandlordService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/landlord")

public class LandlordController {

    private final LandlordService landlordService;

    public LandlordController(LandlordService landlordService) {
        this.landlordService = landlordService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LandlordResponseDTO create(@Valid @RequestBody LandlordRequestDTO request) {
        return landlordService.create(request);
    }

    @PutMapping("/{id}")
    public LandlordResponseDTO update(@PathVariable Long id,
                                      @Valid @RequestBody LandlordRequestDTO request) {
        return landlordService.update(id, request);
    }

    @GetMapping
    public List<LandlordResponseDTO> findAll() {
        return landlordService.findAll();
    }

}
