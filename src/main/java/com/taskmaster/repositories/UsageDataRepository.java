package com.taskmaster.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.taskmaster.models.company.data.usage.UsageData;

public interface UsageDataRepository extends JpaRepository<UsageData, Long> {
}
