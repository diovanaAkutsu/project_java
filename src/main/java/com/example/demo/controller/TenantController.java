package com.example.demo.controller;

import com.example.demo.domain.dto.TenantRequestDTO;
import com.example.demo.domain.dto.TenantResponseDTO;
import com.example.demo.service.TenantService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tenants")
public class TenantController {

    private final TenantService tenantService;

    public TenantController(TenantService tenantService) {
        this.tenantService = tenantService;
    }

    // Aqui ele cria um novo inquilino
    // Fluxo:
    // Recebe o que o cliente mandou em formato DTO
    // @valid valida as anotações dentro do DTO
    // @RequestBody transforma o JSON recebido em um objeto Java
    // Chama o serviço tenantService para criar o inquilino e retorna a resposta
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TenantResponseDTO create(@Valid @RequestBody TenantRequestDTO request) {
        return tenantService.create(request);
    }

    // Lista todos os inquilinos
    // Fluxo:
    // Chama o serviço tenantService para buscar todos os inquilinos e retorna a lista
    @GetMapping
    public List<TenantResponseDTO> findAll() {
        return tenantService.findAll();
    }

    // Busca um inquilino por ID
    // Fluxo:
    // Recebe o ID do inquilino na URL
    // @PathVariable extrai o ID da URL e o passa para o metodo
    // Chama o serviço tenantService para buscar o inquilino por ID e retorna a resposta
    @GetMapping("/{id}")
    public TenantResponseDTO findById(@PathVariable Long id) {
        return tenantService.findById(id);
    }

    // Busca inquilinos por ID do proprietário
    // Fluxo:
    // Recebe o ID do proprietário na URL
    // @PathVariable extrai o ID da URL e o passa para o metodo
    // Chama o serviço tenantService para buscar os inquilinos por ID do proprietário e
    // retorna a lista de inquilinos encontrados
    @GetMapping("/landlord/{landlordId}")
    public List<TenantResponseDTO> findByLandlordId(@PathVariable Long landlordId) {
        return tenantService.findByLandlordId(landlordId);
    }

    // Atualiza um inquilino existente
    // Fluxo:
    // Recebe o ID do inquilino na URL e os dados atualizados no corpo da requisição
    // @PathVariable extrai o ID da URL e o passa para o metodo
    // @Valid valida as anotações dentro do DTO
    // @RequestBody transforma o JSON recebido em um objeto Java
    // Chama o serviço tenantService para atualizar o inquilino e retorna a resposta
    @PutMapping("/{id}")
    public TenantResponseDTO update(@PathVariable Long id,
                                    @Valid @RequestBody TenantRequestDTO request) {
        return tenantService.update(id, request);
    }

    // Exclui um inquilino por ID
    // Fluxo:
    // Recebe o ID do inquilino na URL
    // @PathVariable extrai o ID da URL e o passa para o metodo
    // Chama o serviço tenantService para excluir o inquilino e retorna status 204 No Content
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        tenantService.delete(id);
    }

}
