package com.taskmaster.services.company;

import com.taskmaster.models.company.data.Client;
import com.taskmaster.models.company.data.Job;
import com.taskmaster.models.company.data.JobDTO;
import com.taskmaster.models.user.AppUser;
import com.taskmaster.repositories.JobRepository;
import com.taskmaster.repositories.ClientRepository;
import com.taskmaster.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class JobService {

    private static final Logger logger = LoggerFactory.getLogger(JobService.class);

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private UserRepository userRepository;

    public JobDTO addJob(String email, JobDTO jobDTO) {
        AppUser user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user email"));

        logger.debug("User found: {}", user);

        Client client = null;
        if (jobDTO.getClientId() != null) {
            client = clientRepository.findById(jobDTO.getClientId())
                    .orElseThrow(() -> new IllegalArgumentException("Client not found"));
        }

        logger.debug("Client found: {}", client);

        Job job = new Job();
        job.setUser(user);
        job.setClient(client);
        job.setJobTitle(jobDTO.getJobTitle());
        job.setMessage(jobDTO.getMessage());
        job.setJobDates(jobDTO.getJobDates());
        job.setStartTime(jobDTO.getStartTime());
        job.setEndTime(jobDTO.getEndTime());
        job.setStatus(jobDTO.getStatus());
        job.setContractItems(jobDTO.getContractItems());

        logger.debug("Job to be saved: {}", job);

        Job savedJob = jobRepository.save(job);
        return new JobDTO(savedJob);
    }

    public List<JobDTO> getAllJobs(Long userId) {
        List<Job> jobs = jobRepository.findByUserId(userId);
        return jobs.stream().map(JobDTO::new).collect(Collectors.toList());
    }

    public Job updateJob(String email, Long jobId, Job jobUpdates) {
        AppUser user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
        Job existingJob = jobRepository.findByUserId(user.getId()).stream()
                .filter(job -> job.getId().equals(jobId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Job not found for this user"));

        // Update only the fields that are provided
        if (jobUpdates.getJobTitle() != null) {
            existingJob.setJobTitle(jobUpdates.getJobTitle());
        }
        if (jobUpdates.getMessage() != null) {
            existingJob.setMessage(jobUpdates.getMessage());
        }
        if (jobUpdates.getJobDates() != null) {
            existingJob.setJobDates(jobUpdates.getJobDates());
        }
        if (jobUpdates.getStartTime() != null) {
            existingJob.setStartTime(jobUpdates.getStartTime());
        }
        if (jobUpdates.getEndTime() != null) {
            existingJob.setEndTime(jobUpdates.getEndTime());
        }
        if (jobUpdates.getStatus() != null) {
            existingJob.setStatus(jobUpdates.getStatus());
        }
        if (jobUpdates.getClient() != null) {
            existingJob.setClient(clientRepository.findById(jobUpdates.getClient().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Client not found")));
        }

        return jobRepository.save(existingJob);
    }

    public void deleteJob(String email, Long jobId) {
        AppUser user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user email"));

        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new IllegalArgumentException("Job not found"));

        if (!job.getUser().getId().equals(user.getId())) {
            throw new IllegalArgumentException("Job does not belong to the user");
        }

        jobRepository.delete(job);
    }
}
