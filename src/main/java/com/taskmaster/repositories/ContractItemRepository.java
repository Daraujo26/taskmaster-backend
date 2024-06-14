package com.taskmaster.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.taskmaster.models.company.data.usage.ContractItem;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContractItemRepository extends JpaRepository<ContractItem, Long> {
    List<ContractItem> findByUserId(Long userId);

    Optional<ContractItem> findByIdAndUserId(Long id, Long userId);
}
