package com.taskmaster.controllers.company;

import com.taskmaster.models.company.data.Job;
import com.taskmaster.models.company.data.JobDTO;
import com.taskmaster.models.user.AppUser;
import com.taskmaster.repositories.UserRepository;
import com.taskmaster.services.company.JobService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/jobs")
public class JobController {

    private static final Logger logger = LoggerFactory.getLogger(JobController.class);

    @Autowired
    private JobService jobService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public ResponseEntity<JobDTO> addJob(Principal principal, @RequestBody JobDTO jobDTO) {
        String email = principal.getName();
        logger.debug("Received job creation request from user: {}", email);
        JobDTO newJob = jobService.addJob(email, jobDTO);
        return ResponseEntity.ok(newJob);
    }

    @GetMapping
    public ResponseEntity<List<JobDTO>> getAllJobs(Principal principal) {
        Long userId = getUserIdFromPrincipal(principal);
        List<JobDTO> jobs = jobService.getAllJobs(userId);
        return ResponseEntity.ok(jobs);
    }

    @PutMapping("/{jobId}")
    public ResponseEntity<Job> updateJob(Principal principal, @PathVariable Long jobId, @RequestBody Job job) {
        String email = principal.getName();
        Job updatedJob = jobService.updateJob(email, jobId, job);
        return ResponseEntity.ok(updatedJob);
    }

    @DeleteMapping("/{jobId}")
    public ResponseEntity<Void> deleteJob(Principal principal, @PathVariable Long jobId) {
        String email = principal.getName();
        jobService.deleteJob(email, jobId);
        return ResponseEntity.noContent().build();
    }

    private Long getUserIdFromPrincipal(Principal principal) {
        String email = principal.getName();
        AppUser user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user email"));
        return user.getId();
    }
}
