package com.example.demo.service;

import com.example.demo.domain.dto.TenantRequestDTO;
import com.example.demo.domain.dto.TenantResponseDTO;
import com.example.demo.domain.entity.Landlord;
import com.example.demo.domain.entity.Tenant;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.LandlordRepository;
import com.example.demo.repository.TenantRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TenantService {

    private final TenantRepository tenantRepository;
    private final LandlordRepository landlordRepository;

    public TenantService(TenantRepository tenantRepository, LandlordRepository landlordRepository) {
        this.tenantRepository = tenantRepository;
        this.landlordRepository = landlordRepository;
    }

    public TenantResponseDTO create(TenantRequestDTO request) {
        Landlord landlord = landlordRepository.findById(request.getLandlordId())
                .orElseThrow(() -> new ResourceNotFoundException("Locador não encontrado com id: " + request.getLandlordId()));

        Tenant tenant = new Tenant();
        tenant.setName(request.getName());
        tenant.setEmail(request.getEmail());
        tenant.setPhoneNumber(request.getPhoneNumber());
        tenant.setLandlord(landlord);

        Tenant savedTenant = tenantRepository.save(tenant);

        return toResponse(savedTenant);
    }

    // Aqui ele criou uma lista pra transformar os dados dentro do repository em dto
    public List<TenantResponseDTO> findAll() {
        List<Tenant> tenants = tenantRepository.findAll();
        List<TenantResponseDTO> responses = new ArrayList<>();
        for (Tenant tenant : tenants) {
            responses.add(toResponse(tenant));
        }
        return responses;
    }

    // Busca um inquilino específico pelo ID e o converte em DTO,
    // lançando uma exceção se não for encontrado
    public TenantResponseDTO findById(Long id) {
        Tenant tenant = tenantRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Inquilino não encontrado com id: " + id));

        return toResponse(tenant);
    }

    // Busca todos os inquilinos associados a um proprietário específico
    // e os converte em uma lista de DTOs usando um loop(for)
    public List<TenantResponseDTO> findByLandlordId(Long landlordId) {
        List<Tenant> tenants = tenantRepository.findByLandlordId(landlordId);
        List<TenantResponseDTO> responses = new ArrayList<>();
        for (Tenant tenant : tenants) {
            responses.add(toResponse(tenant));
        }
        return responses;
    }

//  Atualiza um inquilino existente com dados de um DTO, validando a existência do inquilino
//  e do proprietário, e retorna o DTO atualizado
    public TenantResponseDTO update(Long id, TenantRequestDTO request) {
        Tenant tenant = tenantRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Inquilino não encontrado com id: " + id));

        Landlord landlord = landlordRepository.findById(request.getLandlordId())
                .orElseThrow(() -> new ResourceNotFoundException("Locador não encontrado com id: " + request.getLandlordId()));

        tenant.setName(request.getName());
        tenant.setEmail(request.getEmail());
        tenant.setPhoneNumber(request.getPhoneNumber());
        tenant.setLandlord(landlord);

        Tenant updatedTenant = tenantRepository.save(tenant);

        return toResponse(updatedTenant);
    }

//  Remove um inquilino do banco pelo ID, validando se ele existe antes de deletar
    public void delete(Long id) {
        Tenant tenant = tenantRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Inquilino não encontrado com id: " + id));

        tenantRepository.delete(tenant);
    }

    private TenantResponseDTO toResponse(Tenant tenant) {
        return new TenantResponseDTO(
                tenant.getId(),
                tenant.getName(),
                tenant.getEmail(),
                tenant.getPhoneNumber(),
                tenant.getLandlord().getId(),
                tenant.getLandlord().getName()
        );
    }

}