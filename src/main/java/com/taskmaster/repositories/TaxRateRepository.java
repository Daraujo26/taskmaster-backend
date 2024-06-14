package com.taskmaster.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.taskmaster.models.company.data.usage.TaxRate;

public interface TaxRateRepository extends JpaRepository<TaxRate, Long> {
}
