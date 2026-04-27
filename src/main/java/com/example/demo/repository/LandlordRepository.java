package com.example.demo.repository;

import com.example.demo.domain.entity.Landlord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LandlordRepository extends JpaRepository<Landlord, Long> {
}
