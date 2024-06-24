package com.taskmaster.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.taskmaster.models.company.data.usage.TaxRate;

import java.util.List;

@Repository
public interface TaxRateRepository extends JpaRepository<TaxRate, Long> {
    List<TaxRate> findByUserId(Long userId);
}
