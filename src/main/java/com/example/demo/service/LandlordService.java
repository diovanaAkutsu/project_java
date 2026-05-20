package com.example.demo.service;

import com.example.demo.domain.dto.LandlordRequestDTO;
import com.example.demo.domain.dto.LandlordResponseDTO;
import com.example.demo.domain.entity.Landlord;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.LandlordRepository;
import com.example.demo.repository.TenantRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LandlordService {

    private final TenantRepository tenantRepository;
    private final LandlordRepository landlordRepository;

    public LandlordService(LandlordRepository landlordRepository, TenantRepository tenantRepository) {
        this.tenantRepository = tenantRepository;
        this.landlordRepository = landlordRepository;
    }

    public LandlordResponseDTO create(LandlordRequestDTO request) {
        Landlord landlord = new Landlord();
        landlord.setName(request.getName());
        landlord.setEmail(request.getEmail());

        Landlord savedLandlord = landlordRepository.save(landlord);

        return toResponse(savedLandlord);
    }

    // Aqui ele criou uma lista pra transformar os dados dentro do repository em dto
    public List<LandlordResponseDTO> findAll() {
        List<Landlord> tenants = landlordRepository.findAll();
        List<LandlordResponseDTO> responses = new ArrayList<>();
        for (Landlord landlord : tenants) {
            responses.add(toResponse(landlord));
        }
        return responses;
    }

    // Busca um proprietário específico pelo ID e o converte em DTO,
    // lançando uma exceção se não for encontrado
    public LandlordResponseDTO findById(Long id) {
        Landlord landlord = landlordRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Proprietário não encontrado com id: " + id));

        return toResponse(landlord);
    }

    public LandlordResponseDTO update(Long id, LandlordRequestDTO request) {
        Landlord landlord = landlordRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Proprietário não encontrado com id: " + id));

        landlord.setName(request.getName());
        landlord.setEmail(request.getEmail());

        Landlord updatedLandlord = landlordRepository.save(landlord);

        return toResponse(updatedLandlord);
    }

    private LandlordResponseDTO toResponse(Landlord landlord) {
        return new LandlordResponseDTO(
                landlord.getId(),
                landlord.getName(),
                landlord.getEmail()
        );
    }
}