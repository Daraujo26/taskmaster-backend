package com.taskmaster.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.taskmaster.models.company.data.Job;

import java.util.List;
import java.util.Optional;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {
    List<Job> findByUserId(Long userId);

    Optional<Job> findByIdAndUserId(Long id, Long userId);
}
