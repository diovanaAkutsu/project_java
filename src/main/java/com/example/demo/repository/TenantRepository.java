package com.example.demo.repository;

import com.example.demo.domain.entity.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TenantRepository extends JpaRepository<Tenant, Long> {
    List<Tenant> findByLandlordId(Long landlordId);
}